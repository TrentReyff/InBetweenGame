import java.util.Scanner;

/**
 * Title: Game Driver
 * Desc: Imitates an InBetween game with one player
 *        Used to test House and Person classes
 */
public class GameDriver
{
    public static void main(String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        House house;
        Person player1;
        String player1Name;
        String input;
        boolean done = false;

        System.out.println("What is your name?");
        player1Name = stdin.nextLine();
        System.out.println("You start with $100. The buyin is $20. Do you wish to play? [Y/N]");
        input = stdin.nextLine();
        if (!input.equals("Y") && !input.equals("y"))
        {
            System.out.println("OK, next time");
        }
        else
        {
            player1 = new Person(player1Name,100);
            player1.setBuyIn(20);
            house = new House(20, 1);
            do
            {
                house.playHand(player1);
                if (!player1.isActivePlayer())
                    done = true;
                else if (house.getPot() <= 0)
                    done = true;
                else
                {
                    System.out.println("The pot is now at " + house.getPot());
                    System.out.println("Player hand: " + player1.getBankroll());
                    System.out.println("Press return to continue or any key to quit");
                    input = stdin.nextLine();
                    if (input.length() > 0)
                        done = true;
                }
            } while (!done);
            if (player1.getBankroll() <= 0)
                System.out.println("Loser");
            else if (house.getPot() <= 0)
                System.out.println("Winner");
        }
    }
}
