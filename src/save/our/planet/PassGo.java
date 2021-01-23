/**
 * 
 */
package save.our.planet;

import java.text.DecimalFormat;

/**
 * Class for the 'Pass Go' square on the board
 * 
 * @author emmanash
 *
 */
public class PassGo extends Square {
	
	// constants
	private static final double PASS_GO = 40;
	private static DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Default constructor
	 */
	public PassGo() {
	
	}

	/**
	 * Constructor with args
	 * 
	 * @param squareName
	 * @param passgo
	 */
	public PassGo(String squareName, Groups passgo) {
		super(squareName, passgo);
	}
	
	/**
	 * Method to add £40 to a players balance when they land on or pass go
	 * 
	 * @param player
	 */
	public static void passGo(Player player) {
		
		System.out.println("You have passed Stormont! Collect £" + df.format(PASS_GO) + " from Arlene's heating initiative");
		
		player.addToBalance(PASS_GO);
		
		System.out.println("\nYour new balance is £" + df.format(player.getPlayerBalance()) + "\n");
	}
	

}
