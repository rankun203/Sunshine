package org.rankun.test.crimeintent.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.rankun.test.crimeintent.R;
import org.rankun.test.crimeintent.model.Crime;
import org.rankun.test.crimeintent.model.CrimeLab;
import org.rankun.test.crimeintent.model.SimpleDate;
import org.rankun.test.crimeintent.model.SimpleTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by rankun203 on 11/27/14.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mCrimeText;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private static final String TAG = "CrimeIntent_CrimeFragment";
    public static final String EXTRA_CRIME_ID = "org.rankun.test.crimeintent.controller.CrimeFragment.extra_crime_id";
    public static final String EXTRA_NEW_DATE = "org.rankun.test.crimeintent.controller.CrimeFragment.new_date";
    private static final String TAG_DIALOG_DATETIME_TYPE = "datetime_type_picker";
    private static final String TAG_DIALOG_TIME = "time_dialog";
    private static final String TAG_DIALOG_DATE = "date_dialog";
    private static final int REQUEST_CODE_DATE = 0;
    private static final int REQUEST_CODE_DATETIME_TYPE = 1;
    private static final int REQUEST_CODE_FOR_TIME = 2;
    private static final int REQUEST_CODE_FOR_DATE = 3;
    private static final SimpleDateFormat crimeDateFormat = new SimpleDateFormat("yyyy年 MM月 dd日, EEE HH:mm:ss");

    /**
     * Use to create a Crime Fragment rather than call the constructor directly.
     * @param pCrimeId the UUID of the Crime that the Fragment wants to show.
     * @return generated CrimeFragment
     */
    public static Fragment newInstance(UUID pCrimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, pCrimeId);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mCrimeText = (EditText) v.findViewById(R.id.crime_text);
        mCrimeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(mCrimeText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        mCrimeText.setText(mCrime.getTitle());

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeOrDateDialogFragment typePicker = TimeOrDateDialogFragment.newInstance(mCrime.getDate());
                typePicker.setTargetFragment(CrimeFragment.this, REQUEST_CODE_DATETIME_TYPE);
                typePicker.show(getActivity().getSupportFragmentManager(), TAG_DIALOG_DATETIME_TYPE);
            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    public void updateDate() {
        String crimeDate = crimeDateFormat.format(mCrime.getDate());
        mDateButton.setText(crimeDate);
    }

    /**
     * TODO: Test the time elapse using Calendar to convert a Time.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        } else if (requestCode == REQUEST_CODE_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        } else if (requestCode == REQUEST_CODE_FOR_TIME) {
            SimpleTime time = (SimpleTime) data.getSerializableExtra(TimeDialogFragment.EXTRA_TIME);
            Calendar c = Calendar.getInstance();
            c.setTime(mCrime.getDate());
            c.set(Calendar.HOUR_OF_DAY, time.getHour());
            c.set(Calendar.MINUTE, time.getMinute());
            c.set(Calendar.SECOND, time.getSecond());

            mCrime.setDate(c.getTime());
            updateDate();
        } else if (requestCode == REQUEST_CODE_FOR_DATE) {
            SimpleDate date = (SimpleDate) data.getSerializableExtra(DateDialogFragment.EXTRA_DATE);

            // Use a Calendar to update only the original Date's year, month, day.
            Calendar c = Calendar.getInstance();
            c.setTime(mCrime.getDate());
            c.set(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());

            mCrime.setDate(c.getTime());
            updateDate();
        } else if (requestCode == REQUEST_CODE_DATETIME_TYPE) {
            // This return is from a Date_or_Time choose

            int dialogSelected = data.getIntExtra(TimeOrDateDialogFragment.KEY_EXTRA_DATE, 0);
            startDateOrTimeSelectDialog(dialogSelected);
        }
    }

    /**
     * Start the dialog to choose a Time or a Date.
     *
     * @param dialogSelected Which Dialog should be shown, can be
     * {@link org.rankun.test.crimeintent.controller.TimeOrDateDialogFragment#CHOOSE_BUTTON_1} or
     * {@link org.rankun.test.crimeintent.controller.TimeOrDateDialogFragment#CHOOSE_BUTTON_2}
     */
    private void startDateOrTimeSelectDialog(int dialogSelected) {
        Calendar c = Calendar.getInstance();
        c.setTime(mCrime.getDate());

        if (dialogSelected == TimeOrDateDialogFragment.CHOOSE_BUTTON_1) {
            TimeDialogFragment timeDialogFragment = TimeDialogFragment.newInstance(
                    new SimpleTime(
                            c.get(Calendar.HOUR_OF_DAY),
                            c.get(Calendar.MINUTE),
                            c.get(Calendar.SECOND)
                    )
            );
            timeDialogFragment.setTargetFragment(CrimeFragment.this, REQUEST_CODE_FOR_TIME);
            timeDialogFragment.show(getActivity().getSupportFragmentManager(), TAG_DIALOG_TIME);
        } else if (dialogSelected == TimeOrDateDialogFragment.CHOOSE_BUTTON_2) {
            DateDialogFragment fragment = DateDialogFragment.newInstance(
                    new SimpleDate(
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    )
            );
            fragment.setTargetFragment(CrimeFragment.this, REQUEST_CODE_FOR_DATE);
            fragment.show(getActivity().getSupportFragmentManager(), TAG_DIALOG_DATE);
        } else if (dialogSelected == 0) {
            Log.d(TAG, "选择错误");
        }

    }

}

