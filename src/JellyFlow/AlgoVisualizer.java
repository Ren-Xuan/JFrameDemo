package JellyFlow;

import java.io.IOException;

import javax.swing.JOptionPane;


public class AlgoVisualizer {
	private static int blockSide=80;
	private static int DELAY=5;
	private GameData data;
	private AlgoFrame frame;
	
	public AlgoVisualizer(String filename) {
		data=new GameData(filename);
		
		int sceneWidth=data.getLenth()*blockSide;
		int sceneHeight=data.getDepth()*blockSide;
		java.awt.EventQueue.invokeLater(()->{
			frame=new AlgoFrame("",sceneWidth,sceneHeight);
			new Thread(()-> {
				run();
			}
			).start();
			
		});
	}
	public void run() {
		setData();
		if(!data.solve()) {
			JOptionPane.showMessageDialog(null, "there is no solution");
		}
	}
	public void setData() {
		frame.render(data);
	}

}
