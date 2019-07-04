import java.util.Iterator;
import java.util.Random;

public class Deck implements Iterator
{
    String[] names = {"2", "3", "4", "5", "6", "7", "8",
        "9", "10", "Jack", "Queen", "King", "Ace"};
    String[] suits =  {"Clubs", "Diamonds", "Hearts", "Spades"};
    Card[] cardDeck = new Card[52];
    int remainingCards;

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
    }

    public int getRemainingCards()
    {
        return remainingCards;
    }


    public String toString()
    {
        String output = "";
        for(int i = 0; i < cardDeck.length; i++)
            output += cardDeck[i].toString() + "\n";
        return output;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext()
    {
        return remainingCards >= 1;
    }


    @Override
    public Card next()
    {
        return cardDeck[cardDeck.length - remainingCards--];
    }

    public boolean hasCard(Card other)
    {
        for(int i = cardDeck.length - remainingCards; i < cardDeck.length;i++)
            if(cardDeck[i].equals(other))
                return true;
        return false;
    }
}
