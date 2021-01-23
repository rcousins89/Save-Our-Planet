package save.our.planet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.BlankSquare;
import save.our.planet.Groups;

class BlankSquareTest {
	
	BlankSquare b1;
	
	String squareName;
	Groups group;
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		squareName = "MySquareName";
		
	}

	
	/**
	 * test default constructor
	 */
	@Test
	void testBlankSquareDefaultConstructor() {
		
		b1 = new BlankSquare();
		
		
		assertNotNull(b1);
		
	}

	/**
	 * test constructor with args
	 */
	@Test
	void testBlankSquareConstructorArgs() {
		
		BlankSquare b2 = new BlankSquare(squareName, Groups.ACTIVISTS);
		
		assertEquals(squareName, b2.getSquareName());
		assertEquals(Groups.ACTIVISTS, b2.getSquareGroupName());
		
		
	}

}
