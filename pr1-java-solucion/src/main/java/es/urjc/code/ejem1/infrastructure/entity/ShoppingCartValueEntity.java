package es.urjc.code.ejem1.infrastructure.entity;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCartValueEntity {

    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID cartId;

	private Double value;

	public ShoppingCartValueEntity() {
		super();
		this.value = new Double(0);
	}

	public ShoppingCartValueEntity(UUID cartId, Double value) {
		super();
		this.cartId = cartId;
		this.value = value;
	}

	public UUID getCartId() {
		return this.cartId;
	}

	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
