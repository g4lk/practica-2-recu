package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ShoppingCartCreatedEvent {

	private final UUID id;

	public ShoppingCartCreatedEvent(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

}
