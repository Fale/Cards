package cc.locati.cards.libs.poker;

import java.util.ArrayList;

import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Hand;

/**
 * Created by fale on 10/04/14.
 */
public class PokerAI {

    /**
     * Keep here the hand we are analyzing
     */
    Hand hand;

    /**
     * Store the AI level
     */
    int level;

    /**
     * Initialization with basic AI (level 1)
     * @param h Hand to be analized
     */
    public PokerAI(Hand h) {
        this(h, 1);
    }

    /**
     * Initialization
     * @param h Hand to be analized
     * @param AILevel Level of AI (1 = default), only '1' implemented now
     */
    public PokerAI(Hand h, int AILevel) {
        hand = h;
        level = AILevel;
    }

    /**
     * Get the cards to keep. Probably this is the single function of this class most people
     * will use.
     * @return list of cards to Keep (AKA: trash all the other cards)
     */
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
     * Identify pairs and double pairs.
     * @return cards of the pair(s)
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
     * @return cards of the triple
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
     * @return cards of the poker
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
