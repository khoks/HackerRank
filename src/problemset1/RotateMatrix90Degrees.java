package problemset1;

import java.io.File;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.*;

public class RotateMatrix90Degrees {

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			{1,2,3,4}, 
			{5,6,7,8}, 
			{9,10,11,12}, 
			{13,14,15,16}
			};
		
		int[][] rotatedMatrix = new int[4][4];
		
		
		for(int column = 0 ; column < matrix[0].length ; column++) { 
			for(int row = matrix.length - 1 ; row >= 0 ; row--) {
				System.out.println("column - " + column + " row - " + row);
				rotatedMatrix[column][matrix.length-1-row] = matrix[row][column];
			}
		}
		
		/*IntStream.rangeClosed(0, matrix[0].length-1).forEach(column -> {
			Stream.iterate(matrix.length-1, i -> i-1).limit(0).forEach(row -> {
				System.out.println("column - " + column + " row - " + row);
				rotatedMatrix[column][matrix.length-1-row] = matrix[column][row];
			});
		});*/
		System.out.println(rotatedMatrix);
		
		
		
	}

}
