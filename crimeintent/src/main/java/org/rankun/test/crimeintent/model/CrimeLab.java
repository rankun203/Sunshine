package org.rankun.test.crimeintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rankun203 on 12/2/14.
 * Manage the Crime list.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private List<Crime> mCrimes;

    // Make CrimeLab be a singleton.
    private CrimeLab(Context appContext) {
        this.mCrimes = new ArrayList<Crime>();
        this.mAppContext = appContext;

        for (int i = 0; i < 100; i++) {
            addCrime(new Crime("Crime #" + i, (i % 2 == 1)));
        }
    }

    public static CrimeLab get(Context appContext) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(appContext.getApplicationContext());
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime) {
        mCrimes.add(crime);
    }

    public void removeCrime(int location) {
        this.mCrimes.remove(location);
    }

    public int size() {
        return this.mCrimes.size();
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
