package es.urjc.code.ejem1.domain.event;

import java.util.UUID;

public class ProductDeletedEvent {

	private UUID id;

	public ProductDeletedEvent() {}
	public ProductDeletedEvent(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
		
}
