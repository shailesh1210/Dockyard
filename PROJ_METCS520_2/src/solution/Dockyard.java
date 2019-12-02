package solution;

import shipping.IContainer;
import shipping.IDockyard;
import shipping.ITruck;

import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;

public class Dockyard implements IDockyard {
	private HashMap<String, Queue <IContainer>> containersByCity;
	
	public Dockyard(){
		containersByCity = new HashMap<String, Queue<IContainer>>();
	}

	@Override
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

	@Override
	public int containerCount() {
		int totalCount = 0;
		
		for(String city : this.containersByCity.keySet()) {
			totalCount += containerCount(city);
		}
		return totalCount;
	}

	@Override
	public int containerCount(String destinationCity) {
		if(this.containersByCity.containsKey(destinationCity)) {
			return this.containersByCity.get(destinationCity).size();
		}
		else {
			return 0;
		}
	}

	@Override
	public void printDetails() {
		System.out.println("The dockyard contains " + containerCount() + " containers.");
		if(this.containersByCity.size() > 0) {
			for(String city : this.containersByCity.keySet()) {
				System.out.println("\t" + city +  ": " + containerCount(city));
			}
		}
	}
	
	private boolean hasDestinationCity(String city) {
		if(this.containersByCity.containsKey(city)) {
			return true;
		}
		else {
			return false;
		}
	}

}
