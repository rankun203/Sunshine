package org.rankun.test.hellomoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rankun203 on 12/11/14.
 */
public class HelloMoonVideoFragment extends Fragment implements SurfaceHolder.Callback {
    private SurfaceView mSurfaceView;
    private VideoPlayer mVideoPlayer;
    private SurfaceHolder mSurfaceHolder;
    private View mVideoFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVideoFragment = inflater.inflate(R.layout.fragment_hello_moon_video, container);
        mSurfaceView = (SurfaceView) mVideoFragment.findViewById(R.id.video_surface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        return mVideoFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVideoPlayer.stop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mVideoPlayer = new VideoPlayer();
        mVideoPlayer.play(getActivity(), mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }
}
