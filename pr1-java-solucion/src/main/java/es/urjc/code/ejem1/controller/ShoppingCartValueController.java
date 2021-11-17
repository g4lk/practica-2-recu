package es.urjc.code.ejem1.controller;

import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.controller.dto.ShoppingCartValueResponseDTO;
import es.urjc.code.ejem1.domain.service.ShoppingCartValueService;

@RestController
@RequestMapping("/api/cartvalue")
public class ShoppingCartValueController {

    	private ShoppingCartValueService shoppingValueService;
	private ModelMapper mapper = new ModelMapper();

	public ShoppingCartValueController(ShoppingCartValueService shoppingValueService) {
		this.shoppingValueService = shoppingValueService;
	}

	@GetMapping("/")
	public Collection<ShoppingCartValueResponseDTO> getShoppingCart() {
		return Arrays.asList(mapper.map(shoppingValueService.getShoppingCartValues(),
			ShoppingCartValueResponseDTO[].class));
	}
    
}
