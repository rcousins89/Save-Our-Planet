package save.our.planet;



/**
 * Class to create object of player for save our planet 
 * @author Ruairi
 *
 */
public class Player {
	
	// setting constants
	private final static int PLAYER_NAME_CHARACTERS = 1;
	private final static int PLAYER_POSITION_LOW = 1;
	private final static int PLAYER_POSITION_HIGH = 12;
	
	/**
	 * must be greater than or equal to one character
	 */
	private String playerName;
	
	/**
	 * must be between 1 and 12
	 */
	private int playerPosition;
	
	/**
	 * player's balance
	 */
	private double playerBalance;
	
	
	/**
	 * default constructor
	 */
	public Player() {
		
	}
	
	
	/**
	 * Constructor with Arguments 
	 * @param playerName
	 * @param playerPosition
	 * @param startingBalance
	 */
	public Player(String playerName, int playerPosition, double startingBalance) {
		
		this.setPlayerName(playerName);
		this.setPlayerPosition(playerPosition);		
		this.playerBalance = startingBalance;
	}


	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}


	/**
	 * @return the playerPosition
	 */
	public int getPlayerPosition() {
		return playerPosition;
	}


	/**
	 * @return the playerBalance
	 */
	public double getPlayerBalance() {
		return playerBalance;
	}


	/**
	 * @param playerName the playerName to set -  Must be greater than or equal to one character - throw exception
	 */
	public void setPlayerName(String playerName) throws IllegalArgumentException {
		if(playerName.trim().length()>=PLAYER_NAME_CHARACTERS) {
			this.playerName = playerName;
		} else {
			throw new IllegalArgumentException("Please enter a valid name");
		}
		
	}


	/**
	 * @param playerPosition the playerPosition to set - must be from 1 to 12
	 */
	public void setPlayerPosition(int playerPosition) throws IllegalArgumentException {
		if(playerPosition>=PLAYER_POSITION_LOW && playerPosition<=PLAYER_POSITION_HIGH) {
			this.playerPosition = playerPosition;
		} else {
			throw new IllegalArgumentException("Position on board must be between 1 and 12");
		}
		
	}


	/**
	 * @param the playerBalance to set
	 */
	public void setPlayerBalance(double d) {//set rule if <=0 bankrupt and end game?
		this.playerBalance = d;
	}
	
	
	/**
	 * Method to add to balance of the player
	 * @param amount
	 */
	public void addToBalance(double amount) {
		setPlayerBalance(getPlayerBalance()+amount);
	}
	
	
	/**
	 * Method to subtract from balance of the player
	 * @param amount
	 */
	public void removeFromBalance(double amount) {
        setPlayerBalance(getPlayerBalance()-amount);
    }

}
