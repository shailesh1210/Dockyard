package solution;

import java.util.List;
import java.util.LinkedList;

import shipping.IContainer;
import shipping.IShip;

/**
 * @brief This class represents a Ship with containers at the dockyard.
 * Each Ship object a unique registration and containers.
 * @author shailesh
 *
 */
public class Ship implements IShip {
	private String registration;
	private List<IContainer> containers;
	
	/**
	 * @brief A default constructor that sets registration to null.
	 * Creates and initializes containers list
	 */
	public Ship() {
		this.registration = null;
		this.containers = new LinkedList<IContainer>();
	}
	
	/**
	 * @brief A constructor with one parameter that sets registration to id.
	 * Creates and initializes containers list
	 */
	public Ship(String id) {
		setRegistration(id);
		this.containers = new LinkedList<IContainer>();
	}

	/**
	 * @brief Returns Ship's registration
	 * @return registration
	 */
	@Override
	public String getRegistration() {
		return this.registration;
	}

	/**
	 * @brief Sets Ship's registration
	 * @param id Ship's registration defined by the user
	 */
	@Override
	public void setRegistration(String id) {
		this.registration = id;
	}

	/**
	 * @brief Adds container to the ship from shipping manifest
	 * @param container IContainer object
	 */
	@Override
	public void addContainer(IContainer container) {
		try {
			containers.add(container);
		}
		catch(NullPointerException e) {
			System.out.println("Error: Container object cannot be a Null!");
			e.printStackTrace();
		}
		
	}

	/**
	 * @brief Returns the list of containers in the ship
	 * @return this.containers
	 */
	@Override
	public List<IContainer> containers() {
		return this.containers;
	}

	/**
	 * @brief Returns and removes containers from the ship
	 * @return dockyardContainers List of containers that is offloaded
	 * from the ship
	 */
	@Override
	public List<IContainer> offload() {
		List<IContainer> dockyardContainers = new LinkedList<IContainer>();
		for(IContainer container : containers) {
			dockyardContainers.add(new Container(container));	
		}
		
		removeContainers();
		
		return dockyardContainers;
	}
	
	/**
	 * @brief Returns number of containers in the ship
	 * @return containers.size()
	 */
	public int containerCount() {
		return this.containers.size();
	}
	
	/**
	 * @brief Removes container from the ship
	 */
	public void removeContainers() {
		this.containers.clear();
	}

	/**
	 * Displays the detail of the ship - registration and container count
	 */
	@Override
	public void printDetails() {
		System.out.println("Ship " + getRegistration() + " has " + containerCount() + " containers.");
	}

}
