package org.rankun.test.crimeintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rankun203 on 11/27/14.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        this(null, false);
    }
    public Crime(String pTitle, boolean pSolved) {
        this.mId = UUID.randomUUID();
        this.mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public void setSolved(boolean isSolved) {
        this.mSolved = isSolved;
    }

    public boolean isSolved() {
        return mSolved;
    }
}
