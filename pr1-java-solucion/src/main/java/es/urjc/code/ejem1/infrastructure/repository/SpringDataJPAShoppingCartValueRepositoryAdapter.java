package es.urjc.code.ejem1.infrastructure.repository;

import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartValueDTO;
import es.urjc.code.ejem1.domain.repository.ShoppingCartValueRepository;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartValueEntity;

@Component
public class SpringDataJPAShoppingCartValueRepositoryAdapter implements ShoppingCartValueRepository {

	private SpringDataJPAShoppingCartValueRepository repository;
	private ModelMapper mapper = new ModelMapper();

	public SpringDataJPAShoppingCartValueRepositoryAdapter(
		SpringDataJPAShoppingCartValueRepository repository) {
		this.repository = repository;
	}

	@Override
	public Collection<FullShoppingCartValueDTO> findAll() {
		return Arrays.asList(mapper.map(repository.findAll(), FullShoppingCartValueDTO[].class));
	}

	@Override
	public void save(FullShoppingCartValueDTO shoppingCartExpenditure) {
		this.repository.save(mapper.map(shoppingCartExpenditure, ShoppingCartValueEntity.class));
	}

}
