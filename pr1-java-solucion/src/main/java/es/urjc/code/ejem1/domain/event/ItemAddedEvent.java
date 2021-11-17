package es.urjc.code.ejem1.domain.event;

public class ItemAddedEvent {

    	private final Long cartId;
	private final Long productId;
	private final int quantity;

	public ItemAddedEvent(Long cartId, Long productId, int quantity) {
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getCartId() {
		return cartId;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
