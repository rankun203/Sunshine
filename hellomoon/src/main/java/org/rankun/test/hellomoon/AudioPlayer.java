package org.rankun.test.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by rankun203 on 12/11/14.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;
    private boolean isPaused = false;

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context context) {
        stop();

        mPlayer = MediaPlayer.create(context, R.raw.one_small_step);
        mPlayer.start();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
    }

    public void pause() {
        Log.d("tag", "mPlayer.isPlaying(): " + isPlaying());
        if (mPlayer == null || isPaused) {
            return;
        }
        mPlayer.pause();
        isPaused = true;
    }

    public void resume() {
        if (mPlayer == null || !isPaused) {
            return;
        }
        mPlayer.start();
        isPaused = false;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isPlaying() {
        if (mPlayer == null) {
            return false;
        }
        return mPlayer.isPlaying();
    }
}
