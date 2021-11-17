package es.urjc.code.ejem1.domain.event;

public class ShoppingCartCreatedEvent {

	private final Long id;

	public ShoppingCartCreatedEvent(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

}
