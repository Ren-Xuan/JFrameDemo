package UIDemo;

import java.awt.Point;

public class Circle {
	public int x,y;
	private int r;
	public int vx,vy;//速度
	public boolean isFilled=false;
	public Circle(int x, int y, int r, int vx, int vy) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.vx = vx;
		this.vy = vy;
	}	
	public int getR() {
		return r;
	}
	public void move(int minx,int miny,int maxx,int maxy) {
		x+=vx;
		y+=vy;
		checkCollision(minx, miny, maxx, maxy);
	}
	private void checkCollision(int minx,int miny,int maxx,int maxy) {
		if(x-r<minx)  {x=r;		vx=-vx;}
		if(x+r>=maxx) {x=maxx-r;vx=-vx;}
		if(y-r<miny)  {y=r;		vy=-vy;}
		if(y+r>=maxy) {y=maxy-r;vy=-vy;}
	}
	public boolean contain(Point p) {
		return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)<=r*r;
		//判断点p是否与圆心的距离小于r
	}
}
