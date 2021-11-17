package es.urjc.code.ejem1.domain.event;


public class ProductCreatedEvent {

	private Long id;
	private String name;
	private String description;
	private double price;

	public ProductCreatedEvent() {
	    super();
	}
	
	public ProductCreatedEvent(Long id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

}
