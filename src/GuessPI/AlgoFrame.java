package GuessPI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UIDemo.AlgoVisHelper;


public class AlgoFrame extends JFrame {
	private int canvasWidth;
	private int canvasHeight;
	
	public int getCanvasWidth() {
		return canvasWidth;
	}
	public int getCanvasHeight() {
		return canvasHeight;
	}
	
	private MoteCarloPiData moteCarloPiData;
	public void render(MoteCarloPiData moteCarloPiData) {
		this.moteCarloPiData=moteCarloPiData;
		this.setTitle(moteCarloPiData.getResultStr());
		repaint();//刷新
		
	}
	
	public AlgoFrame() {
		this("",1024,768);
	}
	public AlgoFrame(String title,int canvasWidth,int canvasHeight) {
		super(title);
		this.canvasWidth=canvasWidth;
		this.canvasHeight=canvasHeight;
		
		AlgoCanvas canvas=new AlgoCanvas();

		this.setContentPane(canvas);
		pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
		System.out.println(this.getPreferredSize());
		System.out.println(canvas.getPreferredSize());
	}
	
	
	
	private class AlgoCanvas extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Graphics2D g2d=(Graphics2D)g;
			
			//抗锯齿
			RenderingHints hints=new RenderingHints(RenderingHints.KEY_ANTIALIASING
					, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.addRenderingHints(hints);
			//具体绘制
			Circle circle=moteCarloPiData.getCircle();
			
			AlgoVisHelper.setStrokeWidth(g2d, 3);
			AlgoVisHelper.setColor(g2d, Color.BLUE);
			AlgoVisHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());
			
			for(int i=0;i<moteCarloPiData.getPointsNumber();i++) {
				Point p=moteCarloPiData.getPoint(i);
				if(circle.contain(p))AlgoVisHelper.setColor(g2d, Color.RED);
				else AlgoVisHelper.setColor(g2d, Color.green);
				AlgoVisHelper.fillCircle(g2d, p.x, p.y, 3);
			}
			
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth,canvasHeight);
		}
	}
}
