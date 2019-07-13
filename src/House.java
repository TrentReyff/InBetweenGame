import java.util.Scanner;

/**
 * Title: House
 * Desc: represents the house that controls the card game
 * Authors: Trent Reyff  and Khalil Tantouri
 * Date: 7/12/2019
 */
public class House
{
    private double pot;
    private Deck deck = new Deck();

    /**
     * constructor - sets pot and shuffles deck
     * @param buyIn amount each player must give to the pot to play
     * @param numPlayers number of participating players
     */
    public House(double buyIn, int numPlayers) // added numPlayers to make it easier to determine amount in pot
    {
        pot = numPlayers * buyIn;
        deck.shuffle();
    }

    /**
     * plays one turn of InBetween
     * if card3 is not inbetween card1 and card2 the player loses the bet
     * if card3 is equal to card2 or card1 the player loses double the bet
     * if card3 is in in between card1 and card2 the player wins the bet
     * @param current player who's turn it is
     */
    public void playHand(Person current)
    {
        Card card1, card2, card3;
        if(!current.isActivePlayer())
            return;
        do
        {
            card1 = dealCard();
            card2 = dealCard();
        }while(card1.compareTo(card2) == 0);
        System.out.println(card1 + " and " + card2);
        double bet = getBet(current);
        card3 = dealCard();
        System.out.println(card3);
        if(card3.compareTo(card1) == 0 || card3.compareTo(card2) == 0)
        {
            pot += bet * 2;
            current.setBankroll(bet * -2);
        }
        else if((card3.compareTo(card2) < 0 && card3.compareTo(card1) < 0)
                || (card3.compareTo(card2) > 0 && card3.compareTo(card1) > 0))
        {
            pot += bet;
            current.setBankroll(-1 * bet);
        }
        else
        {
            pot -= bet;
            current.setBankroll(bet);
        }
    }

    /**
     * deals the next card in the deck / shuffles the deck if none remain
     * @return the next card available
     */
    private Card dealCard()
    {
        if(!deck.hasNext())
            deck.shuffle();
        return deck.next();
    }

    /**
     * asks player how much they want to bet and keeps trying until a valid bet is given
     * @param player person who is betting
     * @return the valid bet that is input
     */
    private Double getBet(Person player)
    {
        Scanner s = new Scanner(System.in);
        double bet;
        do
        {
            System.out.println("How much will you bet?");
            bet = s.nextDouble();
            try
            {
                player.setBet(bet);
            }
            catch (IllegalBetException e)
            {
                System.out.println("ENTER VALID BET");
            }
        }while(bet < 0 || bet > player.getBankroll());
        return bet;
    }

    /**
     * gets the total pot
     * @return pot
     */
    public double getPot()
    {
        return pot;
    }

    /**
     * sets pot
     * @param pot value for the pot to hold
     */
    public void setPot(double pot) // probably never useful unless a new round is started
    {
        this.pot = pot;
    }
}
