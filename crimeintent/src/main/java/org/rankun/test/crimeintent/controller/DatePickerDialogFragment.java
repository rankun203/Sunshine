package org.rankun.test.crimeintent.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import org.rankun.test.crimeintent.R;

/**
 * Created by rankun203 on 12/8/14.
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static String TAG = DatePickerDialogFragment.class.getName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_datepicker, null);

        return new DatePickerDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(R.string.date_picker_choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, getString(R.string.date_picker_choose));
                    }
                })
                .setNegativeButton(R.string.date_picker_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, getString(R.string.date_picker_cancel));
                    }
                })
                .setView(v)
                .create();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Log.d(TAG, "Date changed to: " + monthOfYear + ":" + dayOfMonth);
    }
}
