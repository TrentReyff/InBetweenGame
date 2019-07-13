/**
 * Title: Person
 * Desc: Represents a person who is playing the game
 * Authors: Khalil Tantouri
 * Date: 7/10/2019
 */
class Person
{
    private String name;
    private double bankroll;
    private double bet;
    private boolean activePlayer;

    /**
     * Constructor
     * @param name name of person
     * @param bankroll initial bankroll
     */
    public Person(String name,double bankroll)
    {
        this.name=name;
        this.bankroll=bankroll;
    }

    /**
     * adds amount to bankroll / if bankroll reaches 0 the person is no longer active
     * @param amount amount to add to bankroll
     */
    public void setBankroll(double amount)
    {
        this.bankroll += amount;
        if(this.bankroll <= 0)
            this.activePlayer = false;
    }

    /**
     * sets active player status
     * @param b used to change status of active player
     */
    public void setActivePlayer(boolean b)
    {
        this.activePlayer = b;
    }

    /**
     * takes buyIn out of bankroll and sets person to active
     * @param amount buyIn amount
     */
    public void setBuyIn(double amount)
    {
        this.bankroll -= amount;
        this.activePlayer=true;
    }

    /**
     * sets bet / throws exception if bet is illegal
     * @param amount amount to bet
     * @throws IllegalBetException exception thrown if bet is <= 0 or > bankroll
     */
    public void setBet(double amount) throws IllegalBetException
    {
        if(amount <= 0)
            throw new IllegalBetException("Bet must be greater than zero");
        else if(amount > this.getBankroll())
            throw new IllegalBetException("Bet can't be more than in bankroll");
        else
            this.bet = amount;
    }

    /**
     * gets person's name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * gets person's bankroll
     * @return bankroll
     */
    public double getBankroll()
    {
        return bankroll;
    }

    /**
     * gets person's bet
     * @return bet
     */
    public double getBet()
    {
        return bet;
    }

    /**
     * checks person's active player status
     * @return activePlayer bool
     */
    public boolean isActivePlayer()
    {
        return activePlayer;
    }

    /**
     * equals method comparing person name, bankroll, bet, and activePlayer
     * @param p2 person to compare to
     * @return true if name, bankroll, bet, and activePlayer are the same / false otherwise
     */
    public boolean equals(Person p2)
    {
        if((this.name == p2.name) && (this.bankroll == p2.bankroll)
                && (this.bet == p2.bet) && (this.activePlayer == p2.activePlayer))
            return true;
        else
            return false;
    }

    /**
     * compares bankrolls
     * @param p2 person to compare to
     * @return 1 if person's bankroll is > p2 bankroll | 0 if they are equal
     *          | -1 if < p2 bankrollS
     */
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
