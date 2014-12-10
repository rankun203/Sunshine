package org.rankun.test.crimeintent.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import org.rankun.test.crimeintent.R;

import java.util.Date;

/**
 * Created by rankun203 on 12/10/14.
 */
public class TimeOrDateDialogFragment extends DialogFragment {
    public static final String KEY_EXTRA_DATE = TimeOrDateDialogFragment.class.getSimpleName() + ".date";
    private static String TAG = DatePickerDialogFragment.class.getName();
    public static int CHOOSE_BUTTON_1 = 1;
    public static int CHOOSE_BUTTON_2 = 2;

    public static TimeOrDateDialogFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_EXTRA_DATE, date);

        TimeOrDateDialogFragment fragment = new TimeOrDateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.time_date_picker_title)
                .setNegativeButton(R.string.time_date_picker_time, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toaster.toast(getActivity(), "onClick-" + getString(R.string.time_date_picker_time));
                        sendResult(Activity.RESULT_OK, CHOOSE_BUTTON_1);

                    }
                })
                .setPositiveButton(R.string.time_date_picker_date, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toaster.toast(getActivity(), "onClick-" + getString(R.string.time_date_picker_date));
                        sendResult(Activity.RESULT_OK, CHOOSE_BUTTON_2);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, int chooseResult) {
        if (getTargetFragment() == null) {
            Log.w(TAG, "getTargetFragment returned nothing.");
            return;
        }
        Intent i = new Intent();
        i.putExtra(KEY_EXTRA_DATE, chooseResult);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
