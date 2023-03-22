import model.Matrix;
import runner.NormalThreadRunner;
import runner.PoolThreadRunner;

public class Main {
	
	private static final int NUM_THREADS = 4;
	private static final String APPROACH = "Pool";
	private static final String TYPE = "Row";

	public static void main(String[] args) {
		// Create Matrix 'a'
		Matrix a = new Matrix(4,4);
		a.fillMatrix();
		a.printMatrix();
		System.out.println("\n"); 
		
		// Create Matrix 'b'
		Matrix b = new Matrix(4,4);
		b.fillMatrix();
		b.printMatrix();
		
		Matrix result = null;
		
		if(a.getColumns()==b.getRows()) {
			result = new Matrix(a.getColumns(),b.getRows());
			if(APPROACH.equals("Normal")) {
				NormalThreadRunner.run(a, b, result, NUM_THREADS, TYPE);
			}else if(APPROACH.equals("Pool")) {
				PoolThreadRunner.run(a,b,result, NUM_THREADS, TYPE);
			}
			System.out.println("\n");
			result.printMatrix();
		}else {
			System.out.println("Matrix 'a' can't be multiplied by matrix 'b'");
		}
		
	}

}
