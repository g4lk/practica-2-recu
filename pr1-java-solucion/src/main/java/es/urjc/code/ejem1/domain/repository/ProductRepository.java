package es.urjc.code.ejem1.domain.repository;

import java.util.Collection;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;

public interface ProductRepository {
	Collection<FullProductDTO> finAll();

	FullProductDTO findById(Long id);

	FullProductDTO save(FullProductDTO product);

	void deleteById(Long id);
}
