package SortVisualizer;

import java.util.Arrays;

public abstract class sortData implements sortable{
	protected Object[]datas;
	public String title;
	public static int swapNumber=0;
	public sortData(int N,int randomBound) {
		datas=new Object[N];
		for(int i=0;i<N;i++) {
			datas[i]=(int)(Math.random()*randomBound)+1;
		}
	}
	public sortData(Object[]datas) {
		this.datas=datas;
	}
	public int N() {
		return datas.length;
	}
	public int length() {
		return datas.length;
	}
	public Object get(int index) {
		if(index<0||index>=datas.length)
			throw new IllegalArgumentException("index"+index+"out of boundary!");
		return datas[index];
	}
	
	public boolean compares(int i,int j) {//greater
		return (int)datas[i]>(int)datas[j];
	}
	public void swap(int i,int j) {
		if(i<0||i>=datas.length)
			throw new IllegalArgumentException("i"+i+"out of boundary!");
		if(j<0||j>=datas.length)
			throw new IllegalArgumentException("j"+j+"out of boundary!");
		Object tmp=datas[i];
		datas[i]=datas[j];
		datas[j]=tmp;
	}
	
	
}
