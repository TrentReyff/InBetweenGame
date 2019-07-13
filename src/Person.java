/**
 * Title: Person
 * Desc:
 * Authors: Khalil Tantouri
 * Date: 7/10/2019
 */
class Person
{
    private String name;
    private double bankroll;
    private double bet;
    private boolean activePlayer;

    public Person(String name,double bankroll)
    {
        this.name=name;
        this.bankroll=bankroll;
    }

    public void setBankroll(double amount)
    {
        this.bankroll += amount;
        if(this.bankroll <= 0)
            this.activePlayer = false;
    }

    public void setActivePlayer(boolean b)
    {
        this.activePlayer = b;
    }

    public void setBuyIn(double amount)
    {
        this.bankroll -= amount;
        this.activePlayer=true;
    }

    public void setBet(double amount) throws IllegalBetException
    {
        if(amount <= 0)
            throw new IllegalBetException("Bet must be greater than zero");
        else if(amount > this.getBankroll())
            throw new IllegalBetException("Bet can't be more than in bankroll");
        else
            this.bet = amount;
    }

    public String getName()
    {
        return name;
    }

    public double getBankroll()
    {
        return bankroll;
    }

    public double getBet()
    {
        return bet;
    }

    public boolean isActivePlayer()
    {
        return activePlayer;
    }

    public boolean equals(Person p2)
    {
        if((this.name == p2.name) && (this.bankroll == p2.bankroll)
                && (this.bet == p2.bet) && (this.activePlayer == p2.activePlayer))
            return true;
        else
            return false;
    }

    public int compareTo(Person p2)
    {
        if(this.bankroll > p2.bankroll)
            return 1;
        else if(this.bankroll < p2.bankroll)
            return -1;
        else
            return 0;
    }
}
