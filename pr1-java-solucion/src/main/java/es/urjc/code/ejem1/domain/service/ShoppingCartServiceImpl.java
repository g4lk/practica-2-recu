package es.urjc.code.ejem1.domain.service;

import java.util.Random;

import org.modelmapper.ModelMapper;

import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.ShoppingCart;
import es.urjc.code.ejem1.domain.ShoppingCartItem;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.event.ItemAddedEvent;
import es.urjc.code.ejem1.domain.event.ItemRemovedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartCreatedEvent;
import es.urjc.code.ejem1.domain.event.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.domain.repository.ProductRepository;
import es.urjc.code.ejem1.domain.repository.ShoppingCartRepository;
import es.urjc.code.ejem1.infrastructure.exception.ShoppingCartDontStockException;
import es.urjc.code.ejem1.infrastructure.exception.ShoppingCartItemNotFoundException;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCartRepository shoppingCartRepository;
	private ProductRepository productRepository;
	private ValidationService validationService;
	
	private ModelMapper mapper = new ModelMapper();

	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
	        ProductRepository productRepository,
	        ValidationService validationService) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.productRepository = productRepository;
		this.validationService = validationService;
	}
	@Override
	public FullShoppingCartDTO getShoppingCart(Long id) {
		return mapper.map(shoppingCartRepository.findById(id), FullShoppingCartDTO.class);
	}

	@Override
	public ShoppingCartCreatedEvent createShoppingCart() {
	    	ShoppingCart shoppingCart = new ShoppingCart();
	    	shoppingCart.setId(new Random().nextLong());
		
		return new ShoppingCartCreatedEvent(shoppingCart.getId());
	}

	@Override
	public ShoppingCartClosedEvent updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO) {
	    	FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(id);

		ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);

		if (!shoppingCart.validate(validationService)) {

			throw new ShoppingCartDontStockException("Not enough stock");
		}
		return new ShoppingCartClosedEvent(shoppingCart.getId());
	}

	@Override
	public ShoppingCartDeletedEvent deleteShoppingCart(Long id) {
	    return new ShoppingCartDeletedEvent(id);
	}

	@Override
	public ItemAddedEvent addProduct(Long idShoppingCart, Long idProduct, int quantity) {
	
	    	FullProductDTO fullProductDTO = productRepository.findById(idProduct);
		FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(idShoppingCart);

		return addProduct(mapper.map(fullProductDTO, Product.class), mapper.map(fullShoppingCartDTO, ShoppingCart.class), quantity);
	}

	private ItemAddedEvent addProduct(Product product, ShoppingCart shoppingCart,
	        int quantity) {
		shoppingCart.removeItem(product.getId());

		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(
			product,
		        quantity);
		shoppingCart.addItem(shoppingCartItem);

		return new ItemAddedEvent(shoppingCart.getId(), product.getId(), quantity);
	}

	@Override
	public ItemRemovedEvent deleteProduct(Long idShoppingCart, Long idProduct) {
	    	FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(idShoppingCart);

		ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
		if (!shoppingCart.removeItem(idProduct)) {
			throw new ShoppingCartItemNotFoundException("Item not found.");
		}

		return new ItemRemovedEvent(idShoppingCart, idProduct);
	}
}
