package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ShoppingCartDeletedEvent {

	private final UUID id;

	public ShoppingCartDeletedEvent(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
