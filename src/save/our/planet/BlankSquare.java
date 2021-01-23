/**
 * 
 */
package save.our.planet;

/**
 * Class for the blank square on board
 * 
 * @author emmanash
 *
 */
public class BlankSquare extends Square {

	/**
	 * Default constructor
	 */
	public BlankSquare() {
		
	}

	/**
	 * Constructor with args
	 * 
	 * @param squareName
	 * @param squareGroupName
	 */
	public BlankSquare(String squareName, Groups passgo) {
		super(squareName, passgo);
	}

}
