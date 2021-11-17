package es.urjc.code.ejem1.controller.dto;

import java.util.UUID;

public class ShoppingCartValueResponseDTO {
    private UUID cartId;
	private Double value;

	public ShoppingCartValueResponseDTO() {
		super();
	}

	public ShoppingCartValueResponseDTO(UUID cartId, Double value) {
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
