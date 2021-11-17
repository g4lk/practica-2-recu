package es.urjc.code.ejem1.infrastructure.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCartItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@OneToOne
	private ProductEntity product;

	private int quantity;

	public ShoppingCartItemEntity() {
		super();
	}

	public ShoppingCartItemEntity(UUID id, ProductEntity product, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return this.quantity * this.product.getPrice();
	}

}
