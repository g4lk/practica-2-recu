package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ItemAddedEvent {

    	private final UUID cartId;
	private final UUID productId;
	private final int quantity;

	public ItemAddedEvent(UUID cartId, UUID productId, int quantity) {
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public UUID getCartId() {
		return cartId;
	}
	
	public UUID getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
