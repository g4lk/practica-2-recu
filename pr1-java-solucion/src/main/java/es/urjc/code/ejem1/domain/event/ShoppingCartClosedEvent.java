package es.urjc.code.ejem1.domain.event;


public class ShoppingCartClosedEvent {

	private final Long id;

	public ShoppingCartClosedEvent(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
