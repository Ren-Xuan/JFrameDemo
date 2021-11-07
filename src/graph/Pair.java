package graph;

public class Pair<K,V> implements Comparable{
	private K key;
	private V value;
	public Pair(K key,V value) {
		this.key=key;
		this.value=value;
	}
	public K getKey() {
		return this.key;
	}
	public V getValue() {
		return this.value;
	}
	public void setKey(K key) {
		this.key=key;
	}
	public void setValue(V value) {
		this.value=value;
	}
	@Override
	public int compareTo(Object o) {
		if(this.key==((Pair<K, V>)o).getKey()&&this.getValue()==((Pair<K, V>)o).getValue())return 0;
		return -1;
	}
	
}
