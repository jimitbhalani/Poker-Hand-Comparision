/*
 * Enum for Suit
 * */

public enum Suit {
	Club(0), Diamond(1), Spade(2), Hearts(3);
	private int value;

	private Suit(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}
}
