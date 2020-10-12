import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack {

    Scanner input = new Scanner(System.in);

    ArrayList<Card> deck;
    ArrayList<Card> playerCards;
    ArrayList<Card> dealerCards;

    private void log(String msg) {
        System.out.println(msg);
    }

    private void printCards(ArrayList<Card> cards) {
        printCards(cards, 0); // print with zero hidden cards by default
    }

    private void printCards(ArrayList<Card> cards, int hiddenCards) {
        for (Card card : cards) {
            if (hiddenCards == 0) {
                log(card.getRank() + " of " + card.getSuit());
            } else {
                log("face down card (hidden)");
                hiddenCards--;
            }
        }
    }

    private Card dealCard() {
        return deck.remove(deck.size()-1);
    }

    private int calcSum(ArrayList<Card> cards) {
        int sum = 0;
        int numOfAces = 0;
        for (Card card : cards) {
            if (card.rank == 1) {
                // if card is an Ace
                numOfAces++;
            } else if (card.rank < 11) {
                // if card is numbered
                sum += card.rank;
            } else {
                // if card is a King, Queen, or Jack
                sum += 10;
            }
        }

        // add up the aces after getting the sum
        // of the other cards
        for (int i = 0; i < numOfAces; i++) {
            if (sum + 11 <= 21) {
                sum += 11;
            } else {
                sum += 1;
            }
        }

        return sum;
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public void playGame() {
        // shuffle deck before starting a game
        shuffleDeck();
        // deal to the dealer
        dealerCards.add(dealCard());
        dealerCards.add(dealCard());

        // deal to the player
        playerCards.add(dealCard());

        // Player's turn
        int playerSum = 0;
        String choice = "hit";
        while (choice.equals("hit")) {
            // hit player
            playerCards.add(dealCard());
            playerSum = calcSum(playerCards);
            // print the player's hand
            log("Player's hand sum: " + playerSum);
            printCards(playerCards);
            log("");
            // check if player busted
            if (playerSum > 21) {
                log("You busted with " + playerSum + ". Game Over!");
                return;
            }
            // print the dealer's hand, one card hidden
            log("Dealer's hand: ");
            printCards(dealerCards, 1);
            log("");
            log("Hit or Stay?");
            choice = input.nextLine().toLowerCase();
            log("");
        }

        // Dealer's turn
        int dealerSum = calcSum(dealerCards);
        while (dealerSum < 16) {
            dealerCards.add(dealCard());
            dealerSum = calcSum(dealerCards);
            if (dealerSum > 21) {
                // show the dealer's hand fully
                log("Dealer's hand sum: " + dealerSum);
                printCards(dealerCards);
                log("Dealer busted with " + dealerSum + ". You Won!");
                return;
            }
        }

        // Neither player nor dealer busted
        // The hand sums will need to be
        // compared to determine the winner

        // show the player's hand
        log("Player's hand sum: " + playerSum);
        printCards(playerCards);
        log("");
        // show the dealer's hand fully
        log("Dealer's hand sum: " + dealerSum);
        printCards(dealerCards);
        log("");
        // who won?
        if (dealerSum > playerSum) {
            log("Dealer won with a higher sum :(");
        } else if (dealerSum == playerSum) {
            log("Dealer won with the same sum as you :(");
        } else {
            log("You won! :)");
        }
    }

    private void createDeck() {
        deck = new ArrayList<Card>();
        char[] suits = new char[] {'H','D','C','S'};
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, suits[j]);
                deck.add(card);
            }
        }
    }

    private void addCardsToDeck(ArrayList<Card> cards) {
        for (int i = cards.size()-1; i >= 0; i--){
            deck.add(cards.remove(i));
        }
    }

    public void play() {
        log("");

        // create the deck of 52 cards
        createDeck();
        playerCards = new ArrayList<Card>();
        dealerCards = new ArrayList<Card>();

        String choice = "yes";
        // while player chooses to play again
        while (choice.equals("yes")) {
            // after playing a game add the cards
            // from the hands back to the deck
            // if a game hasn't been played yet
            // these methods won't do anything
            // cause the hands are empty
            addCardsToDeck(playerCards);
            addCardsToDeck(dealerCards);

            playGame();
            // after the game ends prompt the user
            log("Play another game of BlackJack? Input 'yes'/'no'");
            choice = input.nextLine().toLowerCase();
        }
        log("Thanks for playing! Bye!");
    }

}
