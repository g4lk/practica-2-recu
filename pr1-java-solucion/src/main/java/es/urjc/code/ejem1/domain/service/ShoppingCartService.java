package es.urjc.code.ejem1.domain.service;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.domain.event.ItemRemovedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartDeletedEvent;

public interface ShoppingCartService {
	public FullShoppingCartDTO getShoppingCart(Long id);

	public ShoppingCartCreatedEvent createShoppingCart();

	public ShoppingCartClosedEvent updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO);

	public ShoppingCartDeletedEvent deleteShoppingCart(Long id);

	public ItemAddedEvent addProduct(Long idShoppingCart, Long idProduct, int nProducts);

	public ItemRemovedEvent deleteProduct(Long idShoppingCart, Long idProduct);

	
}
