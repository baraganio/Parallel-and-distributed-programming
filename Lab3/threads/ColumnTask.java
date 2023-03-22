package threads;

import model.Matrix;
import model.Pair;

public class ColumnTask extends MatrixTask{
	
	public ColumnTask(int iStart, int jStart, int count, Matrix a, Matrix b, Matrix result) {
        super(iStart, jStart, count, a, b, result);

    }

    public void compute() {
        int i = iStart, j = jStart;
        // Number of data to be processed
        int size = sizeOfTask;
        while (size > 0 &&  i < result.getColumns() && j < result.getRows()) {
            pairs.add(new Pair<>(i, j));
            i++;
            size--;
            // We have finished with a column and need to skip to the next
            if (i == result.getRows()) {
                i = 0;
                j++;
            }
        }
    }
}
