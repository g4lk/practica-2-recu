package es.urjc.code.ejem1.domain.repository;

import java.util.UUID;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;

public interface ShoppingCartRepository {
	FullShoppingCartDTO findById(UUID id);

	FullShoppingCartDTO save(FullShoppingCartDTO shoppingCart);

	void deleteById(UUID id);
	
}
