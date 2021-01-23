package save.our.planet.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import save.our.planet.Groups;
import save.our.planet.Player;
import save.our.planet.Property;

class PropertyTesting {

	Property p1;
	Player player1;

	double propPriceLow, propPriceMid, propPriceHigh, propPriceInvalid, devPriceLow, devPriceMid, devPriceHigh,
			devPriceInvalid, majDevPriceLow, majDevPriceMid, majDevPriceHigh, majDevPriceInvalid, rentBaseLow,
			rentBaseMid, rentBaseHigh, rentBaseInvalid;

	boolean majDev;

	int numOfDevsLow, numOfDevsMid, numOfDevsHigh, numOfDevsInvalid;

	String playerName, squareName, squareGroupName;

	Groups group;

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		p1 = new Property();
		player1 = new Player();

		propPriceLow = 20;
		propPriceMid = 90;
		propPriceHigh = 150;
		propPriceInvalid = 0;
		devPriceLow = (20 / 3);
		devPriceMid = 30;
		devPriceHigh = 50;
		devPriceInvalid = 0;
		majDevPriceLow = 20 / 2;
		majDevPriceMid = 90 / 2;
		majDevPriceHigh = 150 / 2;
		majDevPriceInvalid = 0;
		rentBaseLow = 20;
		rentBaseMid = 90;
		rentBaseHigh = 150;
		rentBaseInvalid = 0;
		majDev = true;
		numOfDevsLow = 0;
		numOfDevsMid = 2;
		numOfDevsHigh = 3;
		numOfDevsInvalid = -1;
		playerName = "PlayerName";
		squareName = "SqaureName";
		group = Groups.ACTIVISTS;

	}

	/**
	 * 
	 */
	@Test
	void testProperty() {

		assertNotNull(p1);
	}

	/**
	 * 
	 */
	@Test
	void testPropertyStringGroupsDoublePlayerDoubleBoolean() {
		Property p1 = new Property(squareName, group, propPriceMid, player1, rentBaseMid, true);
		assertNotNull(p1);
	}

	/**
	 * 
	 */
	@Test
	void testGetSetPropertyPrice() {
		p1.setPropertyPrice(propPriceLow);
		assertEquals(propPriceLow, p1.getPropertyPrice());

		p1.setPropertyPrice(propPriceMid);
		assertEquals(propPriceMid, p1.getPropertyPrice());

		p1.setPropertyPrice(propPriceHigh);
		assertEquals(propPriceHigh, p1.getPropertyPrice());
	}

	/**
	 * 
	 */
	@Test
	void testPropertyPriceInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setPropertyPrice(propPriceInvalid);
		});
	}

	/**
	 * 
	 */
	@Test
	void testGetSetPriceDev() {
		// p1.setPropertyPrice(propPriceLow);
		// p1.setPriceDev(devPriceLow);
		// assertEquals(6.0, p1.getPriceDev());

		p1.setPropertyPrice(propPriceMid);
		p1.setPriceDev(devPriceMid);
		assertEquals(devPriceMid, p1.getPriceDev());

		p1.setPropertyPrice(propPriceHigh);
		p1.setPriceDev(devPriceHigh);
		assertEquals(devPriceHigh, p1.getPriceDev());

	}

	/**
	 * 
	 */
	@Test
	void testPriceDevInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setPriceDev(devPriceInvalid);
		});
	}

	/**
	 * 
	 */
	@Test
	void testGetSetPriceMajDev() {
		p1.setPropertyPrice(propPriceLow);
		p1.setPriceMajDev(majDevPriceLow);
		assertEquals(majDevPriceLow, p1.getPriceMajDev());

		p1.setPropertyPrice(propPriceMid);
		p1.setPriceMajDev(majDevPriceMid);
		assertEquals(majDevPriceMid, p1.getPriceMajDev());

		p1.setPropertyPrice(propPriceHigh);
		p1.setPriceMajDev(majDevPriceHigh);
		assertEquals(majDevPriceHigh, p1.getPriceMajDev());

	}

	/**
	 * 
	 */
	@Test
	void testSetPriceMajDevInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setPriceMajDev(majDevPriceInvalid);
		});
	}

	/**
	 * 
	 */
	@Test
	void testGetSetPropertyOwner() {
		p1.setPropertyOwner(player1);
		assertEquals(player1, p1.getPropertyOwner());
	}

	/**
	 * 
	 */
	@Test
	void testGetSetRentBaseCost() {
		p1.setRentBaseCost(rentBaseLow);
		assertEquals(rentBaseLow, p1.getRentBaseCost());

		p1.setRentBaseCost(rentBaseMid);
		assertEquals(rentBaseMid, p1.getRentBaseCost());

		p1.setRentBaseCost(rentBaseHigh);
		assertEquals(rentBaseHigh, p1.getRentBaseCost());
	}

	/**
	 * 
	 */
	@Test
	void testRentBaseInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setRentBaseCost(rentBaseInvalid);
		});
	}

	/**
	 * 
	 */
	@Test
	void testIsSetMajDev() {
		p1.setMajDev(majDev);
		assertEquals(majDev, p1.isMajDev());
	}

	/**
	 * 
	 */
	@Test
	void testGetSetNumberOfDevs() {
		p1.setNumberOfDevs(numOfDevsLow);
		assertEquals(numOfDevsLow, p1.getNumberOfDevs());

		p1.setNumberOfDevs(numOfDevsMid);
		assertEquals(numOfDevsMid, p1.getNumberOfDevs());

		p1.setNumberOfDevs(numOfDevsHigh);
		assertEquals(numOfDevsHigh, p1.getNumberOfDevs());
	}

	/**
	 * 
	 */
	@Test
	void testNumberOfDevsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			p1.setNumberOfDevs(numOfDevsInvalid);
		});
	}

	/**
	 * 
	 */
	@Test
	void testGetSetSqaureName() {
		p1.setSquareName(squareName);
		assertEquals(squareName, p1.getSquareName());
	}

	/**
	 * 
	 */
	@Test
	void testGetSetSquareGroupName() {
		p1.setSquareGroupName(group);
		assertEquals(group, p1.getSquareGroupName());
	}
}
