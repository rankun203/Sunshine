package org.rankun.test.crimeintent.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import org.rankun.test.crimeintent.R;


public abstract class SingleFragmentActivity extends FragmentActivity {
    private android.support.v4.app.FragmentManager fm;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fm = getSupportFragmentManager();

        // Finds a fragment that was identified by the container ID when added in a transaction.
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        // If fragmentContainer doesn't presented in current Activity's FragmentManager.
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

}
