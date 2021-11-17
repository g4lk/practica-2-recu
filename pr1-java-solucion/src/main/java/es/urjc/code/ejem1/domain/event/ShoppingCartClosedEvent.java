package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ShoppingCartClosedEvent {

	private final UUID id;

	public ShoppingCartClosedEvent(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
}
