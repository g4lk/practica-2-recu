package es.urjc.code.ejem1.domain.service;

import java.util.Collection;
import java.util.UUID;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.event.ProductDeletedEvent;

public interface ProductService {
	public Collection<FullProductDTO> getProducts();
	public FullProductDTO getProduct(UUID id);
	public ProductCreatedEvent createProduct(ProductDTO productDTO);
	public ProductDeletedEvent deleteProduct(UUID id);
}
