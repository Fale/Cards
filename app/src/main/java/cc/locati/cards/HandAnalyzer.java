package cc.locati.cards;

import java.sql.Array;
import java.util.ArrayList;

import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Hand;

/**
 * Created by fale on 10/04/14.
 */
public class HandAnalyzer {


    Hand hand;

    public HandAnalyzer(Hand h) {
        hand = h;
    }

    /**
     * Identify triple.
     * @return cards of the triple
     */
    public ArrayList<Card> triples(Hand hand) {
        hand.sortByValue();
        ArrayList<Card> importantCards = new ArrayList<Card>();
        for (int card = 2; card < hand.getCardCount(); card++) {
            if (hand.getCard(card) == hand.getCard(card - 1) &&
                    hand.getCard(card) == hand.getCard(card - 2)) {
                importantCards.add(hand.getCard(card));
                importantCards.add(hand.getCard(card - 1));
                importantCards.add(hand.getCard(card - 2));
            }
        }
        return importantCards;
    }

    /**
     * Identify poker.
     * @return cards of the poker
     */
    public ArrayList<Card> pokers(Hand hand) {
        hand.sortByValue();
        ArrayList<Card> importantCards = new ArrayList<Card>();
        for (int card = 3; card < hand.getCardCount(); card++) {
            if (hand.getCard(card) == hand.getCard(card - 1) &&
                    hand.getCard(card) == hand.getCard(card - 2) &&
                    hand.getCard(card) == hand.getCard(card - 3)) {
                importantCards.add(hand.getCard(card));
                importantCards.add(hand.getCard(card - 1));
                importantCards.add(hand.getCard(card - 2));
                importantCards.add(hand.getCard(card - 3));
            }
        }
        return importantCards;
    }
}
