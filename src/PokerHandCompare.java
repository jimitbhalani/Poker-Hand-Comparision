import java.util.*;

/*
 * The main entry point of the game. 
 * Here I execute a set of events similar to a real poker game but restricted as per the requirements of the test ie to only compare two poker hands
 * */
public class PokerHandCompare {

	private ArrayList<Card> cardsList = new ArrayList<Card>();
	private Deck deck;
	private Hand firstHand;
	private Hand secondHand;

	private void InitializeDeckOfCards() {
		// for 4 suits, initialize 13 cards of 13 values
		for (Suit suit : Suit.values()) {
			for (CardValue cardVal : CardValue.values()) {
				cardsList.add(new Card(cardVal, suit));
			}
		}
		setDeckOfCards();
	}

	private void setDeckOfCards() {
		deck = new Deck(cardsList);
	}

	private void shuffleDeck() {
		deck.shuffle();
	}

	//shuffle the deck on every round and draw hand1 and hand2 ( first 10 cards of the deck )
	private void drawHands() {
		shuffleDeck();
		firstHand = deck.drawFirstHand();
		secondHand = deck.drawSecondHand();
		System.out.println("  ------------------  ");
		System.out.println("  FIRST HAND ");
		System.out.println("  ------------------  ");
		firstHand.displayHand();
		System.out.println("  ------------------  ");
		System.out.println("  ");
		System.out.println("  ------------------  ");
		System.out.println("  ------------------  ");
		System.out.println("  ");
		System.out.println("  ------------------  ");
		System.out.println("  SECOND HAND ");
		System.out.println("  ------------------  ");
		secondHand.displayHand();
		System.out.println("  ------------------  ");
	}

	/*
	 * First we sort the two drawn hands. This makes comparing easier.
	 * We find a potential PokerHand match for the hands
	 * We compare the strengths of the two hands and declare a winner
	 * Special case to handle incase of draw, we search for the next highest card in the hand and declare the winner. Incase both players have identical pairs, we just end the round
	 * */
	private void evaluateHands() {

		ArrayList<Card> hand1SortedList = firstHand.sortHand();
		ArrayList<Card> hand2SortedList = secondHand.sortHand();

		PokerHand hand1 = null;
		PokerHand hand2 = null;
		for (PokerHand hand : PokerHand.values()) {
			if (hand.hasMatch(hand1SortedList)) {
				hand1 = hand;
			}
			if (hand.hasMatch(hand2SortedList)) {
				hand2 = hand;
			}
		}
		if (hand1 == null || hand2 == null)
			return;

		else {
			System.out.println(" ");
			System.out.println("  -------------------------------------------------- ");
			System.out.println("  FIRST HAND -> " + hand1 + " || SECOND HAND -> " + hand2);
			System.out.println("  -------------------------------------------------- ");
			if (hand1.isGreaterThan(hand2) == 1) {
				System.out.println(" ");
				System.out.println("  ------------------------ ");
				System.out.println("  FIRST HAND IS THE WINNER ");
				System.out.println("  ------------------------ ");
			} else if (hand1.isGreaterThan(hand2) == -1) {
				System.out.println(" ");
				System.out.println("  ------------------------- ");
				System.out.println("  SECOND HAND IS THE WINNER ");
				System.out.println("  ------------------------- ");
			} else if (hand1.isGreaterThan(hand2) == 0) {
				System.out.println(" ");
				System.out.println("  ----------------------- ");
				System.out.println("  BOTH HANDS ARE THE SAME, FINAL RESULT BASED ON HIGH CARD ");
				System.out.println("  ----------------------- ");
				Card firstHighCard = firstHand.getHighCard(hand1SortedList);
				Card secondHighCard = secondHand.getHighCard(hand2SortedList);

				//even if on a tie, the highest cards are equal, we want the next highest card. we just deepcopy the original sorted list as we don't want to change the original copy. Doesn't matter but I feel this is right way to do
				if(firstHighCard.getCardValue().getValue() == secondHighCard.getCardValue().getValue())
				{
					ArrayList<Card> list1copy = deepCopy(hand1SortedList);
					ArrayList<Card> list2copy = deepCopy(hand2SortedList);				
					list1copy.remove(list1copy.remove(list1copy.size()-1));
					list2copy.remove(list2copy.remove(list2copy.size()-1));
					while(true && list1copy.size() > 0 && list2copy.size() > 0)
					{
						firstHighCard = firstHand.getHighCard(list1copy);
						secondHighCard = secondHand.getHighCard(list2copy);
						if(firstHighCard.getCardValue().getValue() != secondHighCard.getCardValue().getValue()) break;
						else
						{
							list1copy.remove(list1copy.remove(list1copy.size()-1));
							list2copy.remove(list2copy.remove(list2copy.size()-1));
						}
					}
				}

				//if even after above, the two players have identical pairs, then end the game here
				if(firstHighCard.getCardValue().getValue() == secondHighCard.getCardValue().getValue())
				{
					System.out.println(" ");
					System.out.println("  ----------------------- ");
					System.out.println("  IDENTICAL PAIRS, NO RESULT ");
					System.out.println("  ----------------------- ");
					System.out.println(" ");
					System.out.println("  -------------- END OF GAME ---------------- ");
					return;
				}

				//if we pass the above two checks, then finally there will be a winner, we compare the card ranks and declare the winner
				int rank = firstHighCard.compareTo(secondHighCard);
				System.out.println(" ");
				if(rank == 1) 
				{
					System.out.println("  FIRST CARD WINS ON HIGH CARD " +firstHighCard.getCardValue());
				}
				else 
				{
					System.out.println("  SECOND CARD WINS ON HIGH CARD " +secondHighCard.getCardValue());
				}
				System.out.println("  ----------------------- ");
			}
		}
		System.out.println(" ");
		System.out.println("  -------------- END OF GAME ---------------- ");
		String answer = "";
		Scanner in = new Scanner(System.in);
		System.out.println(" ");
		System.out.println(" WANT TO PLAY AGAIN ? YES/NO ");
		answer = in.nextLine();
		processOptions(answer,in);
	}

	private ArrayList<Card> deepCopy(ArrayList<Card> sortedList)
	{
		ArrayList<Card> newList = new ArrayList<Card>();
		for(Card card : sortedList)
		{
			newList.add(card);
		}
		return newList;
	}

	/*
	 * Module to start unit testing from TestSuite class
	 * */
	@SuppressWarnings("unused")
	private void UnitTestModules() {
		// check for straight flush
		TestSuite tester = new TestSuite(deck);
		tester.testHighCardHand();
		tester.testOnePairHand();
		tester.testTwoPairHand();
		tester.testThreeKindHand();
		tester.testStraightHand();
		tester.testFlushHand();
		tester.testFullHouseHand();
		tester.testFourKindHand();
		tester.testStraightFlushHand();
		tester.testTwoHandRanks();
		tester.testHandSorting();
		System.out.println("success test cases");
	}

	private void displayOptions()
	{
		System.out.println(" ");
		System.out.println("  -------------- WELCOME TO POKER HAND COMPARES ---------------- ");
		System.out.println(" ");
		System.out.println(" CHOOSE ONE OF THE BELOW OPTIONS ");
		System.out.println(" ");
		System.out.println(" 1. Draw Hands to Compare ");
		System.out.println(" 2. Quit ");
	}

	private void processOptions(int a,Scanner in)
	{

		if( a != 1 && a != 2)
		{
			System.out.println(" Invalid option, please choose the correct option ");
			a = in.nextInt();	
			processOptions(a,in);
			return;
		}
		if( a == 1)
		{
			drawHands();
			evaluateHands();
		}	
		else if(a == 2)
		{
			System.out.println(" Thank you for playing! ");
			return;
		}
	}

	private void processOptions(String answer,Scanner in)
	{
		if(!answer.toLowerCase().equals("yes") && !answer.toLowerCase().equals("no"))
		{
			System.out.println(" Invalid option, please choose the correct option ");
			answer = in.nextLine();
			processOptions(answer,in);
			return;
		}
		if(answer.toLowerCase().equals("yes"))
		{
			displayOptions();
			int a;
			a = in.nextInt();
			processOptions(a,in);
		}else if(answer.toLowerCase().equals("no"))
		{
			System.out.println(" Thank you for playing! ");
			return;
		}
	}

	public static void main(String[] args) {

		PokerHandCompare compare = new PokerHandCompare();
		compare.InitializeDeckOfCards();
		int a;
		Scanner in = new Scanner(System.in);
		compare.displayOptions();
		a = in.nextInt();
		compare.processOptions(a,in);
		//compare.UnitTestModules();  -> uncomment this line to start unit testing but make sure you comment previous calls to functions drawHands() and evaulateHands()
	}
}
