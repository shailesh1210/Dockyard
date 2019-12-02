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

public class ShippingProcessor extends ShippingProcessorBase {

	public ShippingProcessor(IDockyard dockyard) {
		super(dockyard);
	}

	@Override
	protected List<IContainer> readManifest(String shipId) {
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
	protected void processTruck(String registration, String destination) {
		ITruck truck = new Truck(registration, destination);
		
		System.out.printf("Before loading: ");
		truck.printDetails();
		
		this.getDockyard().loadTruck(truck);
		
		System.out.printf("After loading: %s", " ");
		truck.printDetails();
	}

	@Override
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
	
	private IContainer processContainer(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input, ",");
		
		String containerId = tokenizer.nextToken().trim();
		String destinationCity = tokenizer.nextToken().trim();
		
		
		return new Container(containerId, destinationCity);
	}

}
