package logic;

public class EmptyField extends Field {
	private int bombsCnt;

	public EmptyField() {

	}

	public EmptyField(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}

	public int getBombsCnt() {
		return this.bombsCnt;
	}

	public void setBombsCnt(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + getBombsCnt() + " ";
	}
}
