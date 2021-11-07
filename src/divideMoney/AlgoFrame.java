package divideMoney;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
	
	private int[]money;
	public void render(int[]money) {
		this.money=money;
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
//		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
		
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
			
			AlgoVisHelper.setColor(g2d, Color.BLUE);
			int w=canvasWidth/money.length;
			for(int i=0;i<money.length;i++) {
				if(money[i]>0){	
					AlgoVisHelper.setColor(g2d, Color.BLUE);
					AlgoVisHelper.fillRectangle(g2d,
							i*w+1,canvasHeight/2-money[i],w-1,money[i]);
				}else if(money[i]<0){
					AlgoVisHelper.setColor(g2d, Color.RED);
					AlgoVisHelper.fillRectangle(g2d,
							i*w+1,canvasHeight/2,w-1,-money[i]);
				}
			}
			
			
			
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth,canvasHeight);
		}
	}
}
