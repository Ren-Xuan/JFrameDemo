package JellyFlow;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;



public class GameData {
	private int maxTurn;
	private Board startBoard;
	private Board showBoard;
	private String title="";
	public GameData(String filename) {
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
			String turnline=scanner.nextLine();
			this.maxTurn=Integer.parseInt(turnline);
			ArrayList<String> lines=new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line=scanner.nextLine();
				lines.add(line);
			}
			startBoard=new Board(lines.toArray(new String[lines.size()]));
			System.out.println(startBoard);
			
			showBoard=new Board(startBoard);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(scanner!=null) {
				scanner.close();
			}
		}
	}
	public int getDepth() {
		return startBoard.getDepth();
	}
	public int getLenth() {
		return startBoard.getLenth();
		}
	public char[][] getDatas() {
		return startBoard.getDatas();
	}
	public String getTitle() {
		return title;
	}
	public Board getShowBoard() {
		return showBoard;
	}
	public boolean solve() {
		System.out.println(maxTurn);
		if(maxTurn<0)
			return false;
		return solve(startBoard,maxTurn);
	}
	public boolean inArea(int x,int y) {
		return showBoard.inArea(x, y);
	}
	private static final int d[][]= {{1,0},{0,1},{0,-1}};
	private boolean solve(Board board,int turn) {
		
		if(board==null||turn<0)
			throw new IllegalArgumentException("illegal arguments in solve");
		if(turn==0)
			return board.success();
		if(board.success())
			return true;
		//移动可能进行遍历
		for(int x=0;x<getDepth();x++) {
			for(int y=0;y<getLenth();y++) {
				if(board.getData(x, y)!=Board.SPACE) {
					for(int k=0;k<3;k++) {
						int newX=x+d[k][0];
						int newY=y+d[k][1];
						if(inArea(newX, newY)) {
							String swapString=String.format("swap(%d,%d) and (%d,%d)", x,y,newX,newY);
							Board nextboard=new Board(board,board,swapString);
							nextboard.swap(x,y,newX,newY);
							nextboard.run();
							if(solve(nextboard,turn-1))return true;
						}
					}
				}
			}
		}
		return false;
	}
}
