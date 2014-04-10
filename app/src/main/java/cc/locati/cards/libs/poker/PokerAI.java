package cc.locati.cards.libs.poker;

import java.util.ArrayList;

import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Hand;

/**
 * Created by fale on 10/04/14.
 */
public class PokerAI {

    Hand hand;

    public PokerAI(Hand h) {
        hand = h;
    }

    public ArrayList<Card> getCardsToKeep() {
        if (!poker().isEmpty())
            return poker();
        // Full here
        if (!triple().isEmpty())
            return triple();
        if (!pairs().isEmpty())
            return pairs();
        // Colors and scales are to be checked yet
        // If nothing goes right, we will have to choose a single card to keep (max change is 4)
        ArrayList<Card> cardsToKeep = new ArrayList<Card>();
        return cardsToKeep;
    }

    /**
     * Identify double pair.
     * @return number of paris in the hand
     */
    public ArrayList<Card> pairs() {
        hand.sortByValue();
        ArrayList<Card> importantCards = new ArrayList<Card>();
        for (int card = 1; card < hand.getCardCount(); card++) {
            if (hand.getCard(card) == hand.getCard(card - 1)) {
                importantCards.add(hand.getCard(card));
                importantCards.add(hand.getCard(card - 1));
                card++;
            }
        }
        return importantCards;
    }

    /**
     * Identify triple.
     * @return number of paris in the hand
     */
    public ArrayList<Card> triple() {
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
     * @return number of paris in the hand
     */
    public ArrayList<Card> poker() {
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
