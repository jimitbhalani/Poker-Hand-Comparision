/*
 * basic poker type card class implements Comparable to compare cards based on their value
 * */

public class Card implements Comparable<Card> {

	private CardValue value;
	private Suit suit;

	//initialize new card based on value and suit
	public Card(CardValue value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	/*
	 * getters and setters for suit and value
	 * */
	public CardValue getCardValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	/*
	 * we use the compareTo method to sort our collections based on card values
	 * */
	@Override
	public int compareTo(Card Card2) {
		int rank1 = this.getCardValue().getValue();
		int rank2 = Card2.getCardValue().getValue();
		if (rank1 == rank2)
			return 0;
		else if (rank1 > rank2)
			return 1;
		else
			return -1;
	}
}
