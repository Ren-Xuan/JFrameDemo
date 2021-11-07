package SortVisualizer;

import UIDemo.AlgoVisHelper;

public class SortOfSelection extends sortData implements sortable{
	public int orderedIndex =-1;//[0...orderedIndex)是已排序的位置
	public int currentMinIndex=-1;//当前找到的最小位置
	public int currenCompareIndex=-1;//当前比较的最小位置
	public SortOfSelection(int N, int randomBound) {
		super(N, randomBound);
	
	}
	public SortOfSelection(Object[] datas) {
		super(datas);
	}

	@Override
	public void run(AlgoFrame frame) {
		this.title="交换次数最小的算法：选择排序--swap times:"+swapNumber;
		setData(0, 	-1, -1,frame);
		for(int i=0;i<datas.length;i++) {
			int minIndex=i;
			setData(i, -1, i,frame);
			for(int j=i+1;j<datas.length;j++) {
				setData(i, j, minIndex,frame);
				if(this.compares(minIndex, j)) {
					minIndex=j;
					setData(i, j, minIndex,frame);
				}
			}
			
			this.swap(i, minIndex);
			swapNumber++;
			this.title="交换次数最小的算法：选择排序--swap times:"+swapNumber;
			//画图
			setData(i+1, -1, -1,frame);
			
		}
		setData(datas.length, -1, -1,frame);
	}
	private void setData(int orderedIndex,int currentCompareIndex,int currentMinIndex,AlgoFrame frame) {
		this.orderedIndex=orderedIndex;
		this.currenCompareIndex=currentCompareIndex;
		this.currentMinIndex=currentMinIndex;
		frame.render(this);
		AlgoVisHelper.pause(AlgoVisualizer.DELAY);
	}
	@Override
	public String getTitle() {
		return title;
	}


	public int getCurrenCompareIndex() {
		return currenCompareIndex;
	}

	public int getCurrentMinIndex() {
		return currentMinIndex;
	}
	
	public int getOrderedIndex() {
		return orderedIndex;
	}
	
}
