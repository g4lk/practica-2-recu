package es.urjc.code.ejem1.domain;

import java.util.ArrayList;
import java.util.List;

import es.urjc.code.ejem1.domain.service.ValidationService;


public class ShoppingCart {

	private Long id;
	private ShoppingCartStatus status;
	private List<ShoppingCartItem> items;

	private ValidationService validationService;

	public ShoppingCart() {
		super();

		this.status = ShoppingCartStatus.PENDING;
		this.items = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShoppingCartStatus getStatus() {
		return status;
	}

	public void setStatus(ShoppingCartStatus status) {
		this.status = status;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public ValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}

	public void addItem(ShoppingCartItem shoppingCartItem) {
		this.items.add(shoppingCartItem);
	}

	public boolean removeItem(Long idProduct) {
		return this.items.removeIf(item -> item.getProduct().getId().equals(idProduct));
	}

	public double getPrice() {
		double price = 0;

		if (this.items != null) {
			for (ShoppingCartItem item : this.items) {
				price += item.getTotalPrice();
			}
		}

		return price;
	}

	public boolean validate(ValidationService validationService) {
		return validationService.validate(this.items);
	}

}
