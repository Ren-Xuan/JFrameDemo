package maze;

public class position{
	private int x,y;
	private position prev;
	public position(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public position(int x,int y,position prev) {
		this.x=x;
		this.y=y;
		this.prev=prev;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public position getPrev() {
		return prev;
	}
}