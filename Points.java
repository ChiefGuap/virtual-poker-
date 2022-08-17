import java.util.*;

/**
 * @author Bilawal Sheikh
 * @StudentNumber: 500563972
 * @ClassType: Points Class
 */

public class Points {

	//Initialize Variables/Objects
	//Card card = new Card("","");
	Card card;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Integer> rankHand = new ArrayList<Integer>();
	ArrayList<String> suitHand = new ArrayList<String>();
	int[] ranks = new int[14];

	int currency = 0;
	String typeOfHand = "";
	boolean flush = true;
	int sgr= 0; //small Group Rank
	int lgr = 0; //Large Group Rank 
	boolean straight = false; // assume no straight
	int topStraightValue = 0;
	boolean royalFlush = false;

	/**
	 * Constructs the player hand
	 * @param h player cards
	 */
	public Points(ArrayList<Card> h) {
		hand = h;
	}

	/**
	 * Determines the type of hand. Eg. Royal Flush, Stright etc...
	 */
	public void givePoints() {

		//Rank ArrayList Only
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRanks().equals("Ace")) 
				rankHand.add(1);
			else if (hand.get(i).getRanks().equals("Jack")) 
				rankHand.add(11);
			else if (hand.get(i).getRanks().equals("Queen")) 
				rankHand.add(12);
			else  if (hand.get(i).getRanks().equals("King")) 
				rankHand.add(13);
			else 
				rankHand.add(Integer.parseInt(hand.get(i).getRanks()));
		}
		
		//Organize rankHand
		Collections.sort(rankHand);
				
		//Suit ArrayList Only
		for (int i = 0; i < hand.size(); i++) {
			suitHand.add(hand.get(i).getSuits());
		}
		
		//START OF CHECKING HAND
		//---------------------------------------------------------------------------------------------------------------------------------
		
		

		//Determine if there is a flush
		for (int i = 0; i < suitHand.size()-1; i++) {
			if (!suitHand.get(i).equals(suitHand.get(i+1))) {
				flush = false;
			}
		}


		
		//Determine 2 of kind, 3 of kind, or 4 of kind
		for (int i = 0; i <= 13; i++) {
			ranks[i] = 0; // zero the contents of the array
		}

		for (int x = 0; x < rankHand.size(); x++) {
			ranks[rankHand.get(x)]++;
		}

		int sameCards = 1;
		int sameCards2 = 1;

		for (int x = 13; x >= 1; x--) {
			if (ranks[x] > sameCards) {
				if (sameCards != 1) {
					sameCards2 = sameCards;
					sgr = lgr;
				}

				sameCards = ranks[x];
				lgr = x;

			} else if (ranks[x] > sameCards2) {
				sameCards2 = ranks[x];
				sgr = x;
			}
		}
		
		
		int sameCardsJacks = 1;
		int sameCards2Jacks = 1;
		
		
		for (int x = 13; x >= 11; x--) {
			if (ranks[x] > sameCardsJacks) {
				if (sameCardsJacks != 1) {
					sameCards2Jacks = sameCardsJacks;
					sgr = lgr;
				}

				sameCardsJacks = ranks[x];
				lgr = x;

			} else if (ranks[x] > sameCards2Jacks) {
				sameCards2Jacks = ranks[x];
				sgr = x;
			}
		}
		
		//Check for Straight
		for (int x = 1; x <= 9; x++) {
			if (ranks[x] == 1 && ranks[x + 1] == 1 && ranks[x + 2] == 1 && ranks[x + 3] == 1 && ranks[x + 4] == 1) {
				straight = true;
				topStraightValue = x+4;
				break;
			}
		}

		if (ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1 && ranks[13] == 1 && ranks[1] == 1) {
			straight = true;
			royalFlush = true;
			
		}

		
		//IF STATEMENTS TO CHECK WHICH ONE IS TRUE. CHECKS FOR THE POSSIBLE OUTCOME(S)
		if (flush) {
			if (royalFlush) {
				currency = 250;
				typeOfHand = "Royal Flush";
			} else if (straight) {
				currency = 50;
				typeOfHand = "Straight Flush";
			} else {
				currency =5;
				typeOfHand = "Flush";
			}
		} else if (sameCards == 4) { //Four of a kind
			currency = 25;
			typeOfHand = "Four of a Kind";
		} else if (sameCards == 3 && sameCards2 == 2) { //Full house
			currency =6;
			typeOfHand = "Full House";
		} else if (straight) { //Straight
			currency =4;
			typeOfHand = "Straight";
		} else if (sameCards == 3 && sameCards2!=2) { //Three of a kind
			currency =3;
			typeOfHand = "Three of a Kind";
		} else if (sameCards == 2 && sameCards2 == 2) { //Two Pairs
			currency = 2;
			typeOfHand = "Two Pairs";
		} else if (sameCardsJacks == 2 && sameCards2Jacks==1) { //One Pair
			currency =1;
			typeOfHand = "One Pair";
		} else { //No pair
			currency = 0;
			typeOfHand = "No pair";
		}

	}
	
	/**
	 * Gets the amount of Dollars the player won from the hand
	 * @return amount of Dollars won
	 */
	public int getAmountOfJavaDollars() {
		return currency;
	}
	
	/**
	 * Gets the type of hand the user had. 
	 * @return type of hand
	 */
	
	public String getTypeOfHandValue() {
		return typeOfHand;
	}
}