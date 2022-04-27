package strategies;

import application.MineField;
import application.MineMessage;
import logic.BombField;
import logic.EmptyField;
import logic.Field;

public class NormalStrategyAdvanced implements PlayStrategy {
	private Field[][] matrix;
	private MineField field;

	public NormalStrategyAdvanced(MineField field) {
		super();
		this.matrix = field.getMatrix();
		this.field = field;
	}

	@Override
	public boolean set(int x, int y) {
		matrix[y][x].setOpen(true);

		if (matrix[y][x] instanceof BombField) {
			return false;
		}
		field.openNeighbour(x, y);
		openAdditional();
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

	private void openAdditional() {
		int x = (int) Math.floor(Math.random() * (field.getWidth() + 1));
		int y = (int) Math.floor(Math.random() * (field.getHeight() + 1));

		if (matrix[y][x] instanceof EmptyField && !matrix[y][x].isOpen())
			matrix[y][x].setOpen(true);
		else
			openAdditional();

	}

}
