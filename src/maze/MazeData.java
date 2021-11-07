package maze;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import UIDemo.AlgoVisHelper;


public class MazeData {
	public static final char ROAD=' ';
	public static final char WALL='#';
	
	private int M,N;
	private char[][]maze;
	
	private int entranceX,entranceY;
	private int exitX,exitY;
	private static final int d[][]= {{-1,0},{0,1},{1,0},{0,-1}};
	private boolean[][] isVisited;
	private boolean[][] isGenerated;
	private boolean[][] path;
	private boolean[][] inMist;
	private String title="";
	private boolean isRandom=false;//random graph
	public static int lenth=0; 
	public MazeData(String filename){
		if(filename ==null) {
			throw new IllegalArgumentException("file cant be null");
		}
		Scanner scanner =null;
		try {
			File file =new File(filename);
			if(!file.exists()) {
				throw new IllegalArgumentException("file doent exit!");
			}
		
			FileInputStream fis=new FileInputStream(file);
			scanner =new Scanner(new BufferedInputStream(fis),"UTF-8");
			String mnline=scanner.nextLine();
			String[] mn=mnline.trim().split("\\s+");
			M=Integer.parseInt(mn[0]);
			N=Integer.parseInt(mn[1]);
			maze=new char[M][N];
			isVisited=new boolean[M][N];
			isGenerated=new boolean[M][N];
			path=new boolean[M][N];
			for(int i=0;i<M;i++) {
				String line=scanner.nextLine();
				if(line.length()!=N) {
					throw new IllegalArgumentException("N:"+N+"!=line.lenth");
				}
				for(int j=0;j<N;j++) {
					maze[i][j]=line.charAt(j);
				}
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("file doent exit!");
		}finally {
			if(scanner!=null) {
				scanner.close();
			}
		}
		
		entranceX=1;
		entranceY=0;
		exitX=M-2;
		exitY=N-1;
		isRandom=false;
	}
	public MazeData(int M, int N){

        if( M%2 == 0 || N%2 == 0)
            throw new IllegalArgumentException("Our Maze Generalization Algorihtm requires the width and height of the maze are odd numbers");

        this.M = M;
        this.N = N;

        maze = new char[M][N];
        isVisited = new boolean[M][N];
        isGenerated=new boolean[M][N];
        inMist = new boolean[M][N];
        path=new boolean[M][N];
        for(int i = 0 ; i < M ; i ++)
            for(int j = 0 ; j < N ; j ++){
                if(i%2 == 1 && j%2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
                path[i][j]=false;
                isVisited[i][j] = false;
                isGenerated[i][j]=false;
                inMist[i][j] = true;
            }

        entranceX = 1;
        entranceY = 0;
        exitX = M - 2;
        exitY = N - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
        isRandom=true;
        System.out.println("end of generator");
    }
	public boolean inArea(int x,int y) {
		return 0<=x&&0<=y&&x<this.M&&y<this.N;
	}
	public int getEntranceX() {return entranceX;}
	public int getEntranceY() {return entranceY;}
	public int getExitX() {return exitX;}
	public int getExitY() {return exitY;}
	public void openMist(int x, int y){
	        if(!inArea(x, y))
	            throw new IllegalArgumentException("x or y is out of index in openMist function!");

	        for(int i = x-1 ; i <= x+1 ; i ++)
	            for(int j = y-1 ; j <= y+1 ; j++)
	                if(inArea(i,j))
	                    inMist[i][j] = false;

	        return;
	}

	public int N() {return this.N;}
	public int M() {return this.M;}
	public String getTitle() {
		return title;
	}
	public boolean inMist(int x,int y) {
		return inMist[x][y];
	}
	public boolean path(int x,int y) {
		return path[x][y];
	}
	public char getMaze(int x,int y) {
		if(!inArea(x, y))return '#';
		char tmp=' ';
		try {
			tmp=maze[x][y];
		} catch (Exception e) {
			System.err.println("illegal x or y");
		}
		return tmp;
	}
	public void runDFS(AlgoFrame frame) {
		setData(frame);
		if(!go(this.getEntranceX(),this.getEntranceY(),frame)) {
			JOptionPane.showMessageDialog(null, "迷宫无解");
		}
		setData(frame);
	}

	private boolean go(int x, int y, AlgoFrame frame) {
	
		this.isVisited[x][y] = true;
		this.path[x][y] = true;
		lenth++;
		title="step:"+lenth;
		setData(frame);
		if (x == this.getExitX() && y == this.getExitY()) {
			return true;
		}
		for (int i = 0; i < 4; i++) {
			int newX = x + d[i][0];
			int newY = y + d[i][1];
			
			if (inArea(newX, newY) && getMaze(newX, newY) == MazeData.ROAD && !isVisited[newX][newY]) {
				
				if(go(newX, newY, frame))return true;
			}
		}
		path[x][y] = false;
		setData(frame);
		return false;

	}
	public void runBFS(AlgoFrame frame) {
		setData(frame);
		LinkedList<position> queue =new LinkedList();
		
		position entrance=new position(getEntranceX(), getEntranceY());
		queue.push(entrance);
		isVisited[entrance.getX()][entrance.getY()]=true;
		boolean isSoloved=false;
		while (!queue.isEmpty()) {
			position curPos=queue.pop();
			this.path[curPos.getX()][curPos.getY()] = true;
			setData(frame);
			if(curPos.getX()==getExitX()&&curPos.getY()==getExitY()) {
				isSoloved=true;
//				findPath(curPos);
				break;
			}
			for(int i=0;i<4;i++) {
				int newX=curPos.getX()+d[i][0];
				int newY=curPos.getY()+d[i][1];
				if(inArea(newX, newY)
					&&!isVisited[newX][newY]
					&&getMaze(newX, newY)==MazeData.ROAD) {
					queue.push(new position(newX, newY,curPos));
					isVisited[newX][newY]=true;
				}
			}
			
		}
		if(!isSoloved) {
			JOptionPane.showMessageDialog(null, "迷宫无解");
		}
		
		
	}
	 public void initialize(AlgoFrame frame) throws IOException{
		 	if(!isRandom) {
		 		JOptionPane.showMessageDialog(null, "cannot generate ramdom path,path have areadly been setted");
		 	}
	        setData(frame,-1, -1);

	        RandomQueue<position> queue = new RandomQueue<position>();
	        position first = new position(getEntranceX(), getEntranceY()+1);
	        queue.add(first);
	        isGenerated[first.getX()][first.getY()] = true;
	        openMist(first.getX(), first.getY());

	        while(queue.size() != 0){
	            position curPos = queue.remove();

	            for(int i = 0 ; i < 4  ; i ++){
	                int newX = curPos.getX() + d[i][0]*2;
	                int newY = curPos.getY() + d[i][1]*2;

	                if(inArea(newX, newY)
	                        && !isGenerated[newX][newY]
	                        && maze[newX][newY] == MazeData.ROAD){
	                    queue.add(new position(newX, newY));
	                    isGenerated[newX][newY] = true;
	                    openMist(newX, newY);
	                    setData(frame,curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
	                }
	            }
	        }

	        setData(frame,-1, -1);
	        File file=new File(".\\src\\maze\\map");
	        file.delete(); 
	        file.createNewFile();
	        
	        OutputStream os = new FileOutputStream(".\\src\\maze\\map",true);
			PrintWriter pw=new PrintWriter(os);
			pw.println(M+" "+N);
			for (int i = 0; i < M; i++) {
				for(int j=0;j<N;j++) {
					pw.print(maze[i][j]);
				}
				pw.println();
			}
			System.out.println("new map is saved");
			pw.close();
			os.close();
	    }


	public void setData(AlgoFrame frame) {
		frame.render(this);
		AlgoVisHelper.pause(AlgoVisualizer.DELAY);
	}
	private void setData(AlgoFrame frame,int x, int y){
        if(inArea(x, y))
            maze[x][y] = MazeData.ROAD;
        frame.render(this);
        AlgoVisHelper.pause(AlgoVisualizer.DELAY);
    }
	
	
}
