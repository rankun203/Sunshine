package org.rankun.test.crimeintent.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import org.rankun.test.crimeintent.R;
import org.rankun.test.crimeintent.model.Crime;
import org.rankun.test.crimeintent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by rankun203 on 12/2/14.
 */
public class CrimeListFragment extends ListFragment{
    private static final String TAG = "CrimeListFragment";
    private List<Crime> mCrimes;
    private static final SimpleDateFormat crimeDateFormat1 = new SimpleDateFormat("EEE, MM月 dd日, yyyy");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.crimes_title);

        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        ArrayAdapter<Crime> adapter = new CrimeAdapter(mCrimes);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);

        Log.d(TAG, "onListItem(" + position + ")Click");

        // Start CrimePagerActivity from this fragment.
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }

    /**
     * Communicate between Crime List View and CrimeList object.
     */
    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(List<Crime> pCrimes) {
            super(getActivity(), 0, pCrimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            final Crime crime = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(crimeDateFormat1.format(crime.getDate()));

            CheckBox resolvedCheckbox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckbox);
            resolvedCheckbox.setChecked(crime.isSolved());
            resolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    crime.setSolved(isChecked);
                }
            });

            return convertView;
        }
    }
}
