package save.our.planet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.Groups;
import save.our.planet.PassGo;
import save.our.planet.Player;

class PassGoTest {
	
	PassGo pg1;
	Player p1;
	
	String squareName;
	int passGo;
	Groups group;
	

	@BeforeEach
	void setUp() throws Exception {
		
		pg1 = new PassGo();
		p1 = new Player();
		
		PassGo.passGo(p1);
		
		
		squareName = "MySquareName";
		passGo = 40;
		
		
		
	}

	/**
	 * default constructor
	 */
	@Test
	void testPassGoDefault() {
		assertNotNull(pg1);
	}
	
	
	/**
	 * constructor with args
	 */
	@Test
	void testPassGoWithArgs() {
		
		PassGo pg2 = new PassGo(squareName, Groups.PASSGO);
		
		assertEquals(squareName, pg2.getSquareName());
		assertEquals(Groups.PASSGO, pg2.getSquareGroupName());
		
	}

	/**
	 * PassGo method
	 */
	@Test
	void testPassGo1() {
		
		
		int expected = 40;
		
		
		
		
		
		
		
		
		
		
		
		
	}



}
