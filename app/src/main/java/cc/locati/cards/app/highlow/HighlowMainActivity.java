package cc.locati.cards.app.highlow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cc.locati.cards.app.R;

public class HighlowMainActivity extends FragmentActivity  implements LostDialogFragment.LostListener {

    HighLowFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.highlow_single_fragment_layout);

        fragment = (HighLowFragment) getSupportFragmentManager().findFragmentByTag("highlow");
    }

    public void onTryAgain() {
        fragment.drawFirstCard();
    }

    public void onQuit() {
        finish();
    }

}
