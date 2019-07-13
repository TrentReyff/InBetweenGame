/**
 * Title: Deck Driver
 * Desc:
 * Authors: Trent Reyff
 * Date: 7/3/2019
 */
public class DeckDriver
{
    public static void main(String[] args)
    {
        Deck d = new Deck();
        int testCases = 0;
        int inBetween = 0;
        int notInBetween = 0;
        int matchesCard = 0;
        Card card1, card2, card3;
        d.shuffle();
        while(d.hasNext() && testCases < 3)
        {
            card1 = d.next();
            card2 = d.next();
            if(card1.compareTo(card2) == 0)
                continue;
            System.out.println("Card 1: " + card1 + "   Card 2: " + card2);
            card3 = d.next();
            System.out.println("Card 3: " + card3);
            if(card3.compareTo(card1) == 0 || card3.compareTo(card2) == 0)
                matchesCard++;
            else if((card3.compareTo(card1) == -1 && card3.compareTo(card2) == -1)
                    || (card3.compareTo(card1) == 1 && card3.compareTo(card2) == 1))
            {
                notInBetween++;
            }
            else
                inBetween++;

            testCases++;
        }
        System.out.println();
        System.out.println("Cards in between: " + inBetween);
        System.out.println("Cards not in between: " + notInBetween);
        System.out.println("Cards matched: " + matchesCard);
    }
}
