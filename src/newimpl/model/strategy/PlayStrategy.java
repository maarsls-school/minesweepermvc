package newimpl.model.strategy;

import newimpl.model.Playground;

public interface PlayStrategy {
	boolean set(Playground p, int x, int y);
	boolean won();
}
