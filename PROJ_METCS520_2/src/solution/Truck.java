package solution;

import shipping.IContainer;
import shipping.ITruck;

public class Truck implements ITruck {
	private String registration;
	private String destinationCity;
	IContainer container;
	
	public Truck() {
		this.registration = null;
		this.destinationCity = null;
		this.container = null;
	}
	
	public Truck(String id, String city) {
		this.registration = id;
		this.destinationCity = city;
		this.container = null;
	}
	
	public Truck(String id, String city, IContainer container) {
		this.registration = id;
		this.destinationCity = city;
		addContainer(container);
		
	}

	@Override
	public String registration() {
		return this.registration;
	}

	@Override
	public String destinationCity() {
		return this.destinationCity;
	}
	
	public void setRegistration(String id) {
		this.registration = id;
	}
	
	public void setDestinationCity(String city) {
		this.destinationCity = city;
	}

	@Override
	public boolean addContainer(IContainer container) {
		
		if(!hasContainer()) {
			try {
				this.container = container;
			}
			catch(NullPointerException e) {
				System.out.println("Error: Container object cannot be a Null!");
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public IContainer offloadContainer() {
		if(!hasContainer()) {
			return null;
		}
		else {
			IContainer offloadedContainer = new Container(this.container.id(), this.container.destinationCity());
			this.container = null;
			
			return offloadedContainer;
		}
	}

	@Override
	public boolean hasContainer() {
		if(this.container != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void printDetails() {
		if(this.container != null) {
			System.out.println("Truck " + registration() + "is headed to " + destinationCity() + " with container " + this.container.id());
		}
		else {
			System.out.println("Truck " + registration() + "is headed to " + destinationCity() + " with no container");
		}

	}

}
