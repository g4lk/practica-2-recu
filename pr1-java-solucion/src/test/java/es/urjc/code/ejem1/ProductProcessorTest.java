package es.urjc.code.ejem1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.modelmapper.ModelMapper;

import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.event.ProductDeletedEvent;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.sink.ProductProcessor;

@TestMethodOrder(OrderAnnotation.class)
public class ProductProcessorTest {

    	private SpringDataJPAProductRepository productRepository;
	private ProductProcessor productProcessor;

	private ModelMapper mapper = new ModelMapper();

	private static Product product = new Product(
	        "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
	        "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
	        49.99);

	@BeforeEach
	void setUp() {
		this.productRepository = mock(SpringDataJPAProductRepository.class);
		this.productProcessor = new ProductProcessor(productRepository);
		
	}

	@Test
	@Order(1)
	void productCanBeAdded() {
		

		ProductCreatedEvent createdProductEvent = mapper.map(product, ProductCreatedEvent.class);
		
		productProcessor.handleSaveProduct(createdProductEvent);
		
		verify(productRepository,times(1)).save(any(ProductEntity.class));
	}

	@Test
	@Order(2)
	void productCanBeDeleted() {
	    	ProductDeletedEvent deletedProductEvent = mapper.map(product, ProductDeletedEvent.class);
	    	
		productProcessor.handleDeleteProduct(deletedProductEvent);
		
		verify(productRepository).deleteById(deletedProductEvent.getId());
	}
}
