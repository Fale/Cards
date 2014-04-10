package cc.locati.cards.app.highlow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.appcompat.R;
import android.content.Context;

/**
 * Created by fale on 09/04/14.
 */
public class LostDialogFragment extends DialogFragment {

    public interface LostListener {
        void onTryAgain();
        void onQuit();
    }

    Context mContext;

    public LostDialogFragment() {
        mContext = getActivity();
    }

    public static LostDialogFragment newInstance() {
        LostDialogFragment f = new LostDialogFragment();
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(cc.locati.cards.app.R.string.lost)
                .setPositiveButton(cc.locati.cards.app.R.string.tryagain,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((LostListener) getActivity()).onTryAgain();
                            }
                        }
                )
                .setNegativeButton(cc.locati.cards.app.R.string.quit,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((LostListener) getActivity()).onQuit();
                            }
                        }
                )
                .setCancelable(false)
                .create();
    }
}