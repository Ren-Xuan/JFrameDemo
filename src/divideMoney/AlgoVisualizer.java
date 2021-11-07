package divideMoney;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import UIDemo.AlgoVisHelper;

public class AlgoVisualizer {
	private static int DELAY=20;
	private int money[];
	private AlgoFrame frame;
	public AlgoVisualizer(int sceneWidth,int sceneHeigh,int N) {
		int R=10;
		money=new int[N];
		for (int i = 0; i < money.length; i++) {
			money[i]=100;
		}
		
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrame("",sceneWidth,sceneHeigh);
			new Thread(()-> {
				
				run();
			}
			).start();
			
		});
	}
	

	
	
	private void run() {
		while(true) {
			Arrays.sort(money);
			//绘制数据
			frame.render(money);
			AlgoVisHelper.pause(DELAY);
			//更新数据
			for(int k=0;k<10;k++) {//加速50倍
				for(int i=0;i<money.length;i++) {
//					if(money[i]>0) {
						int someone=(int)(Math.random()*money.length);
						money[i]-=1;
						money[someone]+=1;
//					}
				}
			}
		}
	}
}
