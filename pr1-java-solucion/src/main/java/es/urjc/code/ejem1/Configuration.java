package es.urjc.code.ejem1;

import org.springframework.context.annotation.Bean;

import es.urjc.code.ejem1.domain.service.ProductService;
import es.urjc.code.ejem1.domain.service.ProductServiceImpl;
import es.urjc.code.ejem1.domain.service.ShoppingCartService;
import es.urjc.code.ejem1.domain.service.ShoppingCartServiceImpl;
import es.urjc.code.ejem1.domain.service.ShoppingCartValueService;
import es.urjc.code.ejem1.domain.service.ShoppingCartValueServiceImpl;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartValueRepositoryAdapter;
import es.urjc.code.ejem1.service.ValidationServiceImpl;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ShoppingCartService shoppingCartService(
	        SpringDataJPAShoppingCartRepositoryAdapter shoppingCartRepositoryAdapter,
	        SpringDataJPAProductRepositoryAdapter productRepositoryAdapter) {
		return new ShoppingCartServiceImpl(
		        shoppingCartRepositoryAdapter,
		        productRepositoryAdapter,
		        new ValidationServiceImpl());
	}

	@Bean
	public ProductService productService(SpringDataJPAProductRepositoryAdapter repositoryAdapter) {
		return new ProductServiceImpl(repositoryAdapter);
	}
	
	@Bean
	public ShoppingCartValueService shoppingCartValueService(SpringDataJPAShoppingCartValueRepositoryAdapter repositoryAdapter) {
		return new ShoppingCartValueServiceImpl(repositoryAdapter);
	}

}
