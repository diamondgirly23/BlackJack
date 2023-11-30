package default1;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Hand {
	int numOfCards;
	int cardTotal = 0;
	boolean isPlayer = false;
	boolean canPlay = true;
	boolean canSplit = false;
	int currentBet = 0;
	boolean hasWon = false;
	boolean canShowAI = false;
	boolean hasBusted = false;
	boolean hasTied = false;
	
	ArrayList<Card> cards = new ArrayList<Card>();
	public Hand(boolean isPlayer) {
		// This just will be a simple constructor with one parameter which is if the hand is a player hand or the dealer's hand.
		this.isPlayer = isPlayer;
	}
	public void drawCard(Card card, int index) {
		/*
		 * This method has 2 parameters, the card it is drawing, and the index of that card in the deck.
		 * The reason why I need the index is to be able to remove that card from the deck list when I draw it out of it.
		 */
		if(isPlayer) {
			System.out.println("You drew: " + card.toString());
		}
		else if(canShowAI) {
			System.out.println("The dealer drew: " + card.toString());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cards.add(card);
		//This will call the count total function once it adds the card to update the cardtotal.
		countTotal();
		
		BlackJack.deck.cards.remove(index);
		
	}
	public void deleteCard(int cardSplitIndex) {
		cards.remove(cardSplitIndex);
		countTotal();
	}
	public void drawCard(Card card) {
		/*
		 * This drawCard is ONLY USED when splitting hand as you dont need to delete a card from the deck.
		 */
		if(isPlayer) {
			System.out.println("You drew: " + card.toString());
		}
		else if(canShowAI) {
			System.out.println("The dealer drew: " + card.toString());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		cards.add(card);
		//This will call the count total function once it adds the card to update the cardtotal.
		countTotal();
		
		
		
	}
	public void countTotal() {
		/*
		 * WHat this function does is it has two for loops. The first for loop just adds up all the cards.
		 * The second forloop will trigger if the cardTOtal is over 21.
		 * If it is over 21 it will check to see if there are any aces that is currently being counted as an 11.
		 * If it finds one it will minus 10 from the card total (as 11 - 1 = 10) and turn the variable hasUsedAce true.
		 */
		cardTotal = 0;
		for(Card card: cards) {
			cardTotal+= card.getCardValue();
			
			
		
		}
		if(cardTotal > 21) {
			for(Card card: cards) {
				if(card.getCardValue() == 11) {
					cardTotal -= 10;
					card.hasUsedAce = true;
				}
			}
		}
		
	}
	public void checkBust() {
		if(cardTotal > 21) {
			canPlay = false;
		    hasWon = false;
		    hasBusted = false;
		    if(isPlayer)
		    System.out.println("You have busted :( total card value of hand is: " + cardTotal );
		}
	}
	public void showHand() {
		for(Card card: cards) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
		
				
			}
			if(isPlayer) {
				System.out.println("You have: " + card.toString());
			}
			else if(canShowAI) {
				System.out.println("Dealer has: " + card.toString());
			}
			
		}
	}
	public int getTotal() {
		return cardTotal;
	}
	public int checkSplit() {
		Card card1;
		Card card2;
		
		for(int i = 0; i < cards.size(); i++) {
			
			card1 = cards.get(i);
			
			for(int j = i+1; j < cards.size(); j++) {
				
				card2 = cards.get(j);
				
				
				if(card2.cardNumber == card1.cardNumber) {
					canSplit = true;
					return i;
				
				}
			}
		}
		return -1;
	}
	public Card checkSplit(int p) {
		Card card1;
		Card card2;
		for(int i = 0; i < cards.size(); i++) {
			card1 = cards.get(i);
			for(int j = i+1; j < cards.size(); j++) {
				card2 = cards.get(j);
				if(card2.cardNumber == card1.cardNumber) {
					return card1;
				}
			}
		}
		card1 = cards.get(0);
		return card1;
	}
	
}
