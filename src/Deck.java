import java.util.Iterator;
import java.util.Random;

/**
 * Title: Deck
 * Desc: A deck of 52 cards containing 1 of every name and suit combination
 * Authors: Trent Reyff
 * Date: 7/3/2019
 */
public class Deck implements Iterator
{
    String[] names = {"2", "3", "4", "5", "6", "7", "8",
              "9", "10", "Jack", "Queen", "King", "Ace"};
    String[] suits =  {"Clubs", "Diamonds", "Hearts", "Spades"};
    Card[] cardDeck = new Card[52];
    int remainingCards;

    /**
     * Constructor - creates one of each combination and sets remainingCards to 52
     */
    public Deck()
    {
        int i = 0;
        int count = 0;
        do
        {
            for (int j = 0; j < 13; j++)
            {
                cardDeck[count] = new Card(names[j], suits[i]);
                cardDeck[count].setCardValue(j + 2);
                count++;
            }
            i++;
        }while(i <4);
        remainingCards = 52;
    }

    /**
     * Shuffles cards in deck to random order
     */
    public void shuffle()
    {
        Random rnd = new Random();
        Card temp = new Card("","");
        for (int i = 0; i < cardDeck.length; i++)
        {
            int index = rnd.nextInt(cardDeck.length);
            temp.setCardName(cardDeck[index].getCardName());
            temp.setCardSuit(cardDeck[index].getCardSuit());
            temp.setCardValue(cardDeck[index].getCardValue());
            cardDeck[index].setCardName(cardDeck[i].getCardName());
            cardDeck[index].setCardSuit(cardDeck[i].getCardSuit());
            cardDeck[index].setCardValue(cardDeck[i].getCardValue());
            cardDeck[i].setCardName(temp.getCardName());
            cardDeck[i].setCardSuit(temp.getCardSuit());
            cardDeck[i].setCardValue(temp.getCardValue());
        }
        remainingCards = 52;
    }

    /**
     * gets cards left available
     * @return remaining cards in deck
     */
    public int getRemainingCards()
    {
        return remainingCards;
    }

    /**
     * toString method for deck
     * @return the name and suit of every card
     */
    public String toString()
    {
        String output = "";
        for(int i = 0; i < cardDeck.length; i++)
            output += cardDeck[i].toString() + "\n";
        return output;
    }

    /**
     * checks to see if there are any remaining cards
     * @return true if there are 1 or more remaining cards / false otherwise
     */
    public boolean hasNext()
    {
        return remainingCards >= 1;
    }

    /**
     * the next card in the deck
     * @return the next card
     */
    public Card next()
    {
        return cardDeck[cardDeck.length - remainingCards--];
    }

    /**
     * searches for target card in deck
     * @param other target card to find in deck
     * @return true if target card is found / false otherwise
     */
    public boolean hasCard(Card other)
    {
        for(int i = cardDeck.length - remainingCards; i < cardDeck.length;i++)
            if(cardDeck[i].equals(other))
                return true;
        return false;
    }
}
