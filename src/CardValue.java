/*
 * Enum for CardValue
 * 
 * 2-10 represent number cards, 11-13 represent face cards and 14 for Ace
 * */

public enum CardValue {

	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(
			10), JACK(11), QUEEN(12), KING(13), ACE(14);

	private int value;

	private CardValue(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}
}
