package default1;
import java.util.Scanner;
public class PlayerTurn {
		
		static int playerChoice = 0;
	    static Scanner scanner = new Scanner(System.in);
	    
		static public void turn(Deck deck, HandsList handslist, Hand AIhand, Bank bank, int bet) {
		
		boolean hasLooped = true;
		int index;	
		int handsIndex = 0;	
	
			while(hasLooped == true) {
				Hand hand;
				System.out.println("The dealer has " + AIhand.cards.get(1) + " Face up");
				for(int i = 0; i < handslist.hands.size(); i++) {
					hand = handslist.hands.get(i);
					while(hand.canPlay)
					{		
						playerChoices(deck,hand,bank,handsIndex,handslist, bet);	
					}
					handsIndex++;			
				}
				hasLooped = false;
				for(Hand hand1: handslist.hands) {
					if(hand1.canPlay)
						hasLooped = true;
				}
			}
		}
		static public void showingCurrentHand(Hand hand, int index) {
			System.out.println("Hand #" +(index +1));
			System.out.println("Your hand currently has the following cards:");
			
			for(Card card: hand.cards) {
				System.out.print(card.toString());
			}
			System.out.println("");
			System.out.println("The card total of all cards is: " + hand.cardTotal);
		}
		static public void addingHand(int numofhand, HandsList hands, Card card, int Bet, Bank bank) {
			for(int i = 0; i < numofhand; i++) {
				Hand hand = new Hand(true);
				hand.drawCard(card);
				hand.currentBet = Bet;
				
				System.out.println(bank.amount);
				bank.amount -= Bet;
				System.out.println(bank.amount);
				hands.hands.add(hand);
				hands.numOfHands++;
			}
			
		}
		static public void addingHand(int numofhand, HandsList hands, int Bet, Bank bank) {
			for(int i = 0; i < numofhand; i++) {
				Hand hand = new Hand(true);
				hand.currentBet = Bet;
				System.out.println(bank.amount);
				bank.amount -= Bet;
				System.out.println(bank.amount);
				
				hands.hands.add(hand);
				hands.numOfHands++;
			}
			
		}
		static public void playerChoices(Deck deck, Hand hand, Bank bank, int handIndex, HandsList hands, int bet) {
			int cardSplitIndex;
			System.out.println("----------------------------------------------------");
			System.out.println("Press 0 to view your hand");
			System.out.println("Press 1 to hit");
			System.out.println("Press 2 to stand");
			
			if(bank.amount >= hand.currentBet)
			System.out.println("Press 3 to Double");
			cardSplitIndex = hand.checkSplit();
			
			if(hand.canSplit && bank.amount >= bet)
			System.out.println("Press 4 to split your hand");
			playerChoice = scanner.nextInt();
			switch(playerChoice) {
			case 0:
				showingCurrentHand(hand,handIndex);
				break;
			case 1:
				hit(hand, deck);
				break;
			case 2:
				stand(hand);
				break;
			case 3:
				if(bank.amount >= hand.currentBet)
				Double(hand,deck, bank);
				break;
			case 4:
				if(hand.canSplit && bank.amount >= bet)
					split(hand,deck,bank,hands, cardSplitIndex, bet);
				else
					System.out.println("You input a incorrect number please input a correct one.");
				break;
			default:
				System.out.println("You input a incorrect number please input a correct one.");
			}
		}
		static public void hit(Hand hand, Deck deck) {
			int randomIndex;
			randomIndex = (int) (Math.random()*deck.cards.size());
			hand.drawCard(deck.cards.get(randomIndex), randomIndex);
			hand.countTotal();
			hand.checkBust();
			
		}
		static public void stand(Hand hand) {
			hand.canPlay = false;
		}
		static public void Double(Hand hand, Deck deck, Bank bank) {
			int randomIndex;
			randomIndex = (int) (Math.random()*deck.cards.size());
			hand.drawCard(deck.cards.get(randomIndex), randomIndex);
			hand.countTotal();
			hand.checkBust();
			hand.canPlay = false;
			bank.amount -= hand.currentBet;
			hand.currentBet*=2;
		}
		static public void split(Hand hand, Deck deck, Bank bank, HandsList hands, int cardSplitIndex, int bet) {
			Card splitCard;
			splitCard = hand.checkSplit(playerChoice);
			addingHand(1,hands,splitCard, bet,bank);
			hand.deleteCard(cardSplitIndex);
			hand.canSplit = false;
			
			
			
		}
		
		
}
