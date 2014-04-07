package cc.locati.cards.libs.tests;

import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Deck;

/**
 * This program lets check all cards in a 2 decks deck drawing them all.
 */

public class AllCards {

    public static void main(String[] args) {

        System.out.println("This program lets you see all the cards in 2 decks.");
        System.out.println();

        Deck deck = new Deck(2, false);
        Card currentCard;
        deck.shuffle();
        int count = 0;
        while (deck.cardsLeft() > 0) { // Loop to print all cards
            count++;
            currentCard = deck.dealCard();
            System.out.println(count + " The next card is " + currentCard + " of deck " + currentCard.getDeck());
        } // end of while loop

    }  // end main()

} // end class AllCards