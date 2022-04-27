package strategies;

public interface PlayStrategy {
	
	public boolean set(int x, int y);
	public void flagging(int x, int y);
	public boolean won();
}
