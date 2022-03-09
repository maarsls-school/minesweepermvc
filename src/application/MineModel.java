package application;

import java.util.Observable;

public class MineModel extends Observable {

	MineField field;
	
	int width;
	int height;
	int bombs;

	public MineModel(int width, int height, int bombs) {
		this.field = new MineField(width, height, bombs);
		this.width = width;
		this.height = height;
		this.bombs = bombs;
	}


	public void set(int width, int height) {
		boolean ok = field.set(width, height);
		if (ok) {
			setChanged();
			notifyObservers(new MineMessage(MineMessage.ACTIONS.CELL_SET, width, height));
		} else {
			setChanged();
			notifyObservers(new MineMessage(MineMessage.ACTIONS.LOST, width, height));
		}

		if (field.won()) {
			setChanged();
			notifyObservers(new MineMessage(MineMessage.ACTIONS.WON));

		}

	}

	public MineModel(MineField field) {
		this.field = field;
	}

}
