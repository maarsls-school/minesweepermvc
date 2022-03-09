package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class Field {
	protected BooleanProperty open;

	private boolean flag = false;
	// protected boolean open = false;

	public boolean isFlag() {
		return flag;
	}

	public boolean isOpen() {
		if (open != null)
			return open.get();
		return false;
	}

	public final void setOpen(boolean open) {
		this.openProperty().set(open);
	}

	public final BooleanProperty openProperty() {
		if (open == null) {
			open = new SimpleBooleanProperty(false);
		}
		return open;
	}

	@Override
	public String toString() {
		return "Field [flag=" + isFlag() + ", open=" + open + "]";
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
