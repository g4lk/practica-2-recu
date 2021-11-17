package es.urjc.code.ejem1.sink;

import java.util.Collections;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.domain.ShoppingCartStatus;
import es.urjc.code.ejem1.domain.event.ItemRemovedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartItemEntity;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartValueEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartValueRepository;

@Component
public class ShoppingCartProcessor {
	
	private SpringDataJPAShoppingCartRepository shoppingCartRepository;
	private SpringDataJPAShoppingCartValueRepository valueRepository;
	private SpringDataJPAProductRepository productRepository;
	
	public ShoppingCartProcessor(
			SpringDataJPAShoppingCartRepository shoppingCartRepository, 
			SpringDataJPAShoppingCartValueRepository valueRepository,
			SpringDataJPAProductRepository productRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.valueRepository = valueRepository;
		this.productRepository = productRepository;
	}
	
	
	@EventListener
	public void handleItemAddedEvent(ItemAddedEvent event) {
		// If we had two separate services (the one issuing commands and the one persisting commands), 
		// this processor would use the domain classes to do this logic.
		ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(event.getCartId()).get();
		ProductEntity productEntity = productRepository.findById(event.getProductId()).get();
		ShoppingCartItemEntity itemEntity = new ShoppingCartItemEntity(UUID.randomUUID(), productEntity, event.getQuantity());
		shoppingCartEntity.getItems().removeIf((item)->item.getProduct().getId() == productEntity.getId());
		shoppingCartEntity.getItems().add(itemEntity);
		shoppingCartRepository.save(shoppingCartEntity);		
		ShoppingCartValueEntity shoppingCartValueEntity = valueRepository.findById(event.getCartId()).get();
		shoppingCartValueEntity.setValue(shoppingCartEntity.getPrice());
		valueRepository.save(shoppingCartValueEntity);
		
	}
	
	@EventListener
	public void handleShoppingCartCreatedEvent(ShoppingCartCreatedEvent event) {
		ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity(event.getId(),Collections.emptyList());
		shoppingCartRepository.save(shoppingCartEntity);
		
		ShoppingCartValueEntity shoppingCartValueEntity = new ShoppingCartValueEntity(event.getId(),Double.valueOf(0));
		valueRepository.save(shoppingCartValueEntity);
	}
	
	@EventListener
	public void handleClosedShoppingCartEvent(ShoppingCartClosedEvent event) {
		ShoppingCartEntity shoppingCart = shoppingCartRepository.findById(event.getId()).get();
		shoppingCart.setStatus(ShoppingCartStatus.COMPLETED);
		shoppingCartRepository.save(shoppingCart);
		
		valueRepository.deleteById(event.getId());

	}
	
	@EventListener
    public void handleDeleteShoppingCartEvent(ShoppingCartDeletedEvent event) {
		shoppingCartRepository.deleteById(event.getId());
    }
	
	@EventListener
	public void handleItemDeletedEvent(ItemRemovedEvent event) {
		ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(event.getIdShoppingCart()).get();
		shoppingCartEntity.getItems().removeIf((item) -> item.getProduct().getId() == event.getIdProduct());
		shoppingCartRepository.save(shoppingCartEntity);
		
		ShoppingCartValueEntity shoppingCartValueEntity = valueRepository.findById(event.getIdShoppingCart()).get();
		shoppingCartValueEntity.setValue(shoppingCartEntity.getPrice());
		valueRepository.save(shoppingCartValueEntity);
	}
}
	
	
