package SortVisualizer;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {
	public static int DELAY=100;
	private sortData datas;
	private AlgoFrame frame;

	public AlgoVisualizer(int sceneWidth,int sceneHeigh,int N,String className) {
		
//		datas=new SelectionSort(N, sceneHeigh);
//		datas=new SortOfInsertion(N, sceneHeigh);
		datas=new SortOfMerge(N, sceneHeigh);
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrameOfMergeSort("",sceneWidth,sceneHeigh);
			new Thread(()-> {

				datas.run(frame);
			}
			).start();
			
		});
	}
	

	
}
