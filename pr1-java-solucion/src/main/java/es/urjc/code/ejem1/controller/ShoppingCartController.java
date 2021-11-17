package es.urjc.code.ejem1.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.controller.dto.ShoppingCartRequestDTO;
import es.urjc.code.ejem1.controller.dto.ShoppingCartResponseDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.event.ItemRemovedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.domain.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {

	private ShoppingCartService shoppingService;
	private ApplicationEventPublisher applicationEventPublisher;
	
	private ModelMapper mapper = new ModelMapper();
	
	public ShoppingCartController(ShoppingCartService shoppingService, ApplicationEventPublisher applicationEventPublisher) {
		this.shoppingService = shoppingService;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@GetMapping("/{id}")
	public ShoppingCartResponseDTO getShoppingCart(@PathVariable UUID id) {
		return mapper.map(shoppingService.getShoppingCart(id), ShoppingCartResponseDTO.class);
	}

	@PostMapping("/{idShoppingCart}/product/{idProduct}/quantity/{quantity}")
	public ResponseEntity<UUID> addProductToShoppingCart(
	        @PathVariable UUID idShoppingCart,
	        @PathVariable UUID idProduct,
	        @PathVariable int quantity) {

	    
	    ItemAddedEvent event = shoppingService.addProduct(idShoppingCart, idProduct, quantity);

	    applicationEventPublisher.publishEvent(event);
	    
		return ResponseEntity.ok(event.getCartId());
	}

	@DeleteMapping("/{idShoppingCart}/product/{idProduct}")
	public ShoppingCartResponseDTO deleteProductInShoppingCart(
	        @PathVariable UUID idShoppingCart,
	        @PathVariable UUID idProduct) {
	    	ItemRemovedEvent event = shoppingService.deleteProduct(idShoppingCart, idProduct);
		applicationEventPublisher.publishEvent(event);
		return mapper.map(event, ShoppingCartResponseDTO.class);
	}

	@PostMapping
	public ResponseEntity<ShoppingCartResponseDTO> createShoppingCart() {
	    	ShoppingCartCreatedEvent event = shoppingService.createShoppingCart();

		applicationEventPublisher.publishEvent(event);

		URI location = fromCurrentRequest().path("/{id}")
		        .buildAndExpand(event.getId()).toUri();

		return ResponseEntity.created(location).body(
		        mapper.map(event, ShoppingCartResponseDTO.class));
	}

	@PatchMapping("/{id}")
	public ShoppingCartResponseDTO updateShoppingCart(
	        @PathVariable UUID id,
	        @Validated @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
	    	ShoppingCartClosedEvent event = shoppingService.updateShoppingCart(id,
		        mapper.map(shoppingCartRequestDTO, ShoppingCartDTO.class));

		return mapper.map(event, ShoppingCartResponseDTO.class);
		
	}

	@DeleteMapping("/{id}")
	public ShoppingCartResponseDTO deleteShoppingCart(@PathVariable UUID id) {
	    ShoppingCartDeletedEvent event = shoppingService.deleteShoppingCart(id); 
		applicationEventPublisher.publishEvent(event);
		
		ShoppingCartResponseDTO responseDto = new ShoppingCartResponseDTO();
		responseDto.setId(id);
		
		return responseDto;
	}
}
