package es.urjc.code.ejem1.domain.event;


public class ShoppingCartDeletedEvent {

	private final Long id;

	public ShoppingCartDeletedEvent(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
