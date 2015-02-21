import java.util.*;

/*
 * The heart and soul of the game resides inside this enum
 * Each type overrides a hasMatch function which compares the player's hand to one of the matching hands
 * Each incoming hand are sorted first, so comparision is easier that way
 * Each Hand is implemented with a simple logic and few using data structures like HashMap to get the results quickly
 * Each PokerHand type has a value which represents its strength. 1 being the highest and 9 being the lowest
 * */
public enum PokerHand {

	STRAIGHT_FLUSH(1) {
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			int suitValue = hand.get(0).getSuit().getValue();

			// first check if all cards are of the same suit, if one is not then
			// there is no point checking further
			for (int i = 1; i < hand.size(); i++) {
				if (hand.get(i).getSuit().getValue() != suitValue)
					return false;
			}

			// System.out.println(" PASSED THE SAME SUIT MATCH ");
			// check for a Steeler wheel, A 2 3 4 5, a special case handled
			// separately
			int lastCardValue = hand.get(hand.size() - 1).getCardValue()
					.getValue();
			if (lastCardValue == 14) {

				int firstCard = hand.get(0).getCardValue().getValue();
				if (firstCard == 2) {
					int counter = 1;
					for (int i = 1; i < hand.size(); i++) {
						if (i + 1 < hand.size()) {
							if (hand.get(i + 1).getCardValue().getValue()
									- hand.get(i).getCardValue().getValue() == 1)
								counter++;
						}
					}
					if (counter == 3) {
						// WE GOT A STEELER WHEEL!
						return true;
					}
				}
			}

			// check if they are straight, would also handle the case of royal flush
			// 10,J,Q,K,ACE!
			for (int i = 0; i < hand.size(); i++) {
				if (i + 1 < hand.size()) {
					if (hand.get(i + 1).getCardValue().getValue()
							- hand.get(i).getCardValue().getValue() > 1)
						return false;
				}
			}
			return true;
		}
	},
	FOUR_OF_KIND(2) {
		
		//used a hashmap to keep a counter of values repeated 4 times
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < hand.size(); i++) {
				if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
					map.put(hand.get(i).getCardValue().getValue(), 1);
				} else {
					map.put(hand.get(i).getCardValue().getValue(),
							map.get(hand.get(i).getCardValue().getValue()) + 1);
				}
			}
			for (Integer counter : map.values()) {
				if (map.size() == 2 && counter == 4)
					return true;
			}
			return false;
		}
	},
	FULL_HOUSE(3) {
		
		//used a HashMap to get counters of two equal cards + three other equal cards
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < hand.size(); i++) {
				if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
					map.put(hand.get(i).getCardValue().getValue(), 1);
				} else {
					map.put(hand.get(i).getCardValue().getValue(),
							map.get(hand.get(i).getCardValue().getValue()) + 1);
				}
			}
			int counter = 0;
			if (map.size() == 2) {
				for (Integer key : map.keySet()) {
					if (map.get(key) == 3) {
						counter++;
					}
					if (map.get(key) == 2) {
						counter++;
					}
				}
			}
			return counter == map.size();
		}
	},
	FLUSH(4) {
		
		//just need to check if all the cards are of same suit
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			int suitValue = hand.get(0).getSuit().getValue();

			// check all of the same suit
			for (int i = 1; i < hand.size(); i++) {
				if (hand.get(i).getSuit().getValue() != suitValue)
					return false;
			}
			return true;
		}
	},
	STRAIGHT(5) {
		
		//check if cards are in a straight ordering. Sorting makes this easier for us
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			for (int i = 0; i < hand.size(); i++) {
				if (i + 1 < hand.size()) {
					if (hand.get(i + 1).getCardValue().getValue()
							- hand.get(i).getCardValue().getValue() > 1)
						return false;
				}
			}
			return true;
		}
	},
	THREE_OF_KIND(6) {
		
		//use a HashMap counter to check if there are 3 equal cards and 2 unique cards
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < hand.size(); i++) {
				if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
					map.put(hand.get(i).getCardValue().getValue(), 1);
				} else {
					map.put(hand.get(i).getCardValue().getValue(),
							map.get(hand.get(i).getCardValue().getValue()) + 1);
				}
			}
			for (Integer counter : map.values()) {
				if (map.size() == 3 && counter == 3)
					return true;
			}
			return false;
		}
	},
	TWO_PAIR(7) {
		
		//two pairs -> counter of 2 and 1 unique card -> counter of 1
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < hand.size(); i++) {
				if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
					map.put(hand.get(i).getCardValue().getValue(), 1);
				} else {
					map.put(hand.get(i).getCardValue().getValue(),
							map.get(hand.get(i).getCardValue().getValue()) + 1);
				}
			}
			int counter = 0;
			if (map.size() == 3) {

				for (Integer key : map.keySet()) {
					if (map.get(key) == 2) {
						counter++;
					}
					if (map.get(key) == 1) {
						counter++;
					}
				}
			}
			return counter == 3;
		}
	},
	ONE_PAIR(8) {
		
		//one pair - > counter of 1 + three other unique cards -> counter of 3. 
		@Override
		boolean hasMatch(ArrayList<Card> hand) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < hand.size(); i++) {
				if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
					map.put(hand.get(i).getCardValue().getValue(), 1);
				} else {
					map.put(hand.get(i).getCardValue().getValue(),
							map.get(hand.get(i).getCardValue().getValue()) + 1);
				}
			}
			int counter = 0;
			if (map.size() == 4) {

				for (Integer key : map.keySet()) {
					if (map.get(key) == 2) {
						counter++;
					}
					if (map.get(key) == 1) {
						counter++;
					}
				}
			}
			return counter == 4;
		}
	},
	HIGH_CARD(9) {
		
		//first check if its not a straight as straight also has all unique cards
		@Override
		boolean hasMatch(ArrayList<Card> hand) {

			int counter = 0;
			for (int i = 0; i < hand.size(); i++) {
				if (i + 1 < hand.size()) {
					if (hand.get(i + 1).getCardValue().getValue()
							- hand.get(i).getCardValue().getValue() == 1)
						counter++;
				}
			}

			//if not straight, then do a final check of 5 unique cards
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			if (counter != 4) {
				for (int i = 0; i < hand.size(); i++) {
					if (!map.containsKey(hand.get(i).getCardValue().getValue())) {
						map.put(hand.get(i).getCardValue().getValue(), 1);
					} else {
						map.put(hand.get(i).getCardValue().getValue(),
								map.get(hand.get(i).getCardValue().getValue()) + 1);
					}
				}

			}
			return map.size() == 5;
		}
	};

	private int value;

	private PokerHand(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}

	//we use this to compare the ranks of different poker hands
	public int isGreaterThan(PokerHand otherHand)
	{
		int rank1 = this.getValue();
		int rank2 = otherHand.getValue();
		if(rank1 == rank2) return 0;
		else if(rank1 < rank2) return 1;
		else return -1;
	}

	abstract boolean hasMatch(ArrayList<Card> hand);
}
