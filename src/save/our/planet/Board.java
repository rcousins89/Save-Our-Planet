package save.our.planet;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Save Our Planet Board class - manages game play
 * 
 * @author EmmaNash
 * @author RuairiCousins
 * @author AlanScott
 * @author TrevorMcKnight
 *
 */
public class Board {

	// constants
	public static final double INITIAL_BALANCE = 600;
	public static final String PAGE_BREAK = "\n   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n";
	private static final int MIN_PLAYERS = 2;
	private static final int MAX_PLAYERS = 4;
	private final static int PLAYER_POSITION_HIGH = 12;
	private static DecimalFormat df = new DecimalFormat("#.00");

	// end game boolean
	private static boolean endGame = false;

	// create properties
	private static Square p0 = new PassGo("GO!", Groups.PASSGO);
	private static Property p1 = new Property("Gusty Heights", Groups.RENEWABLES, 120, null, 20, false);
	private static Property p2 = new Property("Roaring Rapids", Groups.RENEWABLES, 110, null, 18, false);
	private static Property p3 = new Property("Sunny Acres", Groups.RENEWABLES, 110, null, 18, false);
	private static Property p4 = new Property("Watts Wagons", Groups.TECHNOLOGY, 200, null, 50, false);
	private static Property p5 = new Property("Droning On Ltd.", Groups.TECHNOLOGY, 175, null, 36, false);
	private static Property p6 = new Property("Smashing Glasses", Groups.RECYCLING, 100, null, 16, false);
	private static Property p7 = new Property("Fantastic Plastics", Groups.RECYCLING, 90, null, 14, false);
	private static Property p8 = new Property("Re-smelt It, Re-dealt It", Groups.RECYCLING, 90, null, 14, false);
	private static Property p9 = new Property("The Thunbergade", Groups.ACTIVISTS, 30, null, 2, false);
	private static Property p10 = new Property("That's A Bad Sign Inc.", Groups.ACTIVISTS, 30, null, 8, false);
	private static Square p11 = new BlankSquare("David Attenborough's Wellness Retreat", Groups.BLANK);

	// create property group arrays
	private static Property[] renewables = { p1, p2, p3 };
	private static Property[] technology = { p4, p5 };
	private static Property[] recycling = { p6, p7, p8 };
	private static Property[] activists = { p9, p10 };

	// creating property array
	public static Property[] propArr = { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10 };

	// create board array
	private static Square[] board = { p0, p1, p2, p3, p4, p5, p11, p6, p7, p8, p9, p10 };

	// create an ArrayList to store the players
	private static ArrayList<Player> players = new ArrayList<Player>();

	// creating the scanner for player inputs
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Main method - initiates the game, loops through players turns, determines end
	 * of game conditions
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// calls the setPlayerNames method to create the player objects and
		// adds them to an ArrayList
		players = setPlayerNames(scanner);

		// calls the display welcome method
		displayWelcome(players);

		// game play is contained in a while loop that will continue until
		// the end game boolean is returned as false
		while (!endGame) {

			// the game loops through the player array
			for (Player p : players) {

				// calls the takeTurn method which processes the roll of the dice and returns
				// the
				// player's new position on the virtual board
				Square landedOn = takeTurn(p, scanner, players);

				// calls the processLanding method which offers the player options depending on
				// where they have landed on the board
				processLanding(landedOn, p);

				// checks at the end of each turn if any player has gone bankrupt
				checkForBankruptcy(p, players);
			}
		}
		// closes the scanner
		scanner.close();
	}

	/**
	 * Method to get the endgame boolean
	 * 
	 * @return the endGame
	 */
	public static boolean isEndGame() {
		return endGame;
	}

	/**
	 * Method to set the endgame boolean
	 * 
	 * @param endGame the endGame to set
	 */
	public static void setEndGame(boolean endGame) {
		Board.endGame = endGame;
	}

	/**
	 * Method to display the player's names and their starting balances
	 * 
	 * @param players
	 */
	public static void displayWelcome(ArrayList<Player> players) {

		pageBreak();

		for (int loop = 0; loop < players.size(); loop++) {
			System.out.println("Welcome " + players.get(loop).getPlayerName() + "!");
		}

		System.out.println("\nYour starting balances are £" + df.format(INITIAL_BALANCE));
		System.out.println("Time to start investing and SAVE THE PLANET!");
	}

	/**
	 * Method to create the player objects including their names
	 * 
	 * @param scanner
	 * @return
	 * @throws NumberFormatException if a number is not entered for the number of
	 *                               players
	 * 
	 */
	public static ArrayList<Player> setPlayerNames(Scanner scanner) throws NumberFormatException {

		// outputs the game introduction
		System.out.println("\t\t  Saving Our Planet Starts with You!\n");
		System.out.println("Welcome eco-warriors : we need you start investing in some worthy causes!\n");
		System.out.println("There's no time to spare so get your team together and get ready to...\n");
		String[] welcome = { "\t\t\t", "  S", "A", "V", "E", " ", "O", "U", "R", " ", "P", "L", "A", "N", "E", "T",
				"!\n" };
		for (String w : welcome) {
			System.out.print(w);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pageBreak();
		int numOfPlayers = 0;

		// asks for the number of players (2-4)
		System.out.print("How many players (" + MIN_PLAYERS + "-" + MAX_PLAYERS + ") : ");

		while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
			try {
				numOfPlayers = Integer.parseInt(scanner.nextLine());
				if (numOfPlayers >= MIN_PLAYERS && numOfPlayers <= MAX_PLAYERS) {
					continue;
				} else {
					System.out.println(
							"\nYou need to have " + MIN_PLAYERS + " - " + MAX_PLAYERS + " players please try again : ");
				}
			} catch (NumberFormatException e) {
				System.err.println("Invalid entry...please enter a number");
				System.out.println();
			}
		}

		// creates an ArrayList to store the players
		ArrayList<Player> players = new ArrayList<Player>();

		String playerName;
		System.out.println();

		for (int loop = 0; loop < numOfPlayers; loop++) {
			System.out.print("Enter the name of Player " + (loop + 1) + " : ");

			playerName = scanner.nextLine();

			for (Player name : players) {

				while (name.getPlayerName().equalsIgnoreCase(playerName) || playerName.trim().length() == 0) {
					System.out.println("Sorry you cannot have the same name as another player please re-enter : ");
					playerName = scanner.nextLine();
				}
			}

			Player player = new Player(playerName, 1, INITIAL_BALANCE);
			players.add(player);
		}
		// returns the players ArrayList
		return players;
	}

	/**
	 * Method for the player to take their turn
	 * 
	 * @param player
	 * @param scanner
	 * @param players
	 * @return
	 */
	public static Square takeTurn(Player player, Scanner scanner, ArrayList<Player> players) {

		String option;
		int landed;

		pageBreak();

		// asks the player if they want to take their turn
		System.out.println(player.getPlayerName() + " are you ready to take your turn?");
		System.out.println("\n\tEnter 'Y' - Yes, take my turn!");
		System.out.println("\tEnter 'N' - No, I want to quit!");
		System.out.println("\tEnter 'S' - Let me see how much everyone has and what they own\n");

		option = scanner.nextLine();

		// while loop to handle an invalid input
		while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("s") && !option.equalsIgnoreCase("n")) {
			System.err.println("Not a valid entry - please try again");
			option = scanner.nextLine();
		}

		// while loop for when 'N' is entered
		while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("s")) {

			// statement to confirm the player wishes to quit
			if (option.equalsIgnoreCase("n")) {
				System.out.println("Are you sure you want to quit? \n\t'Y' to quit \n\t'N' to return the the menu");
				option = scanner.nextLine();

				// while loop to handle an invalid input
				while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
					System.err.println("Not a valid entry - please try again");
					option = scanner.nextLine();
				}

				// if the player confirms they want to quit
				if (option.equalsIgnoreCase("y")) {
					System.out.println("You have quit the game - Game over");
					System.out.println(PAGE_BREAK);
					System.out.println("Final Balances");

					for (Player p : players) {
						System.out.println(
								p.getPlayerName() + " - your final balance is £" + df.format(p.getPlayerBalance()));
					}

					determineWinner(players);
					System.exit(0);

					// if the player has chosen not to quit the takeTurn method is re-called for that
					// player
				} else {
					takeTurn(player, scanner, players);
					break;
				}
			}
		}

		// if the player chooses to take their turn a dice roll is activated
		if (option.equalsIgnoreCase("y")) {

			System.out.println("\nAlrighty, lets move!");

			// calls the roll dice method
			int moves = Dice.rollDice();

			pageBreak();

			// outputs to the player the result of their roll
			System.out.println(player.getPlayerName() + " you've rolled a " + moves + "\n");

			// gets the players current position
			int startPos = player.getPlayerPosition();

			// calculates new position on the board
			int newPos = moves + startPos;

			// checks if the player has passed go
			if (newPos > PLAYER_POSITION_HIGH) {
				landed = (newPos - 12);
				player.setPlayerPosition(landed);
				// calls pass go method and adds to players balance
				PassGo.passGo(player);

			} else {
				landed = newPos;
				player.setPlayerPosition(landed);
			}

			// if player enters 'S' - allows the player to see the current game statistics
		} else if (option.equalsIgnoreCase("s")) {
			stats(propArr, player, scanner, players);
		}

		// returns the players position on the board array
		return board[player.getPlayerPosition() - 1];
	}

	/**
	 * Method to allow a player to buy an investment property
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void buyProperty(Square landedOn, Player player) {

		// checks if player can afford to invest in the property
		if (player.getPlayerBalance() > ((Property) landedOn).getPropertyPrice()) {

			System.out.println("\nYour current balance is: £" + df.format(player.getPlayerBalance()));

			System.out.println(PAGE_BREAK);

			System.out.println("   *\t  The cost of a development on this property is: £"
					+ df.format(((Property) landedOn).getPriceDev()) + "\t     *");
			System.out.println("   *   The cost of a major development on this property is: £"
					+ df.format(((Property) landedOn).getPriceMajDev()) + "   *");
			System.out.println("   *\t     The base rent price for this property is: £"
					+ df.format(((Property) landedOn).getRentBaseCost()) + "\t     *");

			System.out.println(PAGE_BREAK);

			System.out.println("Would you like to purchase '" + landedOn.getSquareName() + "' for £"
					+ df.format(((Property) landedOn).getPropertyPrice()) + " 'Y/N?'");

			String purchase = scanner.nextLine();

			// checks if the player wants to purchase the property
			while (!purchase.equalsIgnoreCase("y")) {
				if (purchase.equalsIgnoreCase("n")) {
					break;
				}

				System.out.println("Would you like to purchase '" + landedOn.getSquareName() + "' for £"
						+ df.format(((Property) landedOn).getPropertyPrice()) + " 'Y/N?'");
				purchase = scanner.next();

			}

			// deducts the property price from the player's balance and sets them as the
			// owner of the property
			if (purchase.equalsIgnoreCase("y")) {

				double cost = (((Property) landedOn).getPropertyPrice());

				player.removeFromBalance(cost);

				((Property) landedOn).setPropertyOwner(player);

				System.out.println("\nProperty purchased, your balance is £" + df.format(player.getPlayerBalance()));
				System.out.println(landedOn.getSquareName() + " is now owned by "
						+ ((Property) landedOn).getPropertyOwner().getPlayerName());
			}

			// lets the player know they cannot afford the property if their balance is too
			// low
		} else {
			System.out.println("You cannot afford to invest in this property");
		}
	}

	/**
	 * Method to pay rent to a property owner
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void payRent(Square landedOn, Player player) {

		Player propertyOwner = ((Property) landedOn).getPropertyOwner();

		// lets the player know who's property they have landed on
		System.out.println(((Property) landedOn).getPropertyOwner().getPlayerName() + " owns this property");

		// calculates the rent owed based on the number of developments
		double rentOwed = (((Property) landedOn).getRentBaseCost() + (40 * ((Property) landedOn).getNumberOfDevs()));

		if (((Property) landedOn).isMajDev()) {
			rentOwed += 120;
		}

		// lets the player know how much they owe in rent
		System.out.println(
				"You owe £" + df.format(rentOwed) + " to " + ((Property) landedOn).getPropertyOwner().getPlayerName());

		// calls the removeFromBalance method to deduct the rent from the players
		// balance
		player.removeFromBalance(rentOwed);

		// lets the player know their new balance
		System.out.println("Your new balance is £" + df.format(player.getPlayerBalance()));

		// calls the addToBalance method to add the rent to the property owners balance
		propertyOwner.addToBalance(rentOwed);

		// lets the property owner know their new balance
		System.out.println(
				propertyOwner.getPlayerName() + "'s new balance is £" + df.format(propertyOwner.getPlayerBalance()));
	}

	/**
	 * Method for a player to build a standard development on property
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void buildDev(Square landedOn, Player player) {

		// finds the cost of building a development
		double cost = ((Property) landedOn).getPriceDev();

		// checks if player can afford to build a development
		if (player.getPlayerBalance() > cost) {

			System.out.println(
					"You currently have " + ((Property) landedOn).getNumberOfDevs() + " developments on this property");
			System.out.println("Your current balance is: £" + df.format(player.getPlayerBalance()));

			// asks if they want to build a development
			System.out.println("Would you like to build a Development for £" + df.format(cost) + " : Y/N?");
			String buildDev = scanner.nextLine();

			// checks the player's response
			while (!buildDev.equalsIgnoreCase("y")) {
				if (buildDev.equalsIgnoreCase("n")) {
					System.out.println("No problem - maybe next time!");
					break;
				}
				System.out.println("Would you like to build a Development for £" + df.format(cost) + " : Y/N?");
				buildDev = scanner.nextLine();

			}

			// if the player has chosen to build a development; builds it and adjusts their
			// balance
			if (buildDev.equalsIgnoreCase("y")) {

				((Property) landedOn).setNumberOfDevs((((Property) landedOn).getNumberOfDevs() + 1));
				System.out.println("Congratulations you've built a Development, you now have "
						+ ((Property) landedOn).getNumberOfDevs() + " developments on " + landedOn.getSquareName());

				player.removeFromBalance(cost);

				System.out.println("Your new balance is: £" + df.format(player.getPlayerBalance()));

			}

		} else {
			// advises the player they cannot afford to build a development
			System.out.println("You cannot afford to build a Development");
		}
	}

	/**
	 * Method for a player to build a major development on a property
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void buildMajDev(Square landedOn, Player player) {

		// determines the cost of building a major development
		double cost = ((Property) landedOn).getPriceMajDev();

		// checks if the player can afford a major development
		if (player.getPlayerBalance() > cost) {

			// asks player if they want to build a major development
			System.out.println("Your current balance is: £" + df.format(player.getPlayerBalance()));
			System.out.println("Would you like to build a Major Development for £" + df.format(cost) + " : Y/N?");
			String buildMajDev = scanner.nextLine();

			// checks the player's response
			while (!buildMajDev.equalsIgnoreCase("y")) {
				if (buildMajDev.equalsIgnoreCase("n")) {
					System.out.println("No problem - maybe next time!");
					break;
				}
				System.out.println("Would you like to build a Major Development for £" + df.format(cost) + " : Y/N?");
				buildMajDev = scanner.nextLine();

			}

			// if the player has chosen to build a major development; builds it and adjusts
			// their balance
			if (buildMajDev.equalsIgnoreCase("y")) {
				((Property) landedOn).setMajDev(true);
				;
				System.out.println(
						"Congratulations you've built a Major Development, you have now completed development on "
								+ landedOn.getSquareName());
				player.removeFromBalance(cost);
				System.out.println("Your new balance is: £" + df.format(player.getPlayerBalance()));
			}

		} else {
			// advises the player they cannot afford to build a development
			System.out.println("You cannot afford a Major Development");
		}

	}

	/**
	 * Method to check the number of developments on a property
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void checkNumDevs(Square landedOn, Player player) {

		// check if current number of standard developments is less than 3
		if (((Property) landedOn).getNumberOfDevs() < 3) {

			// calls buildDev method to build a standard development
			buildDev(landedOn, player);

			// checks if all standard developments have been built and that a major
			// development has not been built
		} else if (((Property) landedOn).getNumberOfDevs() == 3 && !((Property) landedOn).isMajDev()) {

			// calls buildMajDev method
			buildMajDev(landedOn, player);

			// if all developments including a major development have been built output to
			// player
		} else if (((Property) landedOn).isMajDev()) {
			System.out.println("You've fully developed your property");
		}
	}

	/**
	 * Method to process what action can be taken by player on the square they have
	 * landed on
	 * 
	 * @param landedOn
	 * @param player
	 */
	public static void processLanding(Square landedOn, Player player) {

		// informs player which square they have landed on
		System.out.print("You've landed on '" + landedOn.getSquareName() + "' ");

		// checks if player has landed on PassGo
		if (landedOn.getSquareGroupName() == Groups.PASSGO) {
			System.out.println("nothing to see here!");

			// checks if player has landed on the blank square
		} else if (landedOn.getSquareGroupName() == Groups.BLANK) {
			System.out.println("\nTake a break from all your saving the world activities...");

			// checks if player has landed on a square that has no owner
		} else if (((Property) landedOn).getPropertyOwner() == null) {

			System.out.print("which is part of the " + landedOn.getSquareGroupName() + " group\n");

			// calls buyProperty method to allow player to buy the property
			buyProperty(landedOn, player);

			// checks if the player already owns the square
		} else if (((Property) landedOn).getPropertyOwner().getPlayerName().equals(player.getPlayerName())) {

			System.out.println("you own this property already");

		} else {
			// calls the payRent method to pay rent to the property owner
			payRent(landedOn, player);

		}
		// checks if the player owns all the activist group and if true calls the
		// develop activists method
		if (checkActivistGroupOwnership(activists, player)) {
			developActivist(player);

		}
		// checks if the player owns all the recycling group and if true calls the
		// develop recycling method
		if (checkRecyclingGroupOwnership(recycling, player)) {
			developRecycling(player);

		}
		// checks if the player owns all the renewables group and if true calls the
		// develop renewables method
		if (checkRenewableGroupOwnership(renewables, player)) {
			developRenewable(player);

		}
		// checks if the player owns all the technology group and if true calls the
		// develop technology method
		if (checkTechnologyGroupOwnership(technology, player)) {
			developTechnology(player);

		}

	}

	/**
	 * Method to check if a player owns all of the 'Activist' group of properties
	 * 
	 * @param activists
	 * @param player
	 * @return allGroupOwner
	 */
	public static boolean checkActivistGroupOwnership(Property[] activists, Player player) {

		boolean allGroupOwner = false;
		int allGroupOwnerCount = 0;

		for (Property property : activists) {

			if (property.getPropertyOwner() == null) {
				allGroupOwner = false;

			} else {

				if (property.getPropertyOwner().getPlayerName().equals(player.getPlayerName())) {
					allGroupOwnerCount += 1;

					if (allGroupOwnerCount == activists.length) {
						allGroupOwner = true;
						return allGroupOwner;
					}
				}
			}
		}
		return allGroupOwner;
	}

	/**
	 * Method to check if a player owns all of the 'Recycling' group of properties
	 * 
	 * @param recycling
	 * @param player
	 * @return allGroupOwner
	 */
	public static boolean checkRecyclingGroupOwnership(Property[] recycling, Player player) {

		boolean allGroupOwner = false;
		int allGroupOwnerCount = 0;

		for (Property property : recycling) {

			if (property.getPropertyOwner() == null) {
				allGroupOwner = false;

			} else {

				if (property.getPropertyOwner().getPlayerName().equals(player.getPlayerName())) {
					allGroupOwnerCount += 1;

					if (allGroupOwnerCount == recycling.length) {
						allGroupOwner = true;
						return allGroupOwner;
					}
				}
			}
		}
		return allGroupOwner;
	}

	/**
	 * Method to check if a player owns all of the 'Renewable' group of properties
	 * 
	 * @param renewables
	 * @param player
	 * @return allGroupOwner
	 */
	public static boolean checkRenewableGroupOwnership(Property[] renewables, Player player) {

		boolean allGroupOwner = false;
		int allGroupOwnerCount = 0;

		for (Property property : renewables) {

			if (property.getPropertyOwner() == null) {
				allGroupOwner = false;

			} else {

				if (property.getPropertyOwner().getPlayerName().equals(player.getPlayerName())) {
					allGroupOwnerCount += 1;

					if (allGroupOwnerCount == renewables.length) {
						allGroupOwner = true;
						return allGroupOwner;
					}
				}
			}
		}
		return allGroupOwner;
	}

	/**
	 * Method to check if a player owns all of the 'Technology' group of properties
	 * 
	 * @param technology
	 * @param player
	 * @return allGroupOwner
	 */
	public static boolean checkTechnologyGroupOwnership(Property[] technology, Player player) {

		boolean allGroupOwner = false;
		int allGroupOwnerCount = 0;

		for (Property property : technology) {

			if (property.getPropertyOwner() == null) {
				allGroupOwner = false;

			} else {

				if (property.getPropertyOwner().getPlayerName().equals(player.getPlayerName())) {
					allGroupOwnerCount += 1;

					if (allGroupOwnerCount == technology.length) {
						allGroupOwner = true;
						return allGroupOwner;
					}
				}
			}
		}
		return allGroupOwner;
	}

	/**
	 * Method to terminate the game
	 * 
	 * @param players
	 */
	public static void terminateGame(ArrayList<Player> players) {

		Board.setEndGame(true);

		System.out.println(PAGE_BREAK);
		System.out.println("\t\t\t\tGame Over");
		System.out.println(PAGE_BREAK);

		Board.displayAllPlayerBalances(players);

		determineWinner(players);

		System.exit(0);

	}

	/**
	 * Method to check if any player has gone bankrupt
	 * 
	 * @param player
	 * @param players
	 */
	public static void checkForBankruptcy(Player player, ArrayList<Player> players) {

		// checks if any player's balance is <=0 and outputs

		if (player.getPlayerBalance() <= 0) {

			System.out.println("Uh oh, " + player.getPlayerName() + " is bankrupt!");

			terminateGame(players);
		}

	}

	/**
	 * Method to determine the winner of game including if there was a tie
	 * 
	 * @param players
	 */
	public static void determineWinner(ArrayList<Player> players) {

		double temp = players.get(0).getPlayerBalance();

		for (int loop = 0; loop < players.size(); loop++) {

			if (players.get(loop).getPlayerBalance() > temp) {
				temp = players.get(loop).getPlayerBalance();
			}
		}
		ArrayList<Player> winners = new ArrayList<Player>();
		for (int loop2 = 0; loop2 < players.size(); loop2++) {

			if (players.get(loop2).getPlayerBalance() == temp) {
				winners.add(players.get(loop2));
			}
		}
		if (winners.size() == 1) {
			System.out.println("The winner is:");
			System.out.println("\n\t** " + winners.get(0).getPlayerName() + " ** ");
			System.out.println(PAGE_BREAK);

		} else if (winners.size() > 1) {
			System.out.println("We have had a tie! The winners are:");
			System.out.print("\n\t");

			for (Player w : winners) {
				System.out.print(" ** " + w.getPlayerName() + " ** ");
			}

			System.out.println(PAGE_BREAK);
		}

	}

	/**
	 * Method to display all the players balances
	 * 
	 * @param players
	 */
	public static void displayAllPlayerBalances(ArrayList<Player> players) {

		for (Player player : players) {

			System.out.println(player.getPlayerName() + "'s balance is: £" + df.format(player.getPlayerBalance()));
			System.out.println();
		}
	}

	/**
	 * Method to show all of the game statistics - calls the
	 * displayAllPlayerBalances and takeTurn methods
	 * 
	 * @param propArr
	 * @param player
	 * @param scanner
	 * @param players
	 */
	public static void stats(Property[] propArr, Player player, Scanner scanner, ArrayList<Player> players) {
		pageBreak();

		System.out.println("\t\t\t  **Player Balances**");
		displayAllPlayerBalances(players);

		System.out.println("\t\t\t**Investments So Far**");
		for (Property property : propArr) {
			if (property.getPropertyOwner() != null) {
				System.out.println(
						property.getSquareName() + " is owned by : " + property.getPropertyOwner().getPlayerName());
			}
		}

		takeTurn(player, scanner, players);
	}

	/**
	 * Method to allow selection of property in Technology group that is to be
	 * developed
	 * 
	 * @param player
	 */
	public static void developTechnology(Player player) {

		System.out.println("\nWould you like to develop a property in the Technology group 'Y/N'?");
		String selection;

		selection = scanner.nextLine();

		// while loop to handle an invalid input
		while (!selection.equalsIgnoreCase("y") && !selection.equalsIgnoreCase("n")) {
			System.err.println("Not a valid entry - please try again");
			selection = scanner.nextLine();
		}

		while (!selection.equalsIgnoreCase("n")) {
			try {
				if (selection.equalsIgnoreCase("y")) {
					System.out
							.println(PAGE_BREAK + "\n\t\t'Technology' group properties for development\n" + PAGE_BREAK);
					System.out.println("Select the number of the property you wish to develop below : \n");
					for (int i = 0; i < technology.length; i++) {
						System.out.print("\t");
						System.out.println(i + 1 + " : " + technology[i].getSquareName());
					}
					System.out.println("\n\tor...");
					System.out.print("\n\t");
					System.out.println(technology.length + 1 + " : leave Technology development");

					int optionSelection = Integer.parseInt(scanner.nextLine());
					switch (optionSelection) {
					case 1:
						checkNumDevs(p4, player);
						break;
					case 2:
						checkNumDevs(p5, player);
						break;
					case 3:
						selection = "n";
						break;
					default:
						System.out.println("Please enter a number between 1 and 3");
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number");
			}
		}
	}

	/**
	 * Method to allow selection of property in Recycling group that is to be
	 * developed
	 * 
	 * @param player
	 */
	public static void developRecycling(Player player) {

		System.out.println("\nWould you like to develop a property in the Recycling group 'Y/N'?");
		String selection;

		selection = scanner.nextLine();

		// while loop to handle an invalid input
		while (!selection.equalsIgnoreCase("y") && !selection.equalsIgnoreCase("n")) {
			System.err.println("Not a valid entry - please try again");
			selection = scanner.nextLine();
		}

		while (!selection.equalsIgnoreCase("n")) {
			try {
				if (selection.equalsIgnoreCase("y")) {
					System.out
							.println(PAGE_BREAK + "\n\t\t'Recycling' group properties for development\n" + PAGE_BREAK);
					System.out.println("Select the number of the property you wish to develop below : \n");
					for (int i = 0; i < recycling.length; i++) {
						System.out.print("\t");
						System.out.println(i + 1 + " : " + recycling[i].getSquareName());
					}
					System.out.println("\n\tor...");
					System.out.print("\n\t");
					System.out.println(recycling.length + 1 + " : leave Recycling development");

					int optionSelection = Integer.parseInt(scanner.nextLine());
					switch (optionSelection) {
					case 1:
						checkNumDevs(p6, player);
						break;
					case 2:
						checkNumDevs(p7, player);
						break;
					case 3:
						checkNumDevs(p8, player);
					case 4:
						selection = "n";
						break;
					default:
						System.out.println("Please enter a number between 1 and 4");
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number");
			}
		}
	}

	/**
	 * Method to allow selection of property in Activist group that is to be
	 * developed
	 * 
	 * @param player
	 */
	public static void developActivist(Player player) {

		System.out.println("\nWould you like to develop a property in the Activist group 'Y/N'?");
		String selection;

		selection = scanner.nextLine();

		// while loop to handle an invalid input
		while (!selection.equalsIgnoreCase("y") && !selection.equalsIgnoreCase("n")) {
			System.err.println("Not a valid entry - please try again");
			selection = scanner.nextLine();
		}

		while (!selection.equalsIgnoreCase("n")) {
			try {
				if (selection.equalsIgnoreCase("y")) {
					System.out.println(PAGE_BREAK + "\n\t\t'Activist' group properties for development\n" + PAGE_BREAK);
					System.out.println("Select the number of the property you wish to develop below :\n");
					for (int i = 0; i < activists.length; i++) {
						System.out.print("\t");
						System.out.println(i + 1 + " : " + activists[i].getSquareName());
					}
					System.out.println("\n\tor...");
					System.out.print("\n\t");
					System.out.println(activists.length + 1 + " : leave Activist development");

					int optionSelection = Integer.parseInt(scanner.nextLine());
					switch (optionSelection) {
					case 1:
						checkNumDevs(p9, player);
						break;
					case 2:
						checkNumDevs(p10, player);
						break;
					case 3:
						selection = "n";
						break;
					default:
						System.out.println("Please enter a number between 1 and 3");
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number");
			}
		}
	}

	/**
	 * Method to allow selection of property in Renewable group that is to be
	 * developed
	 * 
	 * @param player
	 */
	public static void developRenewable(Player player) {

		System.out.println("\nWould you like to develop a property in the Renewable group 'Y/N'?");
		String selection;

		selection = scanner.nextLine();

		// while loop to handle an invalid input
		while (!selection.equalsIgnoreCase("y") && !selection.equalsIgnoreCase("n")) {
			System.err.println("Not a valid entry - please try again");
			selection = scanner.nextLine();
		}

		while (!selection.equalsIgnoreCase("n")) {
			try {
				if (selection.equalsIgnoreCase("y")) {
					System.out
							.println(PAGE_BREAK + "\n\t\t'Renewables' group properties for development\n" + PAGE_BREAK);
					System.out.println("Select the number of the property you wish to develop below :\n");
					for (int i = 0; i < renewables.length; i++) {
						System.out.print("\t");
						System.out.println(i + 1 + " : " + renewables[i].getSquareName());
					}
					System.out.println("\n\tor...");
					System.out.print("\n\t");
					System.out.println(renewables.length + 1 + " : leave Renewable development");

					int optionSelection = Integer.parseInt(scanner.nextLine());
					switch (optionSelection) {
					case 1:
						checkNumDevs(p1, player);
						break;
					case 2:
						checkNumDevs(p2, player);
						break;
					case 3:
						checkNumDevs(p3, player);
						break;
					case 4:
						selection = "n";
						break;
					default:
						System.out.println("Please enter a number between 1 and 4");
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number");
			}
		}
	}

	/**
	 * Method to insert a page break between sections of the game
	 */
	public static void pageBreak() {
		System.out.println("       ");
		for (int loop = 0; loop < 35; loop++) {
			if (loop == 0) {
				System.out.print("   ");
			} else {
				System.out.print("* ");
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n");
	}
}
