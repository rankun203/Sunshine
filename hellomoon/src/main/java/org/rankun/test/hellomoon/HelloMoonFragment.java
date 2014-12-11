package org.rankun.test.hellomoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by rankun203 on 12/11/14.
 */
public class HelloMoonFragment extends Fragment {

    private Button mPlayButton;
    private Button mStopButton;
    private Button mPauseButton;
    private Button mVideoButton;
    private AudioPlayer mPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_hello_moon, container, false);
        mPlayButton = (Button) v.findViewById(R.id.hellomoon_playButton);
        mStopButton = (Button) v.findViewById(R.id.hellomoon_stopButton);
        mPauseButton = (Button) v.findViewById(R.id.hellomoon_pauseButton);
        mVideoButton = (Button) v.findViewById(R.id.hellomoon_videoButton);

        mPlayer = new AudioPlayer();

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.play(getActivity());
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPaused()) {
                    mPlayer.resume();
                    mPauseButton.setText(R.string.media_pause);
                } else {
                    mPlayer.pause();
                    mPauseButton.setText(R.string.media_resume);
                }
            }
        });
        mVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), HelloMoonVideoActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}
