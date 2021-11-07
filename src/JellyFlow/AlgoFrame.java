package JellyFlow;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	private GameData data;
	public void render(GameData data) {
		this.data=data;
		setTitle(data.getTitle());
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
		private HashMap<Character, Color> colorMap;
		private ArrayList<Color> colorList;
		public AlgoCanvas() {
			colorMap=new HashMap<>();
			colorList=new ArrayList<>();
			colorList.add(Color.GREEN);
			colorList.add(Color.ORANGE);
			colorList.add(Color.RED);
			colorList.add(Color.BLACK);
			colorList.add(Color.BLUE);
			colorList.add(Color.CYAN);
			colorList.add(Color.PINK);
			colorList.add(Color.GRAY);
			
			
			
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Graphics2D g2d=(Graphics2D)g;
			
			//抗锯齿
			RenderingHints hints=new RenderingHints(RenderingHints.KEY_ANTIALIASING
					, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.addRenderingHints(hints);
			//具体绘制
			
			int w=canvasWidth/data.getLenth();
			int h=canvasHeight/data.getDepth();
			Board showbBoard=data.getShowBoard();
			for(int i=0;i<data.getDepth();i++) {
				for(int j=0;j<data.getLenth();j++) {
					char c=showbBoard.getData(i, j);
					if(c!=Board.SPACE) {
						if(!colorMap.containsKey(c)) {
							int size=colorMap.size();
							colorMap.put(c, colorList.get(size));
						}
						Color color=colorMap.get(c);
						AlgoVisHelper.setColor(g2d, color);
						AlgoVisHelper.fillRectangle(g2d, j*h+2, i*w+2, w-4, h-4);
						
						 AlgoVisHelper.setColor(g2d, Color.white);
	                     String text = String.format("( %d , %d )", i, j);
	                     AlgoVisHelper.drawText(g2d, text, j*h + h/2, i*w + w/2);
						
					}
				}
			}
			
			
			
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth,canvasHeight);
		}
	}
}
