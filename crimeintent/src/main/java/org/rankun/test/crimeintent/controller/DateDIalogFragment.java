package org.rankun.test.crimeintent.controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import org.rankun.test.crimeintent.model.SimpleDate;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rankun203 on 12/10/14.
 */
public class DateDialogFragment extends DialogFragment {
    private static final String TAG = DateDialogFragment.class.getName() + ".tag";
    public static final String KEY_DATE = "key_date";
    public static final String EXTRA_DATE = DateDialogFragment.class.getName() + "extra_date";
    private SimpleDate mDate;

    public static DateDialogFragment newInstance(SimpleDate date) {
        DateDialogFragment fragment = new DateDialogFragment();
        Bundle frags = new Bundle();
        frags.putSerializable(KEY_DATE, date);
        fragment.setArguments(frags);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            Log.e(TAG, "Arguments is null");
        }
        mDate = (SimpleDate) getArguments().getSerializable(KEY_DATE);

        return new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mDate.setYear(year);
                        mDate.setMonthOfYear(monthOfYear);
                        mDate.setDayOfMonth(dayOfMonth);
                        setResult(Activity.RESULT_OK);
                    }
                },
                mDate.getYear(), mDate.getMonthOfYear(), mDate.getDayOfMonth()
        );
    }

    public void setResult(int resultCode) {
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
//        i.putExtra(EXTRA_DATE, mCalendar.getTime());
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
