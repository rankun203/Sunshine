package org.rankun.test.crimeintent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rankun203 on 12/2/14.
 * Manage the Crime list.
 */
public class CrimeLab {
    private List<Crime> crimeList;

    public CrimeLab() {
        this.crimeList = new LinkedList<Crime>();
    }

    public void addCrime(Crime crime) {
        this.crimeList.add(crime);
    }

    public void removeCrime(int location) {
        this.crimeList.remove(location);
    }

    public int size() {
        return this.crimeList.size();
    }

    public List get() {
        return crimeList;
    }

    public Crime get(int location) {
        return this.crimeList.get(location);
    }
}
