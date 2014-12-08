package org.rankun.test.crimeintent.controller;

import android.support.v4.app.Fragment;

/**
 * Created by rankun203 on 12/3/14.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }


}
