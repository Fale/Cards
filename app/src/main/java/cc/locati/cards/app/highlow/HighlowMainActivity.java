package cc.locati.cards.app.highlow;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.caverock.androidsvg.SVGImageView;

import cc.locati.cards.app.R;
import cc.locati.cards.libs.cards.Card;
import cc.locati.cards.libs.cards.Deck;

public class HighlowMainActivity extends ActionBarActivity {

    int correctGuesses;

    Deck deck;

    Card currentCard;
    Card nextCard;

    TextView comments;
    TextView points;
    TextView currentCardImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlow_main);

        deck = new Deck();
        deck.shuffle();
        currentCard = deck.dealCard();
        nextCard = deck.dealCard();

        comments = (TextView)findViewById(R.id.textComments);
        points = (TextView)findViewById(R.id.points);

/*        SVGImageView currentCardImage = (SVGImageView)findViewById(R.id.imageView);
        currentCardImage.setImageAsset(currentCard.getAsset());*/
        currentCardImage = (TextView)findViewById(R.id.card);
        currentCardImage.setText(currentCard.getAsset());

        final Button buttonHigher = (Button) findViewById(R.id.buttonHigher);
        buttonHigher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('H');
            }
        });

        final Button buttonLower = (Button) findViewById(R.id.buttonLower);
        buttonLower.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('L');
            }
        });
    }

    private void makeChoice(char choice) {
        if (nextCard.getValue() == currentCard.getValue()) {
            comments.setText("The value is the same as the previous card.");
            comments.setText("You lose on ties.  Sorry!");
        }
        else if (nextCard.getValue() > currentCard.getValue()) {
            if (choice == 'H') {
                comments.setText("Your prediction was correct.");
                correctGuesses++;
                points.setText(Integer.toString(correctGuesses));
                drawAnotherCard();
            }
            else {
                comments.setText("Your prediction was incorrect.");
            }
        }
        else {  // nextCard is lower
            if (choice == 'L') {
                comments.setText("Your prediction was correct.");
                correctGuesses++;
                points.setText(Integer.toString(correctGuesses));
                drawAnotherCard();
            }
            else {
                comments.setText("Your prediction was incorrect.");
            }
        }
    }

    private void drawAnotherCard() {
        currentCard = nextCard;
        nextCard = deck.dealCard();
        currentCardImage.setText(currentCard.getAsset());
        comments.setText("");
    }
}
