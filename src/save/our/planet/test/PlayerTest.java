package save.our.planet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.Player;

class PlayerTest {

	Player p1;

	String validName, boundaryValidName, invalidTrim;
	int playerPositionValid, playerPositionBoundaryLow, playerPositionBoundaryHigh;
	int playerPositionInvalidLow, playerPositionInvalidHigh;
	int playerBalance;

	@BeforeEach
	void setUp() throws Exception {

		p1 = new Player();

		validName = "ValidName";
		boundaryValidName = "r";
		invalidTrim = " ";
		playerPositionValid = 7;
		playerPositionBoundaryLow = 1;
		playerPositionBoundaryHigh = 12;
		playerPositionInvalidLow = 0;
		playerPositionInvalidHigh = 13;
		playerBalance = 2000;
	}

	/**
	 * test default constructor
	 */
	@Test
	void testPlayer() {

		Player p = new Player();

		assertNotNull(p);
	}

	/**
	 * constructor with arguments valid data
	 */
	@Test
	void testPlayerConstructorArgsValid() {
		Player p1 = new Player(validName, playerPositionValid, playerBalance);

		assertEquals(validName, p1.getPlayerName());
		assertEquals(playerPositionValid, p1.getPlayerPosition());
		assertEquals(playerBalance, p1.getPlayerBalance());

	}

	/**
	 * constructor with arguments invalid data
	 */
	@Test
	void testPlayerConstructorArgsInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			Player p2 = new Player(invalidTrim, playerPositionValid, playerBalance);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			Player p2 = new Player(validName, playerPositionInvalidLow, playerBalance);
		});

	}

	@Test
	void testSetPlayerName() {

		p1.setPlayerName(validName);

		assertEquals(validName, p1.getPlayerName());

		p1.setPlayerName(boundaryValidName);

		assertEquals(boundaryValidName, p1.getPlayerName());

	}

	@Test
	void testSetPlayerNameInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {

			p1.setPlayerName(invalidTrim);

		});

	}

	@Test
	void testSetPlayerPositionValid() {

		p1.setPlayerPosition(playerPositionValid);
		assertEquals(playerPositionValid, p1.getPlayerPosition());

		p1.setPlayerPosition(playerPositionBoundaryLow);
		assertEquals(playerPositionBoundaryLow, p1.getPlayerPosition());

		p1.setPlayerPosition(playerPositionBoundaryHigh);
		assertEquals(playerPositionBoundaryHigh, p1.getPlayerPosition());

	}

	@Test
	void testSetPlayerPositionInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {

			p1.setPlayerPosition(playerPositionInvalidHigh);

		});

		assertThrows(IllegalArgumentException.class, () -> {

			p1.setPlayerPosition(playerPositionInvalidLow);

		});

	}

	@Test
	void testSetPlayerBalance() {

		p1.setPlayerBalance(playerBalance);

		assertEquals(playerBalance, p1.getPlayerBalance());

	}

	@Test
	void testAddToBalance() {

		Player player = new Player(validName, playerPositionValid, playerBalance);

		player.addToBalance(500);

		int expected = 2500;

		assertEquals(expected, player.getPlayerBalance());

	}

	@Test
	void testRemoveFromBalance() {

		Player player = new Player(validName, playerPositionValid, playerBalance);

		player.removeFromBalance(500);

		int expected = 1500;

		assertEquals(expected, player.getPlayerBalance());

	}

}
