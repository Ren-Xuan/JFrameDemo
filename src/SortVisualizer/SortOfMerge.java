package SortVisualizer;

import java.util.Arrays;

import UIDemo.AlgoVisHelper;

public class SortOfMerge extends sortData implements sortable{
	public int left,right;//��������
	public int mergeIndex;//
	private AlgoFrame frame;
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public void setRight(int right) {
		this.right = right;
	}

	public int orderedIndex =-1;//[0...orderedIndex)���������λ��
	public int currentMinIndex=-1;//��ǰ�ҵ�����Сλ��
	public int currenCompareIndex=-1;//��ǰ�Ƚϵ���Сλ��
	public SortOfMerge(int N, int randomBound) {
		super(N, randomBound);
	
	}
	public SortOfMerge(Object[] datas) {
		super(datas);
	}

	@Override
	public void run(AlgoFrame frame) {
		this.frame=frame;
		this.title="����������С���㷨��ѡ������--swap times:"+swapNumber;
		setData(-1,-1, -1);
		System.out.println(Arrays.toString(datas));
		mergeSort(0, datas.length-1);
		setData(0, datas.length-1, datas.length-1);
	}
	private void setData(int left,int right,int mergeIndex) {
		this.left=left;
		this.right=right;
		this.mergeIndex=mergeIndex;
		frame.render(this);
		AlgoVisHelper.pause(AlgoVisualizer.DELAY);
	}
	private void mergeSort(int left,int right) {
		if(left>=right)return;
		
		setData(left, right, -1);
		
		int mid=(left+right)/2;
		mergeSort(left, mid);
		mergeSort(mid+1, right);
		merge(left,mid,right);
	}
	private void merge(int left,int mid ,int right) {
		Object[]aux=Arrays.copyOfRange(datas, left, right+1);
		
		for(int l=left,r=mid+1,i=left;i<=right;i++) {
			aux[i-left]= r==right+1      ? datas[l++]	//�Ұ���Ѿ��������
						:l==mid+1	  ? datas[r++]	//�����Ѿ��������
						:compares(l, r)? datas[r++]	//���ߴ�,�Ұ�߽���
						:			    datas[l++];	//�Ұ�ߴ�,���߽���
			
		}
		for(int i=left;i<=right;i++) {
			datas[i]=aux[i-left];
			setData(left, right, i);
		}
		
		System.out.println(Arrays.toString(aux));
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
