public class Card
{
    private String cardName;
    private String cardSuit;
    private int cardValue;


    public Card(String name, String suit)
    {
        cardName = name;
        cardSuit = suit;
        cardValue = 0;              // value of card will be initialized to 0 until set
    }

    public String getCardName()
    {
        return cardName;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    public String getCardSuit()
    {
        return cardSuit;
    }

    public void setCardSuit(String cardSuit)
    {
        this.cardSuit = cardSuit;
    }

    public int getCardValue()
    {
        return cardValue;
    }

    public void setCardValue(int cardValue)
    {
        this.cardValue = cardValue;
    }

    public boolean equals(Card other)
    {
        return this.cardName.equals(other.cardName) &&
                this.cardSuit.equals(other.cardSuit);
    }

    public int compareTo(Card other)
    {
        if(this.cardValue > other.cardValue)
            return -1;
        else if(this.cardValue < other.cardValue)
            return 1;
        else
            return 0;
    }

    public String toString()
    {
        return cardName + " of " + cardSuit;
    }


}
