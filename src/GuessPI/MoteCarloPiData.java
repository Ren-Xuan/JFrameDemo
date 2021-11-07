package GuessPI;

import java.awt.Point;
import java.util.LinkedList;

public class MoteCarloPiData {
	private Circle circle;
	private LinkedList<Point> points;
	private int insideCircle=0;
	private String result="";
	public MoteCarloPiData(Circle circle) {
		this.circle=circle;
		points=new LinkedList<>();
	}
	public Circle getCircle() {
		return circle;
	}
	public Point getPoint(int i) {
		return points.get(i%points.size());
	}
	public int getPointsNumber() {
		return points.size();
	}
	public void addPoint(Point p) {
		points.add(p);
		if(circle.contain(p)) {
			insideCircle++;
		}
	}
	public double estimatePi() {
		int  circleArea=insideCircle;
		int  sqareArea=points.size();
		return (double)circleArea*4/sqareArea;
	}
	public String getResultStr() {
		return "inside:" + insideCircle + "  outside:" + points.size() + "  pi:" +estimatePi();
	}
}
