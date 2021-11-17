package es.urjc.code.ejem1.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartValueEntity;

public interface SpringDataJPAShoppingCartValueRepository extends JpaRepository<ShoppingCartValueEntity, Long>{

}
