package es.urjc.code.ejem1.infrastructure.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCartValueEntity {

    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	private Double value;

	public ShoppingCartValueEntity() {
		super();
		this.value = new Double(0);
	}

	public ShoppingCartValueEntity(Long cartId, Double value) {
		super();
		this.cartId = cartId;
		this.value = value;
	}

	public Long getCartId() {
		return this.cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
