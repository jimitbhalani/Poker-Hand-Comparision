import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Class to unit test two functions of PokerHand enum namely:
 *  -> hasMatch() which is matching a custom hand to see if it equals that hand from PokerHand Enum 
 *  -> isGreaterThan() which compares two poker hands and returns the one with higher strength
 * 
 * The way you can test is as follows
 *   1. Say for example we are testing hasMatch() for a one pair
 *   2. In our testOnePairHand(), we initialize our custom hand from InitializeOnePair(). You can change the values of cards here and if its not a one pair hand, it will assert an error
 *      ( card 2-10 represent number cards, 11-13 for face cards and 14 for Ace, 
 *        for suits we have 0,1,2,3 
 *        all these are initialized in enum CardValue and Suit)
 *   3. So there are unit tests for each of 9 poker hands
 *   

   Also created a unit test to check if sorting is correctly implemented on our hands. Sorting the hands before comparing them is one of the prime
   implementations of this poker game. If that is not done correctly, then the whole game would fail
    -> sortHand() which is a function found in the Hand class

 * */
public class TestSuite {

	private Deck deck;
	private ArrayList<Card> cards;  

	//5 cards to create our custom hands for each unit test
	private Card firstCard = null;
	private Card secondCard = null;
	private Card thirdCard = null;
	private Card fourthCard = null;
	private Card fifthCard = null;

	//initialize the cards inside the testSuite constructor with deck's already initialized cards 
	public TestSuite(Deck deck) {
		this.deck = deck;
		cards = this.deck.getDeck();
	}

	/*
	 * Below we have a set of unit test functions to test hasMatch() from PokerHand enum
	 * */

	//test to check if we have a match for a one pair hand from our PokerHand enum
	@Test
	public void testOnePairHand()
	{
		Hand hand = InitializeOnePair(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.ONE_PAIR;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for two pair hand check
	@Test
	public void testTwoPairHand()
	{
		Hand hand = InitializeTwoPair(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.TWO_PAIR;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for high card hand check
	@Test
	public void testHighCardHand()
	{
		Hand hand = InitializeHighCard(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.HIGH_CARD;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for three of a kind 
	@Test
	public void testThreeKindHand()
	{
		Hand hand = InitializeThreeKind(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.THREE_OF_KIND;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for a full house
	@Test
	public void testFullHouseHand()
	{
		Hand hand = InitializeFullHouse(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.FULL_HOUSE;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for straight
	@Test
	public void testStraightHand()
	{
		Hand hand = InitializeStraight(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.STRAIGHT;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for four kind
	@Test
	public void testFourKindHand()
	{
		Hand hand = InitializeFourKind(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.FOUR_OF_KIND;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for flush
	@Test
	public void testFlushHand()
	{
		Hand hand = InitializeFlush(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.FLUSH;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}

	//unit test for straight_flush
	@Test
	public void testStraightFlushHand()
	{
		Hand hand = InitializeStraightFlush(firstCard, secondCard, thirdCard, fourthCard,
				fifthCard);
		PokerHand pokerhand = PokerHand.STRAIGHT_FLUSH;
		ArrayList<Card> sortedHand = hand.sortHand();
		boolean result = pokerhand.hasMatch(sortedHand);
		assertEquals(true,result);
	}


	/*
	 * unit test function for isGreaterThan() to compare two hands to check which one is ranked higher
	 * */

	//Testing the isGreaterThan() of PokerHand enum. So initialize any two Poker hands you want to compare and change the unit test accordingly, here I am testing a flush with a straight hand
	@Test
	public void testTwoHandRanks()
	{
		PokerHand pokerFlushHand = PokerHand.FLUSH;
		PokerHand pokerStraightHand = PokerHand.STRAIGHT;
		int result = pokerFlushHand.isGreaterThan(pokerStraightHand);
		assertEquals(1,result);
	}


	/*unit test function for sortHand() to test the sorting functionality of our hands
	 * */

	//we test sorting numbers based on our card values ( 2-10 for number values and 11-14 for face values )
	@Test
	public void testHandSorting()
	{
		//create 5 cards with different values
		Card one = new Card(CardValue.FIVE,Suit.Club);
		Card two = new Card(CardValue.SEVEN,Suit.Hearts);
		Card three = new Card(CardValue.QUEEN,Suit.Club);
		Card four = new Card(CardValue.KING,Suit.Spade);
		Card five = new Card(CardValue.THREE,Suit.Diamond);

		//create the unsorted Hand and expected Sorted Hand from above card values
		Hand unsortedHand = new Hand(one,two,three,four,five);
		Hand expectedSortedHand = new Hand(five,one,two,three,four);  // -> changing the expected output here would assert throws an exception

		//sort the unsorted hand to test and get the list of expectedSortedHand
		ArrayList<Card> resultSortedList = unsortedHand.sortHand();
		ArrayList<Card> expectedSortedList = expectedSortedHand.getHandCardsList();

		//check if those two lists match
		assertEquals(expectedSortedList,resultSortedList);
	}

	/*
	 * This section of the code initializes all the custom hands for our unit tests, you can go ahead and change the numbers to create your own custom hand for testing
	 * */

	//initialize a custom hand that matches a straight flush (same suit and straight pattern, eg: A 2 3 4 5) //Ace being no 14
	private Hand InitializeStraightFlush(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 2) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 4) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 5) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 14) {
				fifthCard = cards.get(i);
			}
		}

		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize a custom hand for four of a kind (different suit and 4 duplicate cards )
	private Hand InitializeFourKind(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 14) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 2) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 14) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 14) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 14) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize three of a kind ( 3 same card numbers and different suits )
	private Hand InitializeThreeKind(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 14) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 3) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 3) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 9) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize a full house hand 
	private Hand InitializeFullHouse(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 14) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 3) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 3) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 14) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize flush
	private Hand InitializeFlush(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 14) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 5) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 8) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 12) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize straight
	private Hand InitializeStraight(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 3) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 7) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 5) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 6) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 4) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize two pair
	private Hand InitializeTwoPair(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 3) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 4) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 11) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 4) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize one pair
	private Hand InitializeOnePair(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 5) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 3) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 4) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 11) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 3) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

	//initialize high card
	private Hand InitializeHighCard(Card firstCard, Card secondCard,
			Card thirdCard, Card fourthCard, Card fifthCard) {
		Hand hand;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().getValue() == 0
					&& cards.get(i).getCardValue().getValue() == 3) {
				firstCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 7) {
				secondCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 3
					&& cards.get(i).getCardValue().getValue() == 9) {
				thirdCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 2
					&& cards.get(i).getCardValue().getValue() == 11) {
				fourthCard = cards.get(i);
			}
			if (cards.get(i).getSuit().getValue() == 1
					&& cards.get(i).getCardValue().getValue() == 13) {
				fifthCard = cards.get(i);
			}
		}
		hand = new Hand(firstCard, secondCard, thirdCard, fourthCard, fifthCard);
		return hand;
	}

}
