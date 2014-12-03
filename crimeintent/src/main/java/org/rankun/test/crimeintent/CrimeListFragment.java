package org.rankun.test.crimeintent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rankun203 on 12/2/14.
 */
public class CrimeListFragment extends ListFragment{
    private List<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.crimes_title);

        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(
                getActivity(),
                R.layout.simple_list_item_1,
                mCrimes
        );

        setListAdapter(adapter);
    }
}
