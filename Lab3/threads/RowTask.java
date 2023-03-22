package threads;

import model.Matrix;
import model.Pair;

public class RowTask extends MatrixTask{
	
	public RowTask(int iStart, int jStart, int count, Matrix a, Matrix b, Matrix result) {
		super(iStart,jStart,count,a,b,result);
	}

	@Override
	public void compute() {
		int i = iStart;
		int j= jStart;
		// Number of data to be processed
		int size = sizeOfTask;
		while (size>0 && i<result.getColumns() && j<result.getRows()) {
			pairs.add(new Pair<>(i,j));
			j++;
			size--;
			// We have finished with a row and need to skip to the next
			if(j==result.getColumns()) {
				j=0;
				i++;
			}
		}
	}
	
}
