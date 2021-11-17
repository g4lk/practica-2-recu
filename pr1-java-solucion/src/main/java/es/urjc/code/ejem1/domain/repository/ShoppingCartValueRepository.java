package es.urjc.code.ejem1.domain.repository;

import java.util.Collection;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartValueDTO;

public interface ShoppingCartValueRepository {

    Collection<FullShoppingCartValueDTO> findAll();
    
    void save(FullShoppingCartValueDTO shoppingCartExpenditure);
}
