
public class Cards 
{
    private String type; // can be 4 different Clubs, Diamonds, Hearts, Spades.
    private String cardNumber; // reason this is a string is because ace, jack, Queen, and King.
    private int cardWorth;
    public Cards(String type, String cardNumber, int cardWorth)
    {
        this.cardWorth = cardWorth;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    /**
     * @return the type
     */

    public int getCardWorth()
    {
        return cardWorth;
    }

    public String getType() 
    {
        return type;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String toString()
    {
        return type + " " + cardNumber + ", worth " + cardWorth + " ";
    }
}