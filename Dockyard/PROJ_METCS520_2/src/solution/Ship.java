package solution;

import java.util.List;
import java.util.LinkedList;

import shipping.IContainer;
import shipping.IShip;

public class Ship implements IShip {
	private String registration;
	private List<IContainer> containers;
	
	public Ship() {
		this.registration = null;
		this.containers = new LinkedList<IContainer>();
	}
	
	public Ship(String id) {
		setRegistration(id);
		this.containers = new LinkedList<IContainer>();
	}

	@Override
	public String getRegistration() {
		return this.registration;
	}

	@Override
	public void setRegistration(String id) {
		this.registration = id;
	}

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

	@Override
	public List<IContainer> containers() {
		return this.containers;
	}

	@Override
	public List<IContainer> offload() {
		List<IContainer> dockyardContainers = new LinkedList<IContainer>();
		for(IContainer container : containers) {
			dockyardContainers.add(new Container(container.id(), container.destinationCity()));
			
		}
		
		removeContainers();
		
		return dockyardContainers;
	}
	
	public int containerCount() {
		return this.containers.size();
	}
	
	public void removeContainers() {
		this.containers.clear();
	}

	@Override
	public void printDetails() {
		System.out.println("Ship " + getRegistration() + " has " + containerCount() + " containers.");

	}

}
