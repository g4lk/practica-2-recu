package es.urjc.code.ejem1.infrastructure.repository;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.repository.ShoppingCartRepository;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.exception.ShoppingCartNotFoundException;

@Component
public class SpringDataJPAShoppingCartRepositoryAdapter implements ShoppingCartRepository {

	private SpringDataJPAShoppingCartRepository repository;
	private ModelMapper mapper = new ModelMapper();

	public SpringDataJPAShoppingCartRepositoryAdapter(SpringDataJPAShoppingCartRepository repository) {
		this.repository = repository;
	}

	@Override
	public FullShoppingCartDTO findById(UUID id) {
		return mapper.map(repository.findById(id).orElseThrow(ShoppingCartNotFoundException::new),
		        FullShoppingCartDTO.class);
	}

	@Override
	public FullShoppingCartDTO save(FullShoppingCartDTO shoppingCart) {
		ShoppingCartEntity shoppingCartEntity = mapper.map(shoppingCart, ShoppingCartEntity.class);
		repository.save(shoppingCartEntity);

		return findById(shoppingCartEntity.getId());
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

}
