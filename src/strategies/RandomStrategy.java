package strategies;

import application.MineField;
import application.MineMessage;
import logic.BombField;
import logic.EmptyField;
import logic.Field;

public class RandomStrategy implements PlayStrategy {
	private Field[][] matrix;
	private MineField field;

	public RandomStrategy(MineField field) {
		super();
		this.matrix = field.getMatrix();
		this.field = field;
	}

	@Override
	public boolean set(int x, int y) {
		int xr = (int) Math.floor(Math.random() * (field.getWidth() + 1));
		int yr = (int) Math.floor(Math.random() * (field.getHeight() + 1));
		matrix[yr][xr].setOpen(true);

		if (matrix[yr][xr] instanceof BombField) {
			return false;
		}
		field.openNeighbour(xr, yr);
		return true;
	}

	@Override
	public void flagging(int x, int y) {
		// TODO Auto-generated method stub
		matrix[y][x].setFlag(!matrix[y][x].isFlag());
	}

	@Override
	public boolean won() {
		int flags = 0;
		for (int i = 0; i < field.getHeight(); i++) {
			for (int h = 0; h < field.getWidth(); h++) {
				if (matrix[i][h] instanceof BombField) {
					if (matrix[i][h].isFlag()) {
						flags++;
					}
				}
			}
		}

		if (flags == field.getBombs()) {
			return true;
		}
		return false;
	}

}
