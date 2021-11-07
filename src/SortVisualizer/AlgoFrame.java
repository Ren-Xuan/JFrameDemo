package SortVisualizer;

public interface AlgoFrame {
	public int getCanvasWidth();

	public int getCanvasHeight();

	
	public void render(sortData data);

	public void render(String title,sortData data);
}
