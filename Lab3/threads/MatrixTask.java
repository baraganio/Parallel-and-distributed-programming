package threads;

import java.util.ArrayList;
import java.util.List;

import model.Matrix;
import model.Pair;
import utils.Utils;

public abstract class MatrixTask extends Thread{
	public List<Pair<Integer,Integer>> pairs;
	public final int iStart, jStart, sizeOfTask;
	public final Matrix a, b, result;
	
	public MatrixTask(int iStart, int jStart, int sizeOfTask, Matrix a, Matrix b, Matrix result) {
		pairs = new ArrayList<>();
		this.iStart=iStart;
		this.jStart=jStart;
		this.sizeOfTask=sizeOfTask;
		this.a=a;
		this.b=b;
		this.result=result;
		compute();
	}
	
	@Override
	public void run() {
		// Go over each pair calculating the result
		for(Pair<Integer,Integer> pair : pairs) {
			int i= pair.first;
			int j= pair.second;
			result.setValue(i, j, Utils.buildElement(a,b,i,j));
			
		}
	}
	
	public abstract void compute();
}
