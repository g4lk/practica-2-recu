package es.urjc.code.ejem1.domain.dto;

import java.util.UUID;

public class FullShoppingCartValueDTO {

    private UUID cartId;
	private Double value;

	public FullShoppingCartValueDTO() {
		super();
	}

	public FullShoppingCartValueDTO(UUID cartId, Double value) {
		super();
		this.cartId = cartId;
		this.value = value;
	}

	public UUID getCartId() {
		return cartId;
	}

	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
