package es.urjc.code.ejem1.domain.service;

import java.util.List;

import es.urjc.code.ejem1.domain.ShoppingCartItem;

public interface ValidationService {

	boolean validate(List<ShoppingCartItem> items);
	
}
