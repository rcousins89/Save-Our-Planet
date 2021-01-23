/**
 * 
 */
package save.our.planet;

import java.util.Random;

/**
 * Class that creates "throwable" dice for determining moves on board
 * 
 * @author alanscott
 *
 */
public class Dice {
	
	// setting constant
	private final static int NUMBER_OF_DICE = 2;

	/**
	 * Default constructor
	 */
	public Dice() {

	}

	/**
	 * Method for simulating dice roll using random number generator, loops twice to
	 * replicate two dice.
	 * 
	 * @return separate dice values and total
	 */
	public static int rollDice() {
		int roll1 = 0;
		Random rand = new Random();
		for (int loop = 0; loop < NUMBER_OF_DICE; loop++) {
			roll1 += rand.nextInt(6) + 1;
		}
		return roll1;

	}

}
