package model;

import java.util.Random;

public class Matrix {
	private int[][] matrix;
	private int rows, columns;
	
	public Matrix(int rows,int columns) {
		this.rows=rows;
		this.columns=columns;
		matrix = new int[rows][columns];
	}
	
	public int getRows() {
		return matrix.length;
	}
	
	public int getColumns() {
		return matrix[0].length;
	}
	
	public int getValue(int row,int column) {
		return matrix[row][column];
	}
	
	public void setValue(int row,int column,int value) {
		matrix[row][column]=value;
	}
	
	public void printMatrix() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public void fillMatrix() {
		Random r = new Random();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				matrix[i][j] = r.nextInt(100);
			}
		}
	}
}
