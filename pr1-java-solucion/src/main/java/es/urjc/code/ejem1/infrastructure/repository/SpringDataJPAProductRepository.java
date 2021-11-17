package es.urjc.code.ejem1.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;

public interface SpringDataJPAProductRepository extends JpaRepository<ProductEntity, UUID> {

}
