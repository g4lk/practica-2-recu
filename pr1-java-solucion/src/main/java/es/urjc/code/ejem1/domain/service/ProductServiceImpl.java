package es.urjc.code.ejem1.domain.service;

import java.util.Collection;
import java.util.Random;

import org.modelmapper.ModelMapper;

import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.event.ProductDeletedEvent;
import es.urjc.code.ejem1.domain.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {

	private ProductRepository repository;
	ModelMapper mapper = new ModelMapper();

	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Collection<FullProductDTO> getProducts() {
		return repository.finAll();
	}

	@Override
	public FullProductDTO getProduct(Long id) {
		return repository.findById(id);
	}

	@Override
	public ProductCreatedEvent createProduct(ProductDTO productDTO) {
		// We transform dtos into domain objects
		Product product = mapper.map(productDTO, Product.class);
		// Single logic inside domain
		product.setId(new Random().nextLong());
		
		// We produce the event that is returned to caller
		ProductCreatedEvent event = new ProductCreatedEvent(product.getId(), product.getName(), product.getDescription(), product.getPrice());		

		return event;
	}

	@Override
	public ProductDeletedEvent deleteProduct(Long id) {
	    return new ProductDeletedEvent(id);
	}

}
