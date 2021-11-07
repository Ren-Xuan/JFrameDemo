package GuessPI;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

import UIDemo.AlgoVisHelper;

public class AlgoVisualizer {
	private static int DELAY=20;
	
	private MoteCarloPiData moteCarloPiData;
	private int N;
	private int L;
	private AlgoFrame frame;
	public AlgoVisualizer(int sceneWidth,int sceneHeigh,int N) {
		L=sceneWidth>sceneHeigh?sceneHeigh:sceneWidth;
		Circle circle=new Circle(L/2, L/2, L/2);
		moteCarloPiData=new MoteCarloPiData(circle);
		this.N=N;
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrame("Pi with Monte Carlo",L,L);
			new Thread(()-> {
				
				run();
			}
			).start();
			
		});
	}
	

	
	
	private void run() {
		double pi = 0;
		for (int i = 0; i < N; i++) {
			if (i % 100 == 0) {
				
				frame.render(moteCarloPiData);
				AlgoVisHelper.pause(DELAY);

				System.out.println(moteCarloPiData.estimatePi());
				// ´òµã
			}
			int x = (int) (Math.random() * L);
			int y = (int) (Math.random() * L);

			Point p = new Point(x, y);
			moteCarloPiData.addPoint(p);
			
		}
		
	}
}
