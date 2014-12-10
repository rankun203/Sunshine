package org.rankun.test.crimeintent.controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import org.rankun.test.crimeintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by rankun203 on 12/8/14.
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private static String TAG = DatePickerDialogFragment.class.getName();
    public static String EXTRA_DATE = DatePickerDialogFragment.class.getName() +  ".date";
    private Date mDate;

    /**
     * Use new Instance to receive a Date object instead of create this Fragment in calling side,
     * Control the create process itself is More Flexibility.
     * @param date the Date to be shown.
     * @return {@link DatePickerDialogFragment}
     */
    public static DatePickerDialogFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(mDate);
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
        pickerDialog.setTitle(R.string.date_picker_title);

        return pickerDialog;
    }



    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            Log.w(TAG, "getTargetFragment returned nothing.");
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();

        // Update argument to preserve selected value on rotation
        getArguments().putSerializable(EXTRA_DATE, mDate);

        sendResult(Activity.RESULT_OK);
    }
}
