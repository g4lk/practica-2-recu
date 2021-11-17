package es.urjc.code.ejem1.domain.event;



public class ItemRemovedEvent {

	private Long idShoppingCart;
	private Long idProduct;

	public ItemRemovedEvent(Long idShoppingCart, Long idProduct) {
		this.idShoppingCart = idShoppingCart;
		this.idProduct = idProduct;
	}
	
	public Long getIdShoppingCart() {
		return idShoppingCart;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

}
