package cc.locati.cards.app.highlow;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;

import cc.locati.cards.app.MainActivity;
import cc.locati.cards.app.R;
import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Deck;

public class HighlowMainActivity extends Activity {

    int correctGuesses;
    int recordScore;

    SharedPreferences sharedPref;

    Deck deck = new Deck();

    Card currentCard;
    Card nextCard;

    TextView comments;
    TextView points;
    TextView record;
    TextView currentCardImage;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highlow_main);

        comments = (TextView)findViewById(R.id.textComments);
        points = (TextView)findViewById(R.id.points);
        currentCardImage = (TextView)findViewById(R.id.card);
        record = (TextView)findViewById(R.id.record);

/*        SVGImageView currentCardImage = (SVGImageView)findViewById(R.id.imageView);
        currentCardImage.setImageAsset(currentCard.getAsset());*/

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        recordScore = sharedPref.getInt("recordScore", 0);

        final Button buttonHigher = (Button) findViewById(R.id.buttonHigher);
        buttonHigher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('H');
            }
        });

        final Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('E');
            }
        });

        final Button buttonLower = (Button) findViewById(R.id.buttonLower);
        buttonLower.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('L');
            }
        });

        drawFirstCard();
    }

    private void makeChoice(char choice) {
        char rightChoice = 'N';
        if (nextCard.getValue() > currentCard.getValue())
            rightChoice = 'H';
        if (nextCard.getValue() == currentCard.getValue())
            rightChoice = 'E';
        if (nextCard.getValue() < currentCard.getValue())
            rightChoice = 'L';
        if (choice == rightChoice)
            rightChoice();
        else
            wrongChoice();
    }

    private void rightChoice() {
        comments.setText("Your prediction was correct.");
        correctGuesses++;
        points.setText(Integer.toString(correctGuesses));
        drawAnotherCard();
    }

    private void wrongChoice() {
        comments.setText("Sorry, next card was " + nextCard.toString());

        if (correctGuesses > recordScore) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("recordScore", correctGuesses);
            editor.commit();
            recordScore = correctGuesses;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.lost)
            .setPositiveButton(R.string.tryagain, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    drawFirstCard();
                }
            })
            .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });

        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.show();
    }

    private void drawFirstCard() {
        record.setText(Integer.toString(recordScore));
        deck.shuffle();
        currentCard = deck.dealCard();
        nextCard = deck.dealCard();
        currentCardImage.setText(currentCard.getAsset());
        correctGuesses = 0;
        points.setText("");
        comments.setText("");
    }

    private void drawAnotherCard() {
        if (deck.cardsLeft() >= 1) {
            currentCard = nextCard;
            nextCard = deck.dealCard();
            currentCardImage.setText(currentCard.getAsset());
            comments.setText("");
        } else
            comments.setText("You won");
    }
}
