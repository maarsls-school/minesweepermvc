package newimpl.model.strategy;

import newimpl.model.BombField;
import newimpl.model.EmptyField;
import newimpl.model.MinesweeperMessage;
import newimpl.model.Playground;

public class NormalStrategy implements PlayStrategy {

	@Override
	public boolean set(Playground p, int x, int y) {
		p.get(y, x).setOpen(true);
		p.notifyObservers(new MinesweeperMessage(MinesweeperMessage.ACTIONS.CELL_SET, x, y,
				p.get(y, x) instanceof EmptyField ? ((EmptyField) p.get(y, x)).getBombsCnt() : -1));
		if (p.get(y, x) instanceof BombField) {
			return false;
		}
		p.openNeighbour(x, y);
		return true;
	}

	@Override
	public boolean won() {
		return false;
	}

}
