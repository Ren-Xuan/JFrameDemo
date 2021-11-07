package SortVisualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UIDemo.AlgoVisHelper;

public class AlgoFrameOfInsertionSort extends JFrame implements AlgoFrame{
	private int canvasWidth;
	private int canvasHeight;

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	private sortData data;

	public void render(sortData data) {
		this.data = data;
		this.setTitle(data.getTitle());
		repaint();// 刷新

	}

	public void render(String title,sortData data) {
		this.data = data;
		this.setTitle(title);
		repaint();// 刷新

	}

	public AlgoFrameOfInsertionSort() {
		this("", 1024, 768);
	}

	public AlgoFrameOfInsertionSort(String title, int canvasWidth, int canvasHeight) {
		super(title);
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		AlgoCanvas canvas = new AlgoCanvas();

		this.setContentPane(canvas);
		pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
		System.out.println(this.getPreferredSize());
		System.out.println(canvas.getPreferredSize());
	}

	private class AlgoCanvas extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Graphics2D g2d = (Graphics2D) g;

			// 抗锯齿
			RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.addRenderingHints(hints);
			// 具体绘制

			AlgoVisHelper.setColor(g2d, Color.BLUE);
			int w = 0;
			try {
				w = canvasWidth / data.length();

				for (int i = 0; i < data.length(); i++) {
					// 已排序的为绿色
					if (i < ((SortOfInsertion)data).getOrderedIndex())
						AlgoVisHelper.setColor(g2d, Color.GREEN);
					else
						AlgoVisHelper.setColor(g2d, Color.GRAY);
					if (i == ((SortOfInsertion)data).getCurrentIndex()) {
						AlgoVisHelper.setColor(g2d, Color.BLUE);
					}
					
					AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - (int) data.get(i), w - 1, (int) data.get(i));
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth, canvasHeight);
		}
	}
}
