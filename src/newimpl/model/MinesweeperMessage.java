package newimpl.model;

public class MinesweeperMessage {
	public enum ACTIONS {
		NOTIFY, CELL_SET, LOST, WON, FIELD_FULL, FLAG_ON, FLAG_OFF, NEW_FIELD
	};

	ACTIONS action;
	int x;
	int y;
	int neighbourBombs;
	boolean flag;
	boolean open;

	public MinesweeperMessage(ACTIONS action) {
		this(action, 0, 0, 0);
	}

	public MinesweeperMessage(ACTIONS action, int x, int y) {
		this (action, x,y, -1);
	}

	public MinesweeperMessage(ACTIONS action, int x, int y, int neighbourBombs) {
		this.action = action;
		this.x = x;
		this.y = y;
		this.neighbourBombs = neighbourBombs;
	}
	
	public MinesweeperMessage(ACTIONS action, int x, int y, int neighbourBombs, boolean open, boolean flag) {
		this.action = action;
		this.x = x;
		this.y = y;
		this.neighbourBombs = neighbourBombs;
		this.open = open;
		this.flag = flag;
	}

	public ACTIONS getAction() {
		return action;
	}

	public void setAction(ACTIONS action) {
		this.action = action;
	}


	public int getNeighbourBombs() {
		return neighbourBombs;
	}

	public void setNeighbourBombs(int neighbourBombs) {
		this.neighbourBombs = neighbourBombs;
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return String.format("ACTION: %s POS: (%d %d)", action, x, y);
	}
}
