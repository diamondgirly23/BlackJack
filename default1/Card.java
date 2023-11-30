package default1;



public class Card {
	int cardNumber;
	boolean isAce = false;
	boolean hasUsedAce = false;
	String suit;
	public Card(String suit, int cardNumber) {
		// This constructor will ask for the suit (created in the deck class) and the card number (also created in the deck class)
		this.cardNumber = cardNumber;
		
		this.suit = suit;
		// The one key difference in this constructor is I have put a if statement to see if the card is a ACE or not.
		if(cardNumber == 1) {
			isAce = true;
		}
	
	}
	public int getCardValue() {
		/* This is a method that will return the value of the card. The key take away in this is the fact the ace can be either
		 * equal to 1 or 11. I am using a boolean to keep track of which "version" the card is currently.
		 */
		if(cardNumber == 1) {
			if(hasUsedAce) 
				return 1;
			
			else
				return 11;
		}
		if(cardNumber > 10) {
			
			return 10;
		}
		else {
			return cardNumber;
		}

		
	}
	
	public String toString() {
		// This toString is me overriding the built in "toString" in the class to be able to print what the card is exactly (the suit and the value)
		switch(cardNumber) {
		case 1:
			return "The Ace of " + this.suit + ", ";
		case 11:
			return "the "+ "jack" + " of " + this.suit + ", ";	
		case 12:
			return  "the Queen of "+ this.suit + ", ";	
		case 13:
			return  "the King of "+ this.suit+ ", ";	
		default:
			return "the "+ this.cardNumber + " of " + this.suit+ ", ";
		
			
		}
	
		
	}
}
