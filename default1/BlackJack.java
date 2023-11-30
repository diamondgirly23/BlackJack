package default1;

import java.util.Random;
import java.util.Scanner;
public class BlackJack {
	/* Initializing various things that will be important for the overall program.
	 * Including a couple of static variables that will be used in multiple classes.
	 */
	Scanner scanner = new Scanner(System.in);
	Random random = new Random();
	static Deck deck = new Deck(1);
	static HandsList handslist = new HandsList();
	
	static Hand AIhand = new Hand(false);
	boolean willContinue = true;
	
	int randomResult;
	int choice = 10;
	
	public void mainGame(Bank bank) {
		//This uses a method inside deck to create a deck.
		deck.deckCreation();
		// This function call will be where the main game will take place.
		while(willContinue) {
			MainGameLoop.mainLoop(deck, handslist, AIhand, bank);
			
			while(choice != 1) {
				System.out.println("Press 1 if you wish to play again 0 to exit.");
				choice = scanner.nextInt();
				if(choice == 0) {
					break;
				}
				
			}
			if(choice == 1) {
				willContinue = true;
			}
			else {
				willContinue = false;
			}
			choice = 10;
			
			handslist.hands.clear();
			
			AIhand.canShowAI = false;
			AIhand.canPlay = true;
			AIhand.cards.clear();
			
		}
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
