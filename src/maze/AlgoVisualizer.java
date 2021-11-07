package maze;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

public class AlgoVisualizer {
	public static int blockSide=8;
	public static int DELAY=10;
	private MazeData data;
	private AlgoFrame frame;

	public AlgoVisualizer(String mazeFile,String className) {
		//初始化数据
		data=new MazeData(101,101);
		//初始化视图
		int sceneHeigh=data.M()*blockSide;
		int sceneWidth=data.M()*blockSide;
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrame("",sceneWidth,sceneHeigh);
			new Thread(()-> {
				try {
					data.initialize(frame);
					data.runDFS(frame);
				} catch (IOException e) {			
					e.printStackTrace();
				}
		
			}
			).start();
			
		});
	}
	

	
}
