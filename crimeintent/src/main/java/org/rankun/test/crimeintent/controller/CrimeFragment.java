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

import java.text.SimpleDateFormat;
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
    private static final String TAG_DIALOG_DATE = "datePicker";
    private static final String TAG_DIALOG_DATETIME_TYPE = "datetime_type_picker";
    private static final int REQUEST_CODE_DATE = 0;
    private static final int REQUEST_CODE_DATETIME_TYPE = 1;
    private static final SimpleDateFormat crimeDateFormat = new SimpleDateFormat("yyyy年 MM月 dd日, EEE");

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());

        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onStop();
    }

    @Override
    public void onStart() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onResume();
    }
}
