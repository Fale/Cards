package cc.locati.cards.app.highlow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;

import cc.locati.cards.app.R;
import cc.locati.cards.libs.Card;
import cc.locati.cards.libs.Deck;

public class HighLowFragment extends Fragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        comments = (TextView) getView().findViewById(R.id.textComments);
        points = (TextView) getView().findViewById(R.id.points);
        currentCardImage = (TextView) getView().findViewById(R.id.card);
        record = (TextView) getView().findViewById(R.id.record);

/*        SVGImageView currentCardImage = (SVGImageView)findViewById(R.id.imageView);
        currentCardImage.setImageAsset(currentCard.getAsset());*/

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        recordScore = sharedPref.getInt("recordScore", 0);

        final Button buttonHigher = (Button) getView().findViewById(R.id.buttonHigher);
        buttonHigher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('H');
            }
        });

        final Button buttonEqual = (Button) getView().findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeChoice('E');
            }
        });

        final Button buttonLower = (Button) getView().findViewById(R.id.buttonLower);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.lost)
                .setPositiveButton(R.string.tryagain, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        drawFirstCard();
                    }
                })
                .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
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
