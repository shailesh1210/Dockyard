package solution;

import shipping.IContainer;

/**
 * @brief This class represents a container that is stored in the dockyard as 
 * well as a container that is loaded in the truck. Each container object has 
 * a unique id and a destination city
 * @author shailesh
 *
 */
public class Container implements IContainer {
	private String id;
	private String destinationCity;
	
	/**
	 * @brief A default constructor that set id and destinationCity
	 * to null
	 */
	public Container() {
		setId(null);
		setDestinationCity(null);
	}
	
	/**
	 * A constructor with two parameters that sets id and destinationCity 
	 * to id and city respectively
	 * @param id Container's id
	 * @param city Container's destination city
	 */
	public Container(String id, String city) {
		setId(id);
		setDestinationCity(city);
	}
	
	/**
	 * A copy constructor that sets id and destination city to other container
	 * object's id and destination city
	 * @param container IContainer object
	 */
	public Container(IContainer container) {
		setId(container.id());
		setDestinationCity(container.destinationCity());
	}

	/**
	 * @brief Return Container's id
	 * @return id
	 */
	@Override
	public String id() {
		
		return this.id;
	}

	/**
	 * @brief Returns a string with container's description
	 * @return Container's description
	 */
	@Override
	public String description() {
		
		return String.format("Container " + id() + " must be shipped to " + destinationCity());
	}

	/**
	 * @brief Returns Container's destination city
	 * @return destinationCity
	 */
	@Override
	public String destinationCity() {
		
		return this.destinationCity;
	}
	
	/**
	 * @brief Sets Container's id to id
	 * @param id Container id defined by the user
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @brief Sets Container's destinationCity to city
	 * @param city destination city defined by the user
	 */
	public void setDestinationCity(String city) {
		this.destinationCity = city;
	}

}
