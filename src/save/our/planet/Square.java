package save.our.planet;

/**
 * Class to create object of square for save our planet 
 * 
 * @author emmanash
 *
 */
public class Square {
	
	/**
	 * Name of square
	 */
	private String squareName;
	
	/**
	 * Group square belongs to
	 */
	private Groups squareGroupName;

	/**
	 * Default constructor
	 */
	public Square() {
		
	}

	/**
	 * Constructor with args
	 * 
	 * @param squareName
	 * @param squareGroupName
	 */
	public Square(String squareName, Groups squareGroupName) {
		this.squareName = squareName;
		this.squareGroupName = squareGroupName;
	}

	/**
	 * @return the squareName
	 */
	public String getSquareName() {
		return squareName;
	}

	/**
	 * @param squareName the squareName to set
	 */
	public void setSquareName(String squareName) {
		this.squareName = squareName;
	}

	/**
	 * @return the squareGroupName
	 */
	public Groups getSquareGroupName() {
		return squareGroupName;
	}

	/**
	 * @see Groups
	 * @param squareGroupName the squareGroupName to set
	 */
	public void setSquareGroupName(Groups squareGroupName) {
		this.squareGroupName = squareGroupName;
	}
	
	
	
	

}
