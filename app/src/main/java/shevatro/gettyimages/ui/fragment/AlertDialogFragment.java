package shevatro.gettyimages.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import shevatro.gettyimages.R;

public class AlertDialogFragment extends DialogFragment {
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    public static AlertDialogFragment newInstance(String title, String msg) {
        AlertDialogFragment frag = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, msg);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = bundle.getString(TITLE);
        String msg = bundle.getString(MESSAGE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }
                )
                .create();
    }
}
