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
	
	public int getNbRows() {}
	
	public int getNbColumns() {}
	
	/**
	 * @creates | result
	 */
	public double[] getElementsRowMajor() {}
	
	/**
	 * @creates | result
	 */
	public double[] getElementsColumnMajor() {}
	
	/**
	 * @creates | result, ...result
	 */
	public double[][] getElementsRowArrays() {}
	
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
		
	}
	
	/**
	 * @creates | result
	 * @post | result != null
	 * @post | result.getNbRows() == getNbRows()
	 * @post | result.getNbColumns() == getNbColumns()
	 * @post | IntStream.range(0, getNbRows() * getNbColumns()).allMatch(i ->
	 *       |     result.getElementsRowMajor()[i] == getElementsRowMajor()[i] * factor)
	 */
	public Matrix scaled(double factor) {}
	
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
	public Matrix plus(Matrix other) {}

}
