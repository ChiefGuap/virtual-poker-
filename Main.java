import java.util.*;


public class Main {
	
	public static void main(String[] args) {
		//Initialize Variables/Objects
		Scanner inputInt = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);

		ArrayList<Card> hand = new ArrayList<Card>();
		Deck deck = new Deck();
		//Card card = new Card("", "");
		Card card;
		Points markHand;
		PokerTester test = new PokerTester();

		int choice;
		String disCardRank;
		String disCardSuit;
		int disCard = 0; //remove a card
		String anotherRemove = ""; //empty string. used later to take a user input 
		int moneyLeft = 0; //how much monmey is left lol
		int currency = 3; //starting amount of money 
		int timesPlayed = 0; // how much times you hgave played the game 
		final int COST = 1; //Costs the user 1 currency to play the game 
		String playAgain = "";
		
		
		//Intorduction to the game 
		System.out.println("Welcome to Nabeeha's Fantastic and radical Poker Game");
		System.out.println("You start with 3 Dollars. If you have 0 Dollars, you will not be able to play anymore.");
		System.out.println("If you would like to remove a card, Enter in a Rank and a Suit.");
		System.out.println("Rank = Ace, 2, 3, 4, 5, ..., Jack, Queen, King");
		System.out.println("Suits = Clubs, Hearts, Diamonds and Spades");
		System.out.println("Note: If you want to remove some cards, you can only remove 5 cards max.");
		System.out.println("Enjoy the game.");
		System.out.println();
		System.out.println();
		
		System.out.print("Would you like to run the Simulator or Tester? [1=Simulator, 2=Tester] ");
		choice = inputInt.nextInt();
		
		System.out.println();
		
		if (choice == 1) { 
			do {
				hand = new ArrayList<Card>();
				deck = new Deck();
				card = new Card("", "");
				
				//Start The Poker Game
				timesPlayed++;
				deck.createDeck();
				deck.shuffleDeck();
				hand = deck.dealHand();
				//Prints The User Hand
				System.out.println("You have the following Cards:");
				for (int i = 0; i < hand.size(); i++) {
					card = hand.get(i);
					System.out.println(card.getRanks() + " " + card.getSuits());
				}
		
				System.out.println("");
				System.out.print("Would you like to remove all cards, some cards or none: [1=All, 2=Some, 3=None]: ");
				choice = inputInt.nextInt();
		
				if (choice == 1) {
					hand = deck.dealHand();
					System.out.println("Your hand now contrains:");
					for (int i = 0; i < hand.size(); i++) {
						card = hand.get(i);
						System.out.println(card.getRanks() + " " + card.getSuits());
					}
					
				} else if (choice == 2) {
					disCard = 0;
					do {
						disCard++;
						System.out.println("What cards would you like to discard? ");
						System.out.print("Enter Rank: ");
						disCardRank = inputString.nextLine();
						System.out.print("Enter Suit: ");
						disCardSuit = inputString.nextLine();
						System.out.println("");
						for (int i = 0; i < hand.size(); i++) {
							card = hand.get(i);
							if (card.getRanks().equalsIgnoreCase(disCardRank) && card.getSuits().equalsIgnoreCase(disCardSuit)) {
								hand.remove(i);
								hand.add(deck.getCard());
								break;
							} else if (i == 4) {
								System.out.println("Card can not be found");
								disCard--;
							}
						}
						System.out.println("You're cards are: ");
						for (int i = 0; i < hand.size(); i++) {
							card = hand.get(i);
							System.out.println(card.getRanks() + " " + card.getSuits());
						}
						System.out.println();
						System.out.print("Would you like to remove another card?[Y = Yes, N = No] ");
						anotherRemove = inputString.nextLine();
					} while((anotherRemove.equalsIgnoreCase("Yes") || anotherRemove.equalsIgnoreCase("Y")) && disCard!=5);
					if (disCard == 5) {
						System.out.println();
						System.out.println("You can not change anymore cards, only 5 cards can be changed.");
						System.out.println("--------------------------------------------------------------");
					}
				} else {
					System.out.println("No cards Removed");
				}
				
				markHand = new Points(hand);
				markHand.givePoints();
				System.out.println();
				System.out.println("Type of Hand: " + markHand.getTypeOfHandValue());
				System.out.println("Amount won: " + markHand.getAmountOfJavaDollars() + " Currency");
				currency += markHand.getAmountOfJavaDollars();
				moneyLeft = currency-(timesPlayed*COST);
				System.out.println("Total Profit [minus cost of playing]: " + moneyLeft + " Currency");
				
				System.out.print("Would you like to play again: [Y = Yes, N = No] ");
				playAgain = inputString.nextLine();
				System.out.println("-----------------------------------------------");
				System.out.println();
			} while((playAgain.equalsIgnoreCase("Yes") || playAgain.equalsIgnoreCase("Y")) && moneyLeft >0);
			
			if (moneyLeft == 0) {
				System.out.println("You have no more money, therefore you will not be able to play again.");
				System.out.println("----------------------------------------------------------------------------");
			} else {
				System.out.println("You have quited");
				System.out.println("Total Profit [minus cost of playing]: " + moneyLeft + " Currency");
			}
		} else if (choice == 2) {
			test.testObjects();
		} else {
			System.out.println("Unknown Number");
		}
		//Closes input
		inputInt.close();
		inputString.close();
	}
}