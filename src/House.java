import java.util.Scanner;

/**
 * Title: House
 * Desc:
 * Authors: Trent Reyff  and Khalil Tantouri
 * Date: 7/12/2019
 */
public class House
{
    public double pot;
    public Deck deck = new Deck();

    public House(double buyIn, int numPlayers)
    {
        pot = numPlayers * buyIn;
        deck.shuffle();
    }

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
        double bet = getBet(current);
        card3 = dealCard();

        if(card3 == card1 || card3 == card2)
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

    private Card dealCard()
    {
        if(!deck.hasNext())
            deck.shuffle();
        return deck.next();
    }

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

    public double getPot()
    {
        return pot;
    }

    public void setPot(double pot)
    {
        this.pot = pot;
    }
}
