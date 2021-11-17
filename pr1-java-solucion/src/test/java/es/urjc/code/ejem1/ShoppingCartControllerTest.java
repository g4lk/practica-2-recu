package es.urjc.code.ejem1;

import static org.mockito.Mockito.mock;

import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import es.urjc.code.ejem1.controller.ShoppingCartController;
import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.domain.service.ShoppingCartService;

public class ShoppingCartControllerTest {

    private ShoppingCartController shoppingCtrl;
    private ShoppingCartService shoppingService;
    private ApplicationEventPublisher applicationEventPublisher;
        
    	@BeforeEach
	public void setUp() {
		this.applicationEventPublisher = mock(ApplicationEventPublisher.class);
		this.shoppingService = mock(ShoppingCartService.class);
		this.shoppingCtrl = new ShoppingCartController(shoppingService, applicationEventPublisher);
	}

   @Test
   public void productCanBeAddedToShoppingCart() {
       UUID idShoppingCart = UUID.randomUUID();
       UUID idProduct = UUID.randomUUID();
       int quantity = 3;
       ItemAddedEvent itemAddedEvent = new ItemAddedEvent(idShoppingCart, idProduct, quantity);
        when(shoppingService.addProduct(idShoppingCart, idProduct, quantity)). thenReturn(itemAddedEvent);
       
       this.shoppingCtrl.addProductToShoppingCart(idShoppingCart, idProduct, quantity);
       verify(applicationEventPublisher).publishEvent(itemAddedEvent);
      
   }
	
}
