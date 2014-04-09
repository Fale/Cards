package cc.locati.cards.app.highlow;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import cc.locati.cards.app.R;

public class HighlowMainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.highlow_single_fragment_layout);
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            HighLowFragment fragment = new HighLowFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, "your_fragment_tag").commit();
        } else {
            HighLowFragment fragment = (HighLowFragment) getSupportFragmentManager().findFragmentByTag("your_fragment_tag");
        }
    }
*/
}
