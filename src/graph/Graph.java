package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	int [][]matrix;
	List vertices;
	List [] adjcentLists;
	
	public Graph(int size) {
		matrix=new int[size][size];
		vertices=new ArrayList<>();
		adjcentLists=new List[size];
		for(int i=0;i<size;i++) {
			adjcentLists[i]=new ArrayList<>();
		}
	}
	public void addVertices(int index) {
		vertices.add(index);
		
	}
	
}
