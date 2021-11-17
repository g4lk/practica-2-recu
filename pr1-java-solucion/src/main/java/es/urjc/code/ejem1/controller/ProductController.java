package es.urjc.code.ejem1.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.controller.dto.ProductRequestDTO;
import es.urjc.code.ejem1.controller.dto.ProductResponseDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.event.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;
	private ModelMapper mapper = new ModelMapper();
	private final ApplicationEventPublisher applicationEventPublisher;

	public ProductController(ProductService productService, ApplicationEventPublisher applicationEventPublisher) {
		this.productService = productService;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@GetMapping
	public Collection<ProductResponseDTO> getProducts() {
		return Arrays.asList(mapper.map(productService.getProducts(), ProductResponseDTO[].class));
	}

	@GetMapping("/{id}")
	public ProductResponseDTO getProduct(@PathVariable Long id) {
		return mapper.map(productService.getProduct(id), ProductResponseDTO.class);
	}

	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductDTO productDTO = mapper.map(productRequestDTO, ProductDTO.class);
		ProductCreatedEvent event = productService.createProduct(productDTO);

		applicationEventPublisher.publishEvent(event);

		URI location = fromCurrentRequest().path("/{id}")
		        .buildAndExpand(event.getId()).toUri();

		return ResponseEntity.created(location).body(
		        mapper.map(event, ProductResponseDTO.class));
	}

	@DeleteMapping("/{id}")
	public ProductResponseDTO deleteProduct(@PathVariable Long id) {
	    	applicationEventPublisher.publishEvent(productService.deleteProduct(id));
		
		return new ProductResponseDTO(id);
	}

}
