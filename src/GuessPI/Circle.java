package GuessPI;

import java.awt.Point;

public class Circle {
	private int x,y;
	private int r;
	
	public boolean isFilled=false;
	public Circle(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		
	}	
	public int getR() {
		return r;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	
	public boolean contain(Point p) {
		return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)<=r*r;
		//�жϵ�p�Ƿ���Բ�ĵľ���С��r
	}
}
