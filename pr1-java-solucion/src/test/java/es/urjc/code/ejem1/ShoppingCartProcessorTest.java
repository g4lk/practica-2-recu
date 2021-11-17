package es.urjc.code.ejem1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;



import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;

import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartValueEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartValueRepository;
import es.urjc.code.ejem1.sink.ShoppingCartProcessor;

@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartProcessorTest {
	
	private SpringDataJPAProductRepository productRepository;
	
	private SpringDataJPAShoppingCartValueRepository valueRepository;
	private SpringDataJPAShoppingCartRepository shoppingCartRepository;
	private ShoppingCartProcessor shoppingCartProcessor;

	

	@BeforeEach
	void setUp() {
		this.productRepository = mock(SpringDataJPAProductRepository.class);
		this.shoppingCartRepository = mock(SpringDataJPAShoppingCartRepository.class);
		this.valueRepository = mock(SpringDataJPAShoppingCartValueRepository.class);
		
		shoppingCartProcessor = new ShoppingCartProcessor(shoppingCartRepository, valueRepository, productRepository);
	}
	
	@Test
	@Order(1)
	void shoppingCartCanBeAdded() {
	    	ShoppingCartCreatedEvent shoppingCartCreatedEvent = new ShoppingCartCreatedEvent(UUID.randomUUID());
	    	shoppingCartProcessor.handleShoppingCartCreatedEvent(shoppingCartCreatedEvent);
		verify(shoppingCartRepository).save(any(ShoppingCartEntity.class));
		verify(valueRepository).save(any(ShoppingCartValueEntity.class));
	}
	
	
}
