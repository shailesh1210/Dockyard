package solution;

import java.util.List;

import shipping.IContainer;
import shipping.IDockyard;
import shipping.IShip;
import shipping.ShippingProcessorBase;

public class ShippingProcessor extends ShippingProcessorBase {

	public ShippingProcessor(IDockyard dockyard) {
		super(dockyard);
	}

	@Override
	protected List<IContainer> readManifest(String shipId) {
		return null;
	}

	@Override
	protected void processTruck(String registration, String destination) {

	}

	@Override
	protected IShip processShip(String registration) {
		return null;
	}

}
