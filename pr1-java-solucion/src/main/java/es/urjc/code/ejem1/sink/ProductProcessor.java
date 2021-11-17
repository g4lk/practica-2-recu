package es.urjc.code.ejem1.sink;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.event.ProductDeletedEvent;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;

@Component
public class ProductProcessor {

    	private SpringDataJPAProductRepository productRepository;

	public ProductProcessor(SpringDataJPAProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@EventListener
	public void handleSaveProduct(ProductCreatedEvent event) {
	    productRepository.save(new ProductEntity(event.getId(),event.getName(),event.getDescription(), event.getPrice()));
	}

	@EventListener
	public void handleDeleteProduct(ProductDeletedEvent event) {
	    productRepository.deleteById(event.getId());
	}
}
