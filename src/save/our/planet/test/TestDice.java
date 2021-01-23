package save.our.planet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.Dice;

class TestDice {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	/**
	 * Test to ensure dice totals always fall between 1 and 12
	 */
	@Test
	void testDiceRollMethod() {
		int num = Dice.rollDice();
		for (int loop = 0; loop < 100; loop++) {
			if (num > 0 && num < 13) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
	}
	
	@Test
	void testDiceDefaultConstructor() {
		Dice dice = new Dice();
		assertNotNull(dice);
	}

}
