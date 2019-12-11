package solution;

import java.util.List;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import shipping.IContainer;
import shipping.IDockyard;
import shipping.IShip;
import shipping.ITruck;
import shipping.ShippingProcessorBase;

/**
 * @brief This class handles two events - Shipping event and Trucking event - 
 * associated with the Dockyard simulation.
 * Event 1 - Shipping event: handled by processShip method.
 * - A ship containing a load (containers) arrives at the dockyard
 * - Containers are unloaded and arranged by destination city in the dockyard
 * 
 * Event 2 - Trucking event: handled by processTruck method.
 * - A Truck arrives at the dockyard to pick the container
 * - A container is loaded to the truck based on its destination city
 * 
 * @author shailesh
 */
public class ShippingProcessor extends ShippingProcessorBase {

	/**
	 * @brief A class constructor : Call its superclass constructor and
	 * initializes the dockyard
	 * @param dockyard object of IDockyard
	 */
	public ShippingProcessor(IDockyard dockyard) {
		super(dockyard);
	}
	
	@Override
	/**@brief Reads the manifest of the ship by is Id. It contains the 
	 * list of containers that is unloaded in the dockyard
	 * @param shipId Registration number of the ship
	 * @return list of containers in the ship
	 */
	protected List<IContainer> readManifest(String shipId) {
		//A linked-list to store containers in the ship
		List<IContainer> shipContainers = new LinkedList<IContainer>();
		
		String filename = shipId + "-Manifest";
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader(filename);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Error: " + filename + " not found!");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fileReader);
		try {
			String input = reader.readLine();
			while (input != null)
			{
				shipContainers.add(processContainer(input));
				input = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return shipContainers;
	}

	@Override
	/**
	 * @brief Processes a Truck event. 
	 * Steps:
	 * 1. Create a new Truck object based on registration and destination city
	 * 2. Display Truck's information before loading the container
	 * 3. Load container in the truck from the dockyard
	 * 4. Display Truck's information after loading the container
	 * 
	 * @param registration Truck's registration Id
	 * @param destination Truck's destination city
	 */
	protected void processTruck(String registration, String destination) {
		ITruck truck = new Truck(registration, destination);
		
		System.out.printf("Before loading: ");
		truck.printDetails();
		
		this.getDockyard().loadTruck(truck);
		
		System.out.printf("After loading: %s", " ");
		truck.printDetails();
	}

	@Override
	/**
	 * @brief Process Ship Event.
	 * Steps:
	 * 1. Create a new Ship object
	 * 2. Add containers to the ship from the ship's manifest
	 * 3. Display Ship's information before unloading
	 * 4. Unload containers from the ship to the dockyard
	 * 5. Display Ship's information after unloading
	 * @param registration
	 * @return IShip object
	 */
	protected IShip processShip(String registration) {
		
		IShip ship = new Ship(registration);
		for(IContainer container : readManifest(registration)) {
			ship.addContainer(container);
		}
		
		System.out.printf("Before unloading: ");
		ship.printDetails();
		
		for(IContainer container : ship.offload()) {
			this.getDockyard().addContainer(container);
		}
		
		System.out.printf("After unloading: %s", " ");
		ship.printDetails();
		
		return ship;
	}
	
	/**
	 * @brief Processes an input and creates and IContainer object 
	 * from the ship's manifest. 
	 * @param input String containing information on a given container
	 * @return IContainer object
	 */
	private IContainer processContainer(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input, ",");
		
		String containerId = tokenizer.nextToken().trim();
		String destinationCity = tokenizer.nextToken().trim();
		
		
		return new Container(containerId, destinationCity);
	}

}
