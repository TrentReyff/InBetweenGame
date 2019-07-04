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
// Create an instance of Deck
// Create the following tracking variables
// int testCases
// int inBetween
// int notInBetween
// int matchesCard
// Repeat the following 3 times (step through the deck 3 times
// Shuffle the deck
// Draw card 1
// Draw card 2
// if card 1 equals card 2, skip drawing card 3.
// Draw card 3
// If card 3 is between card 1 and card 2, increment in Between
// else if card 3 is outside of card 1 and card 2, increment notInBetween
// else if card 3 equals card 1 or card 2, increment matchesCard
// End Repeat
// Display statistics. Number of comparisons, number inBetween, notInBetween and matchesCard
// Display stats (optional challenge)
}
