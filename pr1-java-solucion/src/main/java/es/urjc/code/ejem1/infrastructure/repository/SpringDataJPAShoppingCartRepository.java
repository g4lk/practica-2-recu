package es.urjc.code.ejem1.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;

public interface SpringDataJPAShoppingCartRepository extends JpaRepository<ShoppingCartEntity, UUID> {

}
