import java.util.ArrayList;
public class Dealer
{   
    //this function will remove a card from the dealer give it to a player, or put it on the table 

    // dealerDeck is the deck the dealer will use to deal cards from.

    private ArrayList<Cards> dealerDeck; // the 312 cards
    private ArrayList<Cards> dealerCards; // the 2 cards we he will get.

    public Dealer(ArrayList<Cards> deck)
    {
        dealerCards = new ArrayList<Cards>();
        dealerDeck = deck;
    }

    // returns true if dealer has a natural an ace and 11 at beginning of game.
    public boolean getNatural()
    {
        if (Helper.getSumWorth(dealerCards) == 21)
        {
            return true;
        }
        return false;
    }

    public void giveTopCard(Player f, int amountOfCards)
    {
        for (int i = 0; i < amountOfCards; i++)
        {
            f.addCard(dealerDeck.get(0));
            dealerDeck.remove(0);
        }
    }

    public void giveCardToSelf(int amountOfCards)
    {
        for (int i = 0; i < amountOfCards; i++)
        {
            dealerCards.add(dealerDeck.get(0));
            dealerDeck.remove(0);
        }
    }

    public boolean checkForBust()
    {
        if (Helper.getSumWorth(dealerCards) > 21)
        {
            System.out.println("\nDealer BUSTS!");
            return true;
        }
        return false;
    }

    public Cards getFirstCard()
    {
        return dealerCards.get(0);
    }

    public String printDeck()
    {
        String str = "";
        if (dealerCards.size() > 0)
            for (Cards i: dealerCards)
                str += i.toString();
        return str + " With a total worth of " + Helper.getSumWorth(dealerCards);
    }

    public int getWorth()
    {
        return Helper.getSumWorth(dealerCards);
    }

    // gives card to the player instead of moving card to the table.
}