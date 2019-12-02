package solution;

import shipping.IContainer;

public class Container implements IContainer {
	private String id;
	private String destinationCity;
	
	public Container() {
		this.id = null;
		this.destinationCity = null;
	}
	
	public Container(String id, String city) {
		this.id = id;
		this.destinationCity = city;
	}

	@Override
	public String id() {
		
		return this.id;
	}

	@Override
	public String description() {
		
		return null;
	}

	@Override
	public String destinationCity() {
		
		return this.destinationCity;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setDestinationCity(String city) {
		this.destinationCity = city;
	}

}
