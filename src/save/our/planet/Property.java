/**
 * 
 */
package save.our.planet;

/**
 * Class to create object of property for save our planet 
 * 
 * @author emmanash
 *
 */
public class Property extends Square {
	
	/**
	 * Cost of the property square must be more than £1
	 */
	private double propertyPrice;
	
	/**
	 * Cost of each standard development
	 */
	private double priceDev;
	
	/**
	 * Cost of a major development
	 */
	private double priceMajDev;
	
	/**
	 * Owner of the property square
	 */
	private Player propertyOwner;
	
	/**
	 * Rent base cost for undeveloped property must be more than £1
	 */
	private double rentBaseCost;
	
	/**
	 * Is there a major development on the property
	 */
	private boolean majDev;
	
	/**
	 * Number of standard developments on the property
	 */
	private int numberOfDevs;


	/**
	 * Default constructor
	 */
	public Property() {
		
	}

	/**
	 * Constructor with args
	 * 
	 * @param squareName
	 * @param squareGroupName
	 * @param propertyPrice - must be more than £1
	 * @param priceDev
	 * @param propertyOwner
	 * @param rentBaseCost - must be more than £1
	 * @param majDev
	 */
	public Property(String squareName, Groups squareGroupName, double propertyPrice, Player propertyOwner, double rentBaseCost, boolean majDev) {
		super(squareName, squareGroupName);
		this.setPropertyPrice(propertyPrice);
		this.setPriceDev(priceDev);
		this.propertyOwner = propertyOwner;
		this.setRentBaseCost(rentBaseCost);
		this.setPriceMajDev(priceMajDev);
		this.majDev = majDev;
	}

	/**
	 * @return the propertyPrice
	 */
	public double getPropertyPrice() {
		return propertyPrice;
	}

	/**
	 * 
	 * @param propertyPrice - must be more than £1
	 * @throws IllegalArgumentException
	 */
	public void setPropertyPrice(double propertyPrice) throws IllegalArgumentException {
		if (propertyPrice >= 1) {
			this.propertyPrice = propertyPrice;
		} else {
			throw new IllegalArgumentException("Property price cannot be less that £1.00");
		}
		
	}

	/**
	 * @return the priceDev
	 */
	public double getPriceDev() {
		return priceDev;
	}

	/**
	 * @param priceDev
	 */
	public void setPriceDev(double priceDev) {
		this.priceDev = propertyPrice/3;
	}

	/**
	 * @return the priceMajDev
	 */
	public double getPriceMajDev() {
		return priceMajDev;
	}

	/**
	 * @param priceMajDev the priceMajDev to set
	 */
	public void setPriceMajDev(double priceMajDev) {
		this.priceMajDev = propertyPrice/2;
	}

	/**
	 * @return the propertyOwner
	 */
	public Player getPropertyOwner() {
		return propertyOwner;
	}

	/**
	 * @param propertyOwner the propertyOwner to set
	 */
	public void setPropertyOwner(Player propertyOwner) {
		this.propertyOwner = propertyOwner;
	}

	/**
	 * @return the rentBaseCost
	 */
	public double getRentBaseCost() {
		return rentBaseCost;
	}

	/**
	 * 
	 * @param rentBaseCost
	 * @throws IllegalArgumentException
	 */
	public void setRentBaseCost(double rentBaseCost) throws IllegalArgumentException {
		if (rentBaseCost >= 1) {
			this.rentBaseCost = rentBaseCost;
		} else {
			throw new IllegalArgumentException("Base rent cannot be less that £1.00");
		}
	}

	/**
	 * @return the majDev
	 */
	public boolean isMajDev() {
		return majDev;
	}

	/**
	 * @param majDev the majDev to set
	 */
	public void setMajDev(boolean majDev) {
		this.majDev = majDev;
	}
	
	/**
	 * @return the numberOfDevs
	 */
	public int getNumberOfDevs() {
		return numberOfDevs;
	}

	/**
	 * @param numberOfDevs the numberOfDevs to set
	 */
	public void setNumberOfDevs(int numberOfDevs) {
		this.numberOfDevs = numberOfDevs;
	}

	
	
	

}
