import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class BlackJack
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<Player>();

        
        // creating players
        // protecting from pesky people who input strings instead of int
        System.out.print("How many players are in this game (int): ");
        while (scanner.hasNextInt() == false) 
        {
            scanner.next();
            System.out.print("How many players are in this game (int): ");
        }
        int playerAmount = scanner.nextInt();
        System.out.println("\n");
        for (int i = 0; i < playerAmount; i++)
            {players.add(new Player());}
        // game starts here
        // main was getting to messy so I split the entire program into many functions
        startGame(players);
    }

    public static void startGame(ArrayList<Player> players)
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Cards> deck = new ArrayList<Cards>();
            Helper.createDeck(deck); // fills the deck with 312 cards or something all shuffled
            Dealer dealer = new Dealer(deck); // initializing dealer

            for (Player i: players)
            {
                System.out.println(i.printStats());
            }

            // beginning wagers starts here
            System.out.println("\n\nBetting will begin... Minimum bet is 2$ Maximum bet is 1000$\n\n");
            startBetting(players);

            // Dealing starts here
            dealer.giveCardToSelf(2);
            for (Player i: players)
            {
                dealer.giveTopCard(i, 2);
            }
            
            // print info
            System.out.println("\n\nDealer: " + dealer.getFirstCard() + ", an unknown card\n");
            for (Player i: players)
            {
                System.out.println(i.printStats());
                System.out.println();
            }

            // checking for natural.
            if (naturalCheck(players, dealer))
            {
                continue;
            }

            // check if they want to stand or hit  
            System.out.print("\n\nBeginning Play Stage...");
            playStage(players, dealer);
            
            // now I think all we have to do is have dealer go.
            playDealer(players, dealer);
            // resetting everyone.
            for (Player i: players)
            {
                i.reset();
            }
        }
    }

    public static void playDealer(ArrayList<Player> players, Dealer dealer)
    {
        while (dealer.getWorth() <= 16)
        {
            dealer.giveCardToSelf(1);
        }
        System.out.print("Dealer has: "); dealer.printDeck();
        System.out.println();
        if (dealer.checkForBust()) // if dealer busted
        {
            for (Player i: players)
            {
                if (!i.getBust())
                {
                    System.out.println(i.getName() + " just won " + i.getWager() * 2 + " chips!");
                    i.setWinAmount(i.getWager() * 2);
                }
                else 
                    System.out.println(i.getName() + " busted so he gets nothing");
            }
        }
        else
        {
            for (Player i: players)
            {
                if (i.getBust())
                {
                    System.out.println(i.getName() + " lost because they busted ");
                    continue;
                }
                else if (Helper.getSumWorth(i.getCards()) >  dealer.getWorth())
                {
                    System.out.println(i.getName() + " just won " + i.getWager() * 2 + " chips!");
                    i.setWinAmount(i.getWager() * 2);
                }
                else if (Helper.getSumWorth(i.getCards()) ==  dealer.getWorth())
                {
                    System.out.println(i.getName() + " get your chips back!");
                    i.setWinAmount(i.getWager());
                }
                else if (Helper.getSumWorth(i.getCards()) < dealer.getWorth())
                {
                    System.out.println(i.getName() + " you lose all your chips.");
                }
            }
        }
    }

    public static void playStage(ArrayList<Player> players, Dealer dealer)
    {
        Scanner scanner = new Scanner(System.in);
        for (Player i: players)
        {
            String play = "";
            while (!play.toLowerCase().equals("stand"))
            {
                i.printStats();
                System.out.print("\n" + i.getName() + " would you like to 'stand' or 'hit': ");
                play = scanner.nextLine();
                if (play.toLowerCase().equals("hit"))
                {
                    try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
                    dealer.giveTopCard(i, 1);
                    if (i.playerCheckForBust())
                    {
                        play = "stand";
                        continue;
                    }
                }
            }
        }
        System.out.println("\n"); // stuff to make it look nice.
        try{Thread.sleep(1200);}catch(InterruptedException e){System.out.println(e);}  
    }

    public static void startBetting(ArrayList<Player> players)
    {
        Scanner scanner = new Scanner(System.in);
        for (Player i: players)
        {
            int temp = 0;
            System.out.print(i.getName() + " place your bet: ");
            while (scanner.hasNextInt() == false || !i.bet(temp)) 
            {
                if (scanner.hasNextInt() == true)
                {
                    temp = scanner.nextInt();
                    if (i.bet(temp))
                    {
                        break;
                    }
                }
                else{scanner.next();}
                
                System.out.print(i.getName() + " place your bet: ");
            }
            // this is kinda wierd but since I do i.bet(betsize) it already subtracts the chips from the user if it returns true so we don't have to call it later 
            // while (i.bet(betSize) == false || betSize < 2 || betSize > 1000) //making sure their bet follows the rules
            // {
            //     scanner.next();
            //     System.out.print(i.getName() + " MIND THE RULES, place your bet: ");
            // }
        }
    }

    // returns true if it should continue cause dealer had natural
    public static boolean naturalCheck(ArrayList<Player> players, Dealer dealer)
    {
        Scanner scanner = new Scanner(System.in);
        boolean setContinue = false;
        if (dealer.getNatural())
        {
            setContinue = true;
            System.out.println("\nDealer has a natural.");
            // check for player natural
            for (Player i: players)
            {
                if (i.getNatural())
                {
                    System.out.println(i.getName() + " also has a natural, you get your chips back" );
                    i.setWinAmount(i.getWager());
                }
                else
                {
                    System.out.println(i.getName() + " Sorry you lose your money. ");
                }
            }
            return true;
        }
        // checking for player natural
        else
        {
            boolean oneNatural = false;
            for (Player i: players)
            {
                oneNatural = true;
                if (i.getNatural())
                {
                    System.out.println(i.getName() + " has a natural, you get " + (int) (i.getWager() * 1.5));
                    i.setWinAmount((int) (i.getWager() * 1.5));
                    i.setSkip(true);
                }
            }
        }
        return false;
    }

}