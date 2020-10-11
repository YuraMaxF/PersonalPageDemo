package com.yuramax.personalpagedemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * author : weijun
 * e-mail : 1301892339@qq.com
 * time   : 2020/10/11
 * desc   : 模仿下雨view
 * version: 1.0
 */
public class RainImageView extends RelativeLayout {

    private static final int MSG_START = 1;
    private static final int MSG_STOP = 2;

    private MyHandler myHandler;

    public RainImageView(Context context) {
        super(context);
        init();
    }

    public RainImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RainImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        myHandler = new MyHandler();
    }

    //开始下雨
    public void startRain(){
        myHandler.sendEmptyMessage(MSG_START);
    }

    public void stopRain(){
        myHandler.sendEmptyMessage(MSG_STOP);
    }

    private void addView(){
        final ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.ic_launcher);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50,50);
        layoutParams.leftMargin = randomInt(getWidth(getContext()));
        layoutParams.topMargin = 0;
        layoutParams.rightMargin = 0;
        layoutParams.bottomMargin = 0;
        imageView.setLayoutParams(layoutParams);
        imageView.setVisibility(View.INVISIBLE);
        startAnim(imageView);
    }

    private void startAnim(final View view){
        addView(view);
        AnimationUtils.showAnimation(view,1000, new AnimationUtils.OnAnimationListener() {
            @Override
            public void onAnimationEnd() {
                //开始下一个动画
                AnimationUtils.translateAnimation(view, RainImageView.this, 5000, new AnimationUtils.OnAnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        AnimationUtils.hideAnimation(view,1000,null);
                    }
                });
            }
        });
    }

    public void addRainView(View view){
        startAnim(view);
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START:
                    addView();
                    myHandler.sendEmptyMessageDelayed(MSG_START, 1000);
                    break;
                case MSG_STOP:
                    myHandler.removeMessages(MSG_START);//移除循环信息
                    break;
                default:
                    break;
            }
        }
    }

    private int getWidth(Context context) {
        int width = 0;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        width = dm.widthPixels;
        return width;
    }

    private int randomInt(int max) {
        return (int) (Math.random() * max);
    }
}
