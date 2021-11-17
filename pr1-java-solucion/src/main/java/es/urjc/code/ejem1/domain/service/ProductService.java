package es.urjc.code.ejem1.domain.service;

import java.util.Collection;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.event.ProductDeletedEvent;

public interface ProductService {
	public Collection<FullProductDTO> getProducts();
	public FullProductDTO getProduct(Long id);
	public ProductCreatedEvent createProduct(ProductDTO productDTO);
	public ProductDeletedEvent deleteProduct(Long id);
}
