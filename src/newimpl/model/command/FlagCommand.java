package newimpl.model.command;

import newimpl.model.Field;
import newimpl.model.Playground;

public class FlagCommand implements PlayCommandInterface {
	private Playground pg;
	private int x;
	private int y;
	private int action;
	private Field[][] f;
	private Field[][] f2;

	public FlagCommand(Playground pg, int x, int y, int action) {
		this.pg = pg;
		this.x = x;
		this.y = y;
		this.action = action;
		this.f = pg.getMatrixDeepCopy();
		this.f2 = pg.getMatrixDeepCopy();
		
	}

	@Override
	public void doIt() {
		System.out.println("Prev");
		Playground.toStringByField(f);
		System.out.println("2");
		Playground.toStringByField(f2);
		if (action == 0) { // flagging command
			pg.flagging(x, y);
		} else {
			pg.play(x, y, Playground.ACTIONS.CLICK);
		}

	}

	@Override
	public void undo() {
		System.out.println("Imachundoundsetzt neue matrix");
		Playground.toStringByField(f);
		System.out.println("2");
		Playground.toStringByField(f2);
		pg.setMatrix(f);
	}
}
