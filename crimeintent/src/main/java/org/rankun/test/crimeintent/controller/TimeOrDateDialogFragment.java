package org.rankun.test.crimeintent.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import org.rankun.test.crimeintent.R;

import java.util.Date;

/**
 * Created by rankun203 on 12/10/14.
 */
public class TimeOrDateDialogFragment extends DialogFragment {
    private static final String EXTRA_DATE = TimeOrDateDialogFragment.class.getSimpleName() + ".date";

    public static TimeOrDateDialogFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        TimeOrDateDialogFragment fragment = new TimeOrDateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_date_picker_title)
                .setNegativeButton(R.string.time_date_picker_time, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "onClick-" + getString(R.string.time_date_picker_time), Toast.LENGTH_SHORT)
                            .show();
                    }
                })
                .setPositiveButton(R.string.time_date_picker_date, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "onClick-" + getString(R.string.time_date_picker_date), Toast.LENGTH_SHORT)
                            .show();
                    }
                })
                .create();
    }
}
