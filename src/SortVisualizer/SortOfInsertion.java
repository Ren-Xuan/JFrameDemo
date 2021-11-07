package SortVisualizer;

import UIDemo.AlgoVisHelper;

public class SortOfInsertion extends sortData{
	public int orderedIndex=-1;
	public int currentIndex=-1;
		
	public int getOrderedIndex() {
		return orderedIndex;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public SortOfInsertion(int N, int randomBound) {
		super(N, randomBound);
	
	}
	public SortOfInsertion(Object []datas) {
		super(datas);
	}
	
	
	public void run(AlgoFrame frame) {
		
		setData(0, -1,frame);
		for(int i=0;i<datas.length;i++) {
			setData(i, i, frame);
			for(int j=i;    j>0  &&   (int)datas[j]<(int)datas[j-1]  ;j--) {
				swap(j, j-1);
				swapNumber++;
				super.title="处理近乎有序的数据的最优排序算法( 近乎O(n) ):插入排序--swap times:"+swapNumber;
				setData(i+1, j-1,frame);
			}
		}
		setData(datas.length, -1,frame);
	}
	private void setData(int orderedIndex,int currentIndex,AlgoFrame frame) {
		this.orderedIndex=orderedIndex;
		this.currentIndex=currentIndex;
		frame.render(this);
		AlgoVisHelper.pause(AlgoVisualizer.DELAY);
	}
	
	@Override
	public void swap(int i, int j) {
		//算法种不适用swap，因为swap代价很大，不适合频繁交换的插入排序
		super.swap(i, j);
	}
	@Override
	public String getTitle() {
		return title;
	}
	
	
	
}
