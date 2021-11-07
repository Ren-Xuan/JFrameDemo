package UIDemo;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
	private Circle circles[];
	private AlgoFrame frame;
	public AlgoVisualizer(int sceneWidth,int sceneHeigh,int N) {
		int R=10;
		circles=new Circle[N];
		for (int i = 0; i < circles.length; i++) {
			int x=(int)(Math.random()*(sceneWidth-2*R))+R;
			int y=(int)(Math.random()*(sceneHeigh-2*R))+R;
			int vx=(int)(Math.random()*11)-5;//-5��5�������
			int vy=(int)(Math.random()*11)-5;
			circles[i]=new Circle(x, y, R, vx, vy);
		}
		
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrame("",sceneWidth,sceneHeigh);
			new Thread(()-> {
				frame.addMouseListener(new AlgoMouseListener());
				run();
			}
			).start();
			
		});
	}
	
	private class AlgoMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent event) {
			event.translatePoint(-(frame.getBounds().width-frame.getCanvasWidth())+8, 
					-(frame.getBounds().height - frame.getCanvasHeight())+8
					);//��Ҳ��֪��Ϊʲô+8�͸պ�
			System.out.println(event.getPoint());
			for(Circle circle:circles) {
				if(circle.contain(event.getPoint())) {
					circle.isFilled=!circle.isFilled;
					
				}
			}
		}
	}
	private class AlgoKeyListener extends KeyAdapter{
		
	}
	
	
	private void run() {
		while(true) {
			//��������
			frame.render(circles);
			AlgoVisHelper.pause(20);
			//��������
			for(Circle circle:circles) {
				circle.move(0,0,frame.getCanvasWidth(),frame.getCanvasHeight());
			}
		}
	}
}
