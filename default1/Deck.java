package default1;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	/*
	 * I am creating a deck class which will hold cards. I am also making it so there can be a customizable amount of decks
	 * Just like in real blackjack the "shoe" can contain multiple separate decks inside of it.
	 */
 ArrayList<Card> cards = new ArrayList<Card>();
 int numberOfDecks;
 int cardsInDeck = 13;
 int totalCardsInDeck = cardsInDeck*4*numberOfDecks;

 
 
 public Deck (int numberOfDecks) {
	 //This is a simple constructor that will just ask for the number of decks the programmer wishes to put into it.
	 this.numberOfDecks = numberOfDecks;
	
 }
 public void deckCreation() {
		
		
		int cardValue = 1;
		int counter = 1;
		int timesReset = 0;
		cards.clear();
		//What this basically does is takes the cards in the deck and mutliplies it by the number of decks, it will then reset the "value" 
		//every time it goes past 13 as there are only 13 unique card types in the standard deck. In order to still know when to break 
		// I have made a timesreset variable that will increase by one every time it does this, it will then check if it is equal to number of decks.
		for(int i = 1; i <= this.cardsInDeck*numberOfDecks; i++) {			
				cardValue = i;
				if(cardValue > 13) {
					timesReset++;
					i = 1;
					cardValue = i;
				}
				if(timesReset >= numberOfDecks) {
					break;
					
				}
				//Because there are 4 suits of each type this while loop loop 4 times and creates 4 cards for each new cardvalue given.
				while(counter <= 4) {
					cards.add(new Card(suitGetter(counter),cardValue));
					counter++;
				}		
				counter = 1;

				
		}
		
	}
		
	

	public String suitGetter(int counter) {
		// This runs with the while loop where the counter will determine which suit it is going to be.
			
			switch(counter) {
			case 1:
				return "Hearts";
			case 2:
				return"Spades";
			case 3:
				return "Clubs";
			case 4:
				return "Diamonds";
			default:
				return "Error";
		}
		
	}
 
}
