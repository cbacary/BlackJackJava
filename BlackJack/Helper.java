import java.util.ArrayList;
import java.lang.Math;
// called helper for now might change
public class Helper 
{
    // in black jack the dealer will shuffle multiple decks, generally 6.
    // apparently there is some plastic card, to allow the dealer to know when to reshuffle, but we are not going to use that.
    public static void createDeck(ArrayList<Cards> deck)
    {
        for (int y = 0; y < 6; y++)
        {    for (int i = 2; i < 11; i++)
            {
                deck.add(new Cards("Clubs", "" + i, i)); // we have "" + i to cast i as a string basically so I dont get error.
                deck.add(new Cards("Diamond", "" + i, i));
                deck.add(new Cards("Hearts", "" + i, i));
                deck.add(new Cards("Spades", "" + i, i));
            }
            addSpecialCards(deck);
        }
        shuffleDeck(deck);
    }

    public static int getSumWorth(ArrayList<Cards> cards)
    {
        int sum = 0;
        for (Cards i: cards)
            sum += i.getCardWorth();
        return sum;
    }

    public static void addSpecialCards(ArrayList<Cards> cards)
    {
        // decalaring Aces
        cards.add(new Cards("Clubs", "Ace", 11));
        cards.add(new Cards("Diamonds", "Ace", 11));
        cards.add(new Cards("Hearts", "Ace", 11));
        cards.add(new Cards("Spades", "Ace", 11));
        // declaring Jacks
        cards.add(new Cards("Clubs", "Jack", 10));
        cards.add(new Cards("Diamonds", "Jack", 10));
        cards.add(new Cards("Hearts", "Jack", 10));
        cards.add(new Cards("Spades", "Jack", 10));
        // Decalaring Queens
        cards.add(new Cards("Clubs", "Queen", 10));
        cards.add(new Cards("Diamonds", "Queen", 10));
        cards.add(new Cards("Hearts", "Queen", 10));
        cards.add(new Cards("Spades", "Queen", 10));
        // Decalaring Kings
        cards.add(new Cards("Clubs", "King", 10));
        cards.add(new Cards("Diamonds", "King", 10));
        cards.add(new Cards("Hearts", "King", 10));
        cards.add(new Cards("Spades", "King", 10));
    }

    public static void shuffleDeck(ArrayList<Cards> deck)
    {
        // We are going to shufLE
        // go through each card move it to a random location in the deck, go to next card and do the same.

        for (int i = 0; i < deck.size(); i++)
        {
            int randomNumber  = (int) (Math.random() * (((deck.size() - 1) - 0) + 1)) + 0;
            Cards temp = new Cards(deck.get(randomNumber).getType(), deck.get(randomNumber).getCardNumber(), deck.get(randomNumber).getCardWorth());
            deck.set(randomNumber, deck.get(i));
            deck.set(i, temp);
        }
    }
}