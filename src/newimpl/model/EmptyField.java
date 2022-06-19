package newimpl.model;

public class EmptyField extends Field {
	private int bombsCnt;

	public EmptyField(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}
	
	public EmptyField(int bombsCnt, boolean open, boolean flag) {
		this.bombsCnt = bombsCnt;
		super.open = open;
		super.flag = flag;
	}

	public EmptyField() {
	}
	public EmptyField(boolean open) {
		super.open = open;
	}

	public int getBombsCnt() {
		return bombsCnt;
	}

	public void setBombsCnt(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}

	@Override
	public String toString() {
		if (bombsCnt == 0)
			return "_" + super.toString();
		return bombsCnt + super.toString();
	}
}
