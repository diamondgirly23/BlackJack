package default1;

public class DealerTurn {
		static public void turn(Deck deck, Hand AIhand) {
			AIhand.canShowAI = true;
			System.out.println("--------------------------");
			System.out.println("Dealer's turn");
			showingAIHand(AIhand);
			determiningActions(AIhand, deck);
			
		}
		static public void showingAIHand(Hand AIhand) {
			
			System.out.println("dealer flips up their other card. The cards they have are: ");
			
			for(Card card: AIhand.cards) {
				System.out.print(card.toString());
			}
			System.out.println("");
			System.out.println("The card total of all cards is: " + AIhand.cardTotal);
		}
		static public void determiningActions(Hand AIhand, Deck deck) {
			AIhand.countTotal();
			while(AIhand.canPlay) {
				if(AIhand.cardTotal < 17) {
					int randomIndex;
					randomIndex = (int) (Math.random()*deck.cards.size());
					AIhand.drawCard(deck.cards.get(randomIndex), randomIndex);
					AIhand.countTotal();
					AIhand.checkBust();
					System.out.println("The card total of all cards is: " + AIhand.cardTotal);
				}
				else {
					AIhand.canPlay = false;
				}
				if(AIhand.cardTotal > 21){
					AIhand.canPlay = false;
					AIhand.hasBusted = true;
					System.out.println("Dealer has busted");
				}
				
			}
			
		}
}
