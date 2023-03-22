package domain;

import java.util.ArrayList;
import java.util.List;

public class Vector {
	private List<Integer> vector;
	
	public Vector() {
		this.vector= new ArrayList<Integer>();;
	}
	
	public List<Integer> getVector(){
		return this.vector;
	}
	
	public int getPosition(int index) {
		return this.vector.get(index);
	}
	
	public void add(int num) {
		vector.add(num);
	}
	
	@Override
	public String toString() {
		String str = "Vector: ";
		for(int pos:vector) {
			str += pos + ",";
		}
		str = (String) str.subSequence(0, str.length()-1);
		return str;
	}
}
