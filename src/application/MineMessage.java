package application;

public class MineMessage {
	enum ACTIONS {
		NOTIFY, CELL_SET, LOST, WON, FIELD_FULL, FLAG
	};

	ACTIONS action;
	int width;
	int height;
	int neighbourBombs;

	public MineMessage(ACTIONS action) {
		this(action, 0, 0, 0);
	}

	public MineMessage(ACTIONS action, int width, int height) {
		this.action = action;
		this.width = width;
		this.height = height;
	}

	public MineMessage(ACTIONS action, int width, int height, int neighbourBombs) {
		this.action = action;
		this.width = width;
		this.height = height;
		this.neighbourBombs = neighbourBombs;
	}

	public ACTIONS getAction() {
		return action;
	}

	public void setAction(ACTIONS action) {
		this.action = action;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


}
