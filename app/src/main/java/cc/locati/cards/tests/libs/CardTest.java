package cc.locati.cards.tests.libs;

import android.test.InstrumentationTestCase;

import cc.locati.cards.libs.Card;

/**
 * Created by fale on 11/04/14.
 */
public class CardTest extends InstrumentationTestCase {

    public void testCreator() throws Exception {
        Card card;
        card = new Card(5, 0, 0);
        assertEquals(card.toString(), "5 of Hearts");
    }

}
