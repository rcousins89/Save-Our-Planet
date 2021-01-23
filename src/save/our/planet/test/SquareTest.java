package save.our.planet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.Groups;
import save.our.planet.Square;

class SquareTest {
	
	Square sq1;
	String squareName;
	Groups group;
	

	@BeforeEach
	void setUp() throws Exception {
		
		squareName = "SquareName";
		sq1 = new Square();
	}

	/**
	 * test default constructor
	 */
	@Test
	void testSquare() {
	
		assertNotNull(sq1);
		
	}
	
	
	/**
	 * test constructor with arguments
	 */
	@Test
	void testSquareArgsConstructor() {
		
		Square sq2 = new Square(squareName, Groups.RECYCLING);
		
		assertEquals(squareName, sq2.getSquareName());
		assertEquals(Groups.RECYCLING, sq2.getSquareGroupName());
		
	}

	/**
	 * test set/get square group
	 */
	@Test
	void testSquareGroup() {
		
		sq1.setSquareGroupName(Groups.ACTIVISTS);
		
		assertEquals(Groups.ACTIVISTS, sq1.getSquareGroupName());
		
	}

	/**
	 * test set/get square name
	 */
	@Test
	void testSetSquareName() {
		
		sq1.setSquareName(squareName);
		
		assertEquals(squareName, sq1.getSquareName());
		
	}

	

}
