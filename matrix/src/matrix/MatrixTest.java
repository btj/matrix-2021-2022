package matrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatrixTest {

	@Test
	void test() {
		double[] elementsRowMajor = {
				1, 0,
				0, 1,
				0, 0
		};
		Matrix matrix = new Matrix(3, 2, elementsRowMajor);
		assertEquals(3, matrix.getNbRows());
		assertEquals(2, matrix.getNbColumns());
		assertArrayEquals(elementsRowMajor, matrix.getElementsRowMajor());
		double[] elementsColumnMajor = {
				1, 0, 0,
				0, 1, 0
		};
		assertArrayEquals(elementsColumnMajor, matrix.getElementsColumnMajor());
		double[][] elementsRowArraysExpected = {
				new double[] {1, 0},
				new double[] {0, 1},
				new double[] {0, 0}
		};
		double[][] elementsRowArraysActual = matrix.getElementsRowArrays();
		assertNotNull(elementsRowArraysActual);
		assertEquals(3, elementsRowArraysActual.length);
		for (int i = 0; i < 3; i++)
			assertArrayEquals(elementsRowArraysExpected[i], elementsRowArraysActual[i]);
		
		double[] elementsScaledRowMajor = {
				2, 0,
				0, 2,
				0, 0
		};
		assertArrayEquals(elementsScaledRowMajor, matrix.scaled(2).getElementsRowMajor());
		assertArrayEquals(elementsScaledRowMajor, matrix.plus(matrix).getElementsRowMajor());
	}

}
