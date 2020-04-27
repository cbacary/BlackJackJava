import java.util.ArrayList;

public class Player {
    private ArrayList<Cards> cards;
    private int chips;
    private String name;
    private static int turnOrder;
    private boolean busted;
    private int wager;
    private boolean skipForNatural;
    // this is constructor for an actual player.
    public Player()
    {
        turnOrder++;
        skipForNatural = false;
        busted = false;
        name = "Player " + (turnOrder);
        chips = 1000; // all players start with 1k
        cards = new ArrayList<Cards>();
    }

    public String printStats()
    {
        String str = name + ": has ";
        if (cards.size() > 0)
            for (Cards i: cards)
            {str += i + " |||| ";}
        str += " with a total worth of " + Helper.getSumWorth(cards) + ". " + name + " has " + chips;
        return str;
    }

    public boolean playerCheckForBust()
    {
        if (Helper.getSumWorth(cards) > 21)
        {
            busted = true;
            System.out.println("\nYou BUST!\n");
            System.out.println( "you pulled a " + cards.get(cards.size() - 1) + " with a total worth of " + Helper.getSumWorth(cards));
            return true;
        }
        System.out.println("You survived");
        System.out.println( "you pulled a " + cards.get(cards.size() - 1) + " with a total worth of " + Helper.getSumWorth(cards));
        return false;
    }

    public void reset()
    {
        skipForNatural = false;
        busted = false;
        cards = new ArrayList<Cards>();
        if (chips == 0)
        {
            chips = 200;
            System.out.println(name + " has bought in and now has 200 chips.");
        }
    }

    public boolean getBust()
    {
        return busted;
    }

    public void setSkip(boolean x)
    {
        skipForNatural = x;
    }

    public boolean getSkip()
    {
        return skipForNatural;
    }
    

    public int getWager()
    {
        return wager;
    }

    public void setWinAmount(int winAmount)
    {
        chips += winAmount;
    }

    public boolean getNatural()
    {
        for (Cards i: cards)
        {
            if (Helper.getSumWorth(cards) == 21)
            {
                return true;
            }
        }
        return false;
    }
    // return false means bet was to large, they don't have enough money.
    public boolean bet(int betSize)
    {
        if (chips - betSize < 0 || betSize > 1000 || betSize < 2)
        {
            return false;
        }
        else
        {
            wager = betSize;
            chips -= betSize;
            return true;
        }
    }

    public void addCard(Cards card)
    {
        cards.add(card);
    }

    public ArrayList<Cards> getCards()
    {
        return cards;
    }

    public String getName()
    {
        return name;
    }

    public int getChips()
    {
        return chips;
    }
}