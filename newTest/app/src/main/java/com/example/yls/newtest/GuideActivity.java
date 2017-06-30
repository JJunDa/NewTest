package com.example.yls.newtest;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by yls on 2017/6/27.
 */

public class GuideActivity extends BaseActivity {
    private ImageView mImageView;
    private Button mButton;

    private int index = 0;
    private int[] imgArrary = new int[]{
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onStart() {
        super.onStart();

        playBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }

    private MediaPlayer mMediaPlayer;
    private void playBackgroundMusic() {
        mMediaPlayer = new MediaPlayer().create(this,R.raw.new_version);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setVolume(1f,1f);
        mMediaPlayer.start();
    }

    @Override
    protected void initView() {
        mImageView = (ImageView) findViewById(R.id.iv_guide);
        mButton = (Button) findViewById(R.id.btn_start);
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity();
            }
        });
    }

    @Override
    protected void initData() {
        startAnimation();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1001:
                    startAnimation();
                    break;
            }
        }
    };

    private void startAnimation() {
        index++;
        index = index % imgArrary.length;
        mImageView.setBackgroundResource(imgArrary[index]);

        //图片还原比例
        mImageView.setScaleX(1f);
        mImageView.setScaleY(1f);

        mImageView.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(2500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mHandler.sendEmptyMessageDelayed(1001,1000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();

    }

    public void gotoMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
