package matrix;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Each instance of this class represents a matrix of double-precision elements with a given number of rows and columns.
 * 
 * @immutable
 * 
 * @invar | 0 <= getNbRows()
 * @invar | 0 <= getNbColumns()
 * @invar | getElementsRowMajor() != null
 * @invar | getElementsRowMajor().length == getNbRows() * getNbColumns()
 * @invar | getElementsColumnMajor() != null
 * @invar | getElementsColumnMajor().length == getNbRows() * getNbColumns()
 * @invar | getElementsRowArrays() != null
 * @invar | getElementsRowArrays().length == getNbRows()
 * @invar | Arrays.stream(getElementsRowArrays()).allMatch(rowArray ->
 *        |     rowArray != null && rowArray.length == getNbColumns())
 * @invar | IntStream.range(0, getNbRows()).allMatch(i ->
 *        |     IntStream.range(0, getNbColumns()).allMatch(j ->
 *        |         getElementsRowMajor()[i * getNbColumns() + j] == getElementsRowArrays()[i][j]))
 * @invar | IntStream.range(0, getNbRows()).allMatch(i ->
 *        |     IntStream.range(0, getNbColumns()).allMatch(j ->
 *        |         getElementsColumnMajor()[j * getNbRows() + i] == getElementsRowArrays()[i][j]))
 */
public class Matrix {
	
	/**
	 * @invar | 0 <= nbRows
	 * @invar | 0 <= nbColumns
	 * @invar | elementsRowMajor != null
	 * @invar | elementsRowMajor.length == nbRows * nbColumns
	 */
	private int nbRows;
	private int nbColumns;
	/**
	 * @representationObject
	 */
	private double[] elementsRowMajor;
	
	public int getNbRows() { return nbRows; }
	
	public int getNbColumns() { return nbColumns; }
	
	/**
	 * @creates | result
	 */
	public double[] getElementsRowMajor() { return elementsRowMajor.clone(); }
	
	/**
	 * @creates | result
	 */
	public double[] getElementsColumnMajor() {
		double[] result = new double[nbRows * nbColumns];
		for (int i = 0; i < nbRows; i++)
			for (int j = 0; j < nbColumns; j++)
				result[j * nbRows + i] = elementsRowMajor[i * nbColumns + j];
		return result;
	}
	
	/**
	 * @creates | result, ...result
	 */
	public double[][] getElementsRowArrays() {
		double[][] result = new double[nbRows][];
		for (int i = 0; i < nbRows; i++) {
			result[i] = new double[nbColumns];
			for (int j = 0; j < nbColumns; j++)
				result[i][j] = elementsRowMajor[i * nbColumns + j];
		}
		return result;
	}
	
	/**
	 * @pre | 0 <= nbRows
	 * @pre | 0 <= nbColumns
	 * @pre | elementsRowMajor != null
	 * @pre | elementsRowMajor.length == nbRows * nbColumns
	 * 
	 * @post | getNbRows() == nbRows
	 * @post | getNbColumns() == nbColumns
	 * @post | Arrays.equals(getElementsRowMajor(), elementsRowMajor)
	 */
	public Matrix(int nbRows, int nbColumns, double[] elementsRowMajor) {
		this.nbRows = nbRows;
		this.nbColumns = nbColumns;
		this.elementsRowMajor = elementsRowMajor.clone();
	}
	
	/**
	 * @creates | result
	 * @post | result != null
	 * @post | result.getNbRows() == getNbRows()
	 * @post | result.getNbColumns() == getNbColumns()
	 * @post | IntStream.range(0, getNbRows() * getNbColumns()).allMatch(i ->
	 *       |     result.getElementsRowMajor()[i] == getElementsRowMajor()[i] * factor)
	 */
	public Matrix scaled(double factor) {
		double[] resultElements = new double[nbRows * nbColumns];
		for (int i = 0; i < nbRows * nbColumns; i++)
			resultElements[i] = elementsRowMajor[i] * factor;
		return new Matrix(nbRows, nbColumns, resultElements);
	}
	
	/**
	 * @pre | other != null
	 * @pre | other.getNbRows() == getNbRows()
	 * @pre | other.getNbColumns() == getNbColumns()
	 * 
	 * @post | result != null
	 * @post | result.getNbRows() == getNbRows()
	 * @post | result.getNbColumns() == getNbColumns()
	 * @post | IntStream.range(0, getNbRows() * getNbColumns()).allMatch(i ->
	 *       |    result.getElementsRowMajor()[i] == getElementsRowMajor()[i] + other.getElementsRowMajor()[i])
	 */
	public Matrix plus(Matrix other) {
		double[] resultElements = new double[nbRows * nbColumns];
		for (int i = 0; i < nbRows * nbColumns; i++)
			resultElements[i] = elementsRowMajor[i] + other.elementsRowMajor[i];
		return new Matrix(nbRows, nbColumns, resultElements);
	}

}
