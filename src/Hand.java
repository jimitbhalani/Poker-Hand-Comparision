import java.util.*;

/*
 * Hand class represents a hand of 5 cards used to compare the poker hands
 * Each Hand represents one of 9 games of Poker
 * */
public class Hand {
	
	private ArrayList<Card> cardsList = new ArrayList<Card>();
	
	//initialize a Hand of 5 cards
	public Hand(Card first,Card second,Card third,Card fourth, Card fifth) {
		cardsList.add(first);
		cardsList.add(second);
		cardsList.add(third);
		cardsList.add(fourth);
		cardsList.add(fifth);
	}

	//sorting hands makes it easier to test strength of poker hands. One of the most important implementations of this game 
	public ArrayList<Card> sortHand()
	{
		ArrayList<Card> sortedList = new ArrayList<Card>();
		deepCopy(sortedList);
		Collections.sort(sortedList);
		return sortedList;
	}


	//get the cardsList
	public ArrayList<Card> getHandCardsList()
	{
		return cardsList;
	}

	//display hand
	public void displayHand()
	{
		for(Card card : cardsList)
		{
			System.out.println("  " +card.getCardValue() + " -> " + card.getSuit());
		}
	}

	//incase of a draw, the final winner is decided based on the HighCard
	public Card getHighCard(ArrayList<Card> hand1SortedList)
	{
		//get the last card on the two array's, compare them and return the highest card
		Card firstHighCard =  hand1SortedList.get(hand1SortedList.size()-1);
		return firstHighCard;
	}

	//helper function to deepcopy the hands list
	private void deepCopy(ArrayList<Card> list)
	{
		for(Card card : cardsList)
		{
			list.add(card);
		}
	}
}
