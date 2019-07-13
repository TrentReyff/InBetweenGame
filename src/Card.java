/**
 * Title: Card
 * Desc: represents a game card with a name and suit
 * Authors: Trent Reyff
 * Date: 7/3/2019
 */
public class Card
{
    private String cardName;
    private String cardSuit;
    private int cardValue;

    /**
     * Constructor
     * @param name name for the card
     * @param suit suit for the card
     */
    public Card(String name, String suit)
    {
        cardName = name;
        cardSuit = suit;
        cardValue = 0;              // value of card will be initialized to 0 until set
    }

    /**
     * gets card name
     * @return cardName
     */
    public String getCardName()
    {
        return cardName;
    }

    /**
     * sets value for cardName
     * @param cardName name of card to set
     */
    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    /**
     * gets card suit
     * @return cardSuit
     */
    public String getCardSuit()
    {
        return cardSuit;
    }

    /**
     * sets value for cardSuit
     * @param cardSuit suit of card to set
     */
    public void setCardSuit(String cardSuit)
    {
        this.cardSuit = cardSuit;
    }

    /**
     * gets card value
     * @return cardValue numeric value of the card
     */
    public int getCardValue()
    {
        return cardValue;
    }

    /**
     * sets value for cardValue
     * @param cardValue numeric value to set for the card
     */
    public void setCardValue(int cardValue)
    {
        this.cardValue = cardValue;
    }

    /**
     * equals method for two cards
     * @param other card to compare to
     * @return true if card has the same name and suit / false otherwise
     */
    public boolean equals(Card other)
    {
        return this.cardName.equals(other.cardName) &&
                this.cardSuit.equals(other.cardSuit);
    }

    /**
     * compares value of two cares
     * @param other card to compare to
     * @return true if cards have same value / false otherwise
     */
    public int compareTo(Card other)
    {
        if(this.cardValue > other.cardValue)
            return -1;
        else if(this.cardValue < other.cardValue)
            return 1;
        else
            return 0;
    }

    /**
     * toString method
     * @return card name and suit
     */
    public String toString()
    {
        return cardName + " of " + cardSuit;
    }
}
