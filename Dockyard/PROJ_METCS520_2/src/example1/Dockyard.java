package example1;

import shipping.IContainer;
import shipping.IDockyard;
import shipping.ITruck;

public class Dockyard implements IDockyard {

	@Override
	public void addContainer(IContainer container) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean loadTruck(ITruck truck) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int containerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int containerCount(String destinationCity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printDetails() {
		// TODO Auto-generated method stub

	}

}
