package cc.locati.cards.libs.poker;

import java.util.ArrayList;

import cc.locati.cards.HandAnalyzer;
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
        HandAnalyzer analyzer = new HandAnalyzer(hand);
        if (hand.getCountPokers() > 0)
            return analyzer.pokers(hand);
        // Full here
        if (hand.getCountTriples() > 0)
            return analyzer.triples(hand);
        //if (hand.getCountPairs() > 0)
            //return analyzer.pairs(hand);
        // Colors and scales are to be checked yet
        // If nothing goes right, we will have to choose a single card to keep (max change is 4)
        ArrayList<Card> cardsToKeep = new ArrayList<Card>();
        return cardsToKeep;
    }


}
