package utils;

import model.Matrix;
import threads.*;

public final class Utils {
	
	public static int buildElement(Matrix a, Matrix b, int i, int j) {
		if(i<a.getColumns() && j<b.getRows()) {
			int element = 0;
			// Calculate the value of an element of the result matrix
			for(int k=0;k<a.getRows();k++) {
				element += a.getValue(i, k) * b.getValue(k, j);
			}
			return element;
		}
		// Something is wrong
		return -10000;
	}
	
	public static MatrixTask initRowThread(int index, Matrix a, Matrix b, Matrix result, int numThreads) {
		// Calculate the total amount of data to be processed
		int resultSize = result.getColumns() * result.getRows();
		// Calculate the total amount of data each thread will process
		int unitsPerPartition = resultSize / numThreads;
		// Calculate in which row is the initial index
		int iStart = unitsPerPartition * index / result.getColumns();
		// Calculate in which column is the initial index
		int jStart = unitsPerPartition * index % result.getColumns();
		
		// If we are in the last thread, the remaining data will be added to its work
		if(index == numThreads -1)
			unitsPerPartition += resultSize % numThreads;
		
		return new RowTask(iStart,jStart,unitsPerPartition,a,b,result);
	}
	
	public static MatrixTask initColThread(int index, Matrix a, Matrix b, Matrix result, int numThreads) {
		// Calculate the total amount of data to be processed
		int resultSize = result.getColumns() * result.getRows();
		// Calculate the total amount of data each thread will process
        int count = resultSize / numThreads;
        // Calculate in which row is the initial index
        int iStart = count * index % result.getColumns();
     // Calculate in which column is the initial index
        int jStart = count * index / result.getColumns();

        // If we are in the last thread, the remaining data will be added to its work
        if (index == numThreads - 1)
            count += resultSize % numThreads;

        return new ColumnTask(iStart, jStart, count, a, b, result);
    }
}
