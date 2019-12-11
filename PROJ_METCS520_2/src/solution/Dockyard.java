package solution;

import shipping.IContainer;
import shipping.IDockyard;
import shipping.ITruck;

import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @brief This class implements an abstract dockyard with following behaviors:
 * 1. Handles the storage of the containers in an organized way
 * 2. Handles loading of the truck that arrives at the dockyard
 * 3. Keeps tracks of containers in the dockyard
 * @author shailesh
 *
 */
public class Dockyard implements IDockyard {
	//A hashmap to store containers
	private HashMap<String, Queue <IContainer>> containersByCity;
	
	/**
	 * @brief A class constructor : Creates and initializes the hashmap 
	 * for storing the containers from the ship
	 */
	public Dockyard(){
		containersByCity = new HashMap<String, Queue<IContainer>>();
	}

	@Override
	/**@brief Adds container from the ship to the dockyard. Containers
	 * are stored in the dockyard by city.
	 * Steps for adding containers in the dockyard:
	 * 1. A check is performed to see if there is a storage facility for
	 * a given city in the dockyard
	 * 2. If there is not storage for a given city, a new storage (Queue) is created
	 * for that city and the container is added to it. If the storage exists, the 
	 * container is simply added to that storage.
	 * @param container IContainer object
	 */
	public void addContainer(IContainer container) {
		try {
			String city = container.destinationCity();
			if(!hasDestinationCity(city)) {
				Queue<IContainer> containers = new LinkedList<IContainer>();
				containers.add(container);
				
				this.containersByCity.put(city, containers);
			}
			else {
				this.containersByCity.get(city).add(container);
			}
			
		}
		catch(NullPointerException e) {
			System.out.println("Error: Container object cannot be a Null!");
			e.printStackTrace();
		}

	}

	@Override
	/**@brief Loads container from the storage to the truck
	 * Steps involved in loading:
	 * 1. Truck's destination city is used to look up for a container 
	 * in the dockyard's storage
	 * 2. If storage is not empty, container is removed from the storage
	 * and loaded to the truck
	 * @param truck ITruck object
	 * @return true if loading successful, false otherwise
	 */
	public boolean loadTruck(ITruck truck) {
		
		String destination = truck.destinationCity();
		if(hasDestinationCity(destination)) {
			Queue<IContainer> containers = this.containersByCity.get(destination);
			if(containers.size() > 0) {
				truck.addContainer(containers.remove());
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**@brief Keep tracks of container count in the dockyard
	 * @return totalCount Total number of containers in the dockyard
	 */
	@Override
	public int containerCount() {
		int totalCount = 0;
		
		for(String city : this.containersByCity.keySet()) {
			totalCount += containerCount(city);
		}
		return totalCount;
	}

	/**
	 * @brief Keeps tracks of container count by city
	 * @param destinationCity truck's destination city
	 * @return Number of containers in the storage for a given city
	 */
	@Override
	public int containerCount(String destinationCity) {
		if(hasDestinationCity(destinationCity)) {
			return this.containersByCity.get(destinationCity).size();
		}
		else {
			return 0;
		}
	}

	/**
	 * @brief Displays details of the dockyard - Total container count and
	 * container count by city
	 */
	@Override
	public void printDetails() {
		System.out.println("The dockyard contains " + containerCount() + " containers.");
		if(this.containersByCity.size() > 0) {
			for(String city : this.containersByCity.keySet()) {
				System.out.printf("\t %6s: %d\n", city, containerCount(city));
			}
		}
	}
	
	/**
	 * @brief Checks if the storage for a given city exists in the dockyard
	 * @param city destination city
	 * @return true if city exist in the storage, false otherwise
	 */
	private boolean hasDestinationCity(String city) {
		if(this.containersByCity.containsKey(city)) {
			return true;
		}
		else {
			return false;
		}
	}

}
