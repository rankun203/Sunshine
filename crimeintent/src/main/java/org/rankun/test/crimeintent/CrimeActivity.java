package org.rankun.test.crimeintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by rankun203 on 12/3/14.
 */
public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
