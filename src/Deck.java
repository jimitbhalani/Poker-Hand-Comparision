import java.util.*;
import java.util.Random;

/*
 * Deck class represents a deck of 52 cards which we would initialize in our main entry class PokerHandCompare
 * */
public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	Random rand = new Random();

	//initialize a deck of 52 cards
	public Deck(ArrayList<Card> cards)
	{
		this.cards = cards;
	}

	// using fisher-yates for shuffle
	public void shuffle() {
		for (int i = cards.size() - 1; i >= 0; i--) {
			int rnd = randInt(0, cards.size() - 1);
			Collections.swap(cards, i, rnd);
		}
	}

	/*
	 * Functions to draw hands
	 * */
	public Hand drawFirstHand() {
		Hand hand = new Hand(cards.get(0), cards.get(1), cards.get(2),
				cards.get(3), cards.get(4));
		return hand;
	}

	public Hand drawSecondHand() {
		Hand hand = new Hand(cards.get(5), cards.get(6), cards.get(7),
				cards.get(8), cards.get(9));
		return hand;
	}

	/*
	 * Bunch of helper functions and getters-setters
	 * */
	private int randInt(int min, int max) {

		return rand.nextInt((max - min) + 1) + min;
	}

	public ArrayList<Card> getDeck() {
		return cards;
	}

}
