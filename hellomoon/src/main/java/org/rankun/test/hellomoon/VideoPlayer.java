package org.rankun.test.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by rankun203 on 12/11/14.
 */
public class VideoPlayer {

    private MediaPlayer mPlayer;
    private static VideoPlayer mThis;


    public void play(final Context context, SurfaceHolder surfaceHolder) {
        mPlayer = MediaPlayer.create(context, R.raw.apollo_stroll);
        mPlayer.setDisplay(surfaceHolder);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        mPlayer.start();
    }
    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

}
