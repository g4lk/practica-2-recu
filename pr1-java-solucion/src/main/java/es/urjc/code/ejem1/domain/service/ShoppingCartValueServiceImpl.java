package es.urjc.code.ejem1.domain.service;

import java.util.Collection;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartValueDTO;
import es.urjc.code.ejem1.domain.repository.ShoppingCartValueRepository;

public class ShoppingCartValueServiceImpl implements ShoppingCartValueService {

    private ShoppingCartValueRepository repository;

    public ShoppingCartValueServiceImpl(ShoppingCartValueRepository repository) {
	this.repository = repository;
    }

    @Override
    public Collection<FullShoppingCartValueDTO> getShoppingCartValues() {
	// TODO Auto-generated method stub
	return repository.findAll();
    }

}
