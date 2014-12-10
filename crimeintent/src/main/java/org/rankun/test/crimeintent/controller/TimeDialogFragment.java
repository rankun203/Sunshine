package org.rankun.test.crimeintent.controller;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import org.rankun.test.crimeintent.model.SimpleTime;

import java.util.Date;

/**
 * Created by rankun203 on 12/10/14.
 */
public class TimeDialogFragment extends DialogFragment {

    public static final String EXTRA_TIME = TimeDialogFragment.class.getName() + "extra_time";
    private static final String KEY_TIME = "key_time";
    private SimpleTime mTime;

    public static TimeDialogFragment newInstance(SimpleTime initTime) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_TIME, initTime);
        args.putSerializable("testSer", new Date());

        TimeDialogFragment fragment = new TimeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mTime = (SimpleTime) getArguments().getSerializable(KEY_TIME);

        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toaster.toast(getActivity(), "Time was set to " + hourOfDay + ":" + minute);
                mTime.setHour(hourOfDay);
                mTime.setMinute(minute);
                setResult(Activity.RESULT_OK);
            }
        }, mTime.getHour(), mTime.getMinute(), false);
    }

    public void setResult(int resultCode) {
        Intent i = new Intent();
        i.putExtra(EXTRA_TIME, mTime);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
