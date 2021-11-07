package JellyFlow;

public class Board {
	public static final char SPACE='.';
	private int depth;
	private int lenth;
	private char [][]data;
	private Board preBoard;
	private String swapString="";
	public Board(String[]lines) {
		if(lines==null)
			throw new IllegalArgumentException("lines cannot be mull");
		depth=lines.length;
		if(depth==0)
			throw new IllegalArgumentException("lines lenth cannot be 0");
		
		lenth=lines[0].length();
		data=new char[depth][lenth];
		for(int i=0;i<depth;i++) {
			if(lines[i].length()!=lenth)
				throw new IllegalArgumentException("irregular lines lenth");
			for(int j=0;j<lenth;j++) {
				data[i][j]=lines[i].charAt(j);
			}
		}
	}
	public Board(Board board,Board preBoard,String swaString) {
		if(board==null)
			throw new IllegalArgumentException("lines cannot be mull");
		this.lenth=board.getLenth();
		this.depth=board.getDepth();
		this.data=new char[depth][lenth];
		for(int i=0;i<depth;i++) {
			for(int j=0;j<lenth;j++) {
				this.data[i][j]=board.data[i][j];
			}
		}
		this.preBoard=preBoard;
		this.swapString=swaString;
	}
	public Board(Board board) {
		this(board, null,"");
	}
	public int getDepth() {
		return depth;
	}
	public int getLenth() {
		return lenth;
	}
	public char[][] getDatas() {
		return data;
	}
	public char getData(int x,int y) {
		if(!inArea(x, y))
			throw new IllegalArgumentException("x.y"+"("+x+","+y+")out of area");
		return data[x][y];
	}
	public boolean inArea(int x,int y) {
		return x>=0&&y>=0&&x<depth&&y<lenth;
	}
	public String toString() {
		String result="";
		for(int i=0;i<depth;i++) {
			result+=String.valueOf(data[i])+"\n";
		}
		return result;
	}
	public boolean success() {
		for(int i=0;i<depth;i++) {
			for(int j=0;j<lenth;j++) {
				if(data[i][j]!=SPACE)return false;
			}
		}
		printSwapInfo();
		return true;
	}
	public void swap(int x,int y,int newX,int newY) {
		if(!inArea(newX, newY)||!inArea(x, y))
			throw new IllegalArgumentException("error x or y");
		char t=data[x][y];
		data[x][y]=data[newX][newY];
		data[newX][newY]=t;
	}
	public void run() {
		//clear & drop
		do {
			drop();
		} while (clear());
	}
	private static final int d[][]= {{0,1},{1,0}};
	private boolean clear() {
		boolean isCleared=false;
		boolean tag[][]=new boolean[depth][lenth];
		for(int x=0;x<depth;x++) {
			for(int y=0;y<lenth;y++) {
				if(data[x][y]!=SPACE) {
					for(int i=0;i<2;i++) {
						int newX1=x+d[i][0];
						int newY1=y+d[i][1];
						int newX2=newX1+d[i][0];
						int newY2=newY1+d[i][1];
						if(inArea(newX2, newY2)&&inArea(newX1, newY1)
								&&data[newX1][newY1]==data[x][y]
								&&data[newX2][newY2]==data[x][y]) {
							tag[x][y]=true;
							tag[newX1][newY1]=true;
							tag[newX2][newY2]=true;
							isCleared=true;
						}
						
					}
				}
			}
		}
		for(int x=0;x<depth;x++) {
			for(int y=0;y<lenth;y++) {
				if(tag[x][y])
					data[x][y]=SPACE;
			}
		}
		return isCleared;
	}
	public void drop() {
		for(int j=0;j<getLenth();j++) {
			int cur =getDepth()-1;
			for(int i=cur;i>=0;i--) {
				if(data[i][j]!=SPACE) {
					swap(cur, j, i, j);
					cur--;
				}
			}
		}
	}
	public void printSwapInfo() {
		if(preBoard!=null)
			preBoard.printSwapInfo();
		System.out.println(swapString);
		return;
	}
}
