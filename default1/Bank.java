package default1;

public class Bank {
	int amount;
	int minBet;
	int maxBet;
	public Bank(int amount, int minBet, int maxBet) {
		this.amount = amount;
		this.minBet = minBet;
		this.maxBet = maxBet;
	}
	public int getBalance() {
		return this.amount; 
		
	}
	public void setBalance(int Amount) {
		this.amount = Amount;
	}
	public int getMinBet() {
		return minBet;
	}
	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}
	public int getMaxBet() {
		return maxBet;
	}
	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}
	
}
