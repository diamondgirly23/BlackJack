package default1;
import java.util.Scanner;
public class MainGameLoop {
	static Scanner scanner = new Scanner(System.in);
	public static void mainLoop(Deck deck, HandsList handslist, Hand AIhand, Bank bank) {
		// This is creating a variable and its goign to use this variable to keep track of the user input of the bet and to subtract it from the bank.
		// It will also put the bet into the hand itself to know how much the hand is worth, so it knows what the payout will be.
		
		int bet = 0;
		System.out.println("Hello");
		// This is just a quick check to see if they have enough money to bet.
		if(bank.getBalance() < bank.getMinBet()) {
			System.out.println("Wow you somehow lost all your money, you are not good at blackjack, but since this is not real life here, have free money");
			bank.setBalance(bank.amount += 1000);
		}
		System.out.println("Your bank balance is:" + bank.getBalance());
		System.out.println("Minimum bet is: " + bank.getMinBet() + "The maximum bet is: " + bank.getMaxBet());
		
		do {
			System.out.println("PLease input the bet.");
			bet = scanner.nextInt();
			if(bet > bank.getMaxBet() || bet < bank.getMinBet()) {
				System.out.println("You input a invalid amount please try again");
			}
			
			
		}while((bet < bank.getMinBet() || bet > bank.getMaxBet()) || bet > bank.amount);
		
		PlayerTurn.addingHand(1, handslist, bet,bank);
		
		
		int randomIndex;
		// This nested forloop will go through each hand and give the hand(s) 2 cards each. it will also put in the bet into the hand.
		
		for(Hand currentHand: handslist.hands) {
			for(int i = 0; i < 2; i++) {
				randomIndex = (int) (Math.random()*deck.cards.size());
				currentHand.drawCard(deck.cards.get(randomIndex), randomIndex);
				
				
			}	
			currentHand.currentBet = bet;
			
		}
		// This will create the dealer's hand.
		randomIndex = (int) (Math.random()*deck.cards.size());
		AIhand.drawCard(deck.cards.get(randomIndex), randomIndex);
		randomIndex = (int) (Math.random()*deck.cards.size());
		AIhand.drawCard(deck.cards.get(randomIndex), randomIndex);
		if(handslist.hands.get(0).cardTotal == 21) {
			if(AIhand.cardTotal == 21) {
				System.out.println("You and dealer both have 21. Game is a draw");
				bank.amount += handslist.hands.get(0).currentBet;
				return;
			}
			else {
				System.out.println("You have 21! you win! you get 3/2 bet increase");
				bank.amount += handslist.hands.get(0).currentBet*2*(3/2);
				return;
			}
		}
		else if(AIhand.cardTotal == 21) {
			System.out.println("Dealer has 21. You lose");
			return;
		}
		
		// This will be the function that the main game loop will run on as the player turn is what matters the most in this.
		PlayerTurn.turn(deck, handslist, AIhand, bank, handslist.hands.get(0).currentBet);
		// This will be the dealer turn as the name suggests. 
		DealerTurn.turn(deck,AIhand);
		for(Hand currentHand: handslist.hands) {
			if(currentHand.cardTotal > 21)
				currentHand.hasBusted = true;
			if(AIhand.cardTotal <= 21) {
				AIhand.hasBusted = false;
			}
			else{
				AIhand.hasBusted = true;
			}
			if(currentHand.hasBusted == false) {
				if(AIhand.hasBusted)
					currentHand.hasWon = true;
				else if(currentHand.cardTotal > AIhand.cardTotal)
					currentHand.hasWon = true;
				else if(currentHand.cardTotal == AIhand.cardTotal)
					currentHand.hasTied = true;
				else 
					currentHand.hasWon = false;
				
				
			}
			else {
				currentHand.hasWon = false;
			}
			
			
		}
		int counter = 0;
		for(Hand currentHand: handslist.hands) {
			
			if(currentHand.hasWon) {
				bank.amount = bank.amount + currentHand.currentBet*2;
				System.out.println("Hand number: "+ (counter+1)+ "Has won!");
			}
			else if (currentHand.hasTied) {
				bank.amount = bank.amount + currentHand.currentBet;
				System.out.println("Hand number: "+ (counter+1)+ " Has tied!");
			}
			else {
				System.out.println("Dealer won against Hand number: " + (counter+1));
			}
			
		}
		System.out.println("Your new bank amount is: " + bank.amount);
		// This will check to see if the deck needs to be shuffled as is normal in regular blackjack
		// (they dont play all cards they stop it preemptively because of card counting)
		if(deck.cards.size() < (deck.cardsInDeck*deck.numberOfDecks*4)/3) {
			deck.deckCreation();
			System.out.println("Deck reshuffled.");
		}
		
		
	}

	
}
