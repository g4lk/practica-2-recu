package es.urjc.code.ejem1.domain.service;

import java.util.UUID;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.domain.event.ItemRemovedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartDeletedEvent;

public interface ShoppingCartService {
	public FullShoppingCartDTO getShoppingCart(UUID id);

	public ShoppingCartCreatedEvent createShoppingCart();

	public ShoppingCartClosedEvent updateShoppingCart(UUID id, ShoppingCartDTO shoppingCartDTO);

	public ShoppingCartDeletedEvent deleteShoppingCart(UUID id);

	public ItemAddedEvent addProduct(UUID idShoppingCart, UUID idProduct, int nProducts);

	public ItemRemovedEvent deleteProduct(UUID idShoppingCart, UUID idProduct);

	
}
