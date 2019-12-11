package solution;

import shipping.IContainer;
import shipping.ITruck;

/**
 * @brief This class represents a truck carrying a container that is headed
 * to its destination city. Each Truck object has a unique registration, 
 * a destination city it heads to and one container.
 * @author shailesh
 *
 */
public class Truck implements ITruck {
	private String registration;
	private String destinationCity;
	IContainer container;
	
	/**
	 * @brief A default class constructor that sets registration,
	 * destinationCity and container to null
	 */
	public Truck() {
		setRegistration(null);
		setDestinationCity(null);
		
		this.container = null;
	}
	
	/**
	 * @brief A class constructor with two parameters. Sets registration 
	 * and destinationCity to id and city respectively. Sets container to null
	 * @param id Truck's unique registration number
	 * @param city Truck's destination city
	 */
	public Truck(String id, String city) {
		setRegistration(id);
		setDestinationCity(city);
		
		this.container = null;
	}
	
	/**
	 * @brief A class constructor with two parameters. Sets registration 
	 * and destinationCity to id and city respectively. Adds container to
	 * the truck
	 * @param id Truck's unique registration number
	 * @param city Truck's destination city
	 * @param container IContainer object
	 */
	public Truck(String id, String city, IContainer container) {
		this.registration = id;
		this.destinationCity = city;
		addContainer(container);
		
	}

	
	/**
	 * @brief Return Truck's registration number
	 * @return registration
	 */
	@Override
	public String registration() {
		return this.registration;
	}

	/**
	 * @brief Returns Truck's destination city
	 * @return destinationCity
	 */
	@Override
	public String destinationCity() {
		return this.destinationCity;
	}
	
	/**
	 * @brief Sets Truck's registration
	 * @param id Truck's id defined by the user 
	 */
	public void setRegistration(String id) {
		this.registration = id;
	}
	
	/**
	 * @brief Sets Truck's destination city
	 * @param city Truck's destination city defined by the user
	 */
	public void setDestinationCity(String city) {
		this.destinationCity = city;
	}

	/**
	 * @brief Add container to the truck if it is empty.
	 * @param container IContainer object
	 * @return true if container is added to the truck successfully, false if otherwise
	 */
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

	/**
	 * @brief Removes and return container from the truck
	 * @return container object if truck is not empty, null if otherwise
	 */
	@Override
	public IContainer offloadContainer() {
		if(!hasContainer()) {
			return null;
		}
		else {
			IContainer offloadedContainer = new Container(this.container);
			this.container = null;
			
			return offloadedContainer;
		}
	}

	/**
	 * @brief Checks if truck has a container
	 * @return true if truck is not empty, false if otherwise
	 */
	@Override
	public boolean hasContainer() {
		if(this.container != null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @brief Displays truck details - registration, destination city and container id 
	 * if truck is not empty.
	 */
	@Override
	public void printDetails() {
		if(this.container != null) {
			System.out.println("Truck " + registration() + " is headed to " + destinationCity() + " with container " + this.container.id());
		}
		else {
			System.out.println("Truck " + registration() + " is headed to " + destinationCity() + " with no container");
		}

	}

}
