package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ItemRemovedEvent {

	private UUID idShoppingCart;
	private UUID idProduct;

	public ItemRemovedEvent(UUID idShoppingCart, UUID idProduct) {
		this.idShoppingCart = idShoppingCart;
		this.idProduct = idProduct;
	}
	
	public UUID getIdShoppingCart() {
		return idShoppingCart;
	}
	
	public UUID getIdProduct() {
		return idProduct;
	}

}
