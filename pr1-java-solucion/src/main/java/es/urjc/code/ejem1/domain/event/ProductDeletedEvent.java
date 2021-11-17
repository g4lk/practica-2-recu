package es.urjc.code.ejem1.domain.event;

public class ProductDeletedEvent {

	private Long id;

	public ProductDeletedEvent() {}
	public ProductDeletedEvent(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
		
}
