package com.yuramax.personalpagedemo;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * author : weijun
 * e-mail : 1301892339@qq.com
 * time   : 2020/10/11
 * desc   :
 * version: 1.0
 */
public class AnimationUtils {

    public static void showAnimation(final View view, long duration, final OnAnimationListener onAnimationListener){
        view.setVisibility(View.VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);
        float fromX = view.getWidth()/2 + view.getLeft();
        Log.e("","getLeft：" + view.getLeft());
        Log.e("","getWidth：" + view.getWidth());
        Log.e("","-------------------------------");
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, 1.0f, view.getHeight()/2, 1.0f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                if (onAnimationListener != null){
                    onAnimationListener.onAnimationEnd();
                }
            }
        });

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        view.setAnimation(animationSet);
        animationSet.start();

//        view.setAnimation(alphaAnimation);
//        alphaAnimation.start();
    }

    public static void hideAnimation(final View view, long duration, final OnAnimationListener onAnimationListener){
        view.setVisibility(View.INVISIBLE);
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                if (onAnimationListener != null){
                    onAnimationListener.onAnimationEnd();
                }
            }
        });
        view.setAnimation(animation);
        animation.start();
    }

    public static void translateAnimation(final View view, ViewGroup parent, long duration, final OnAnimationListener onAnimationListener){
        int parentHeight = parent.getHeight();
        int height = view.getHeight();
        Animation translateAnimation = new TranslateAnimation(0, 0, 0, parentHeight - height - 10);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                if (onAnimationListener != null){
                    onAnimationListener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(translateAnimation);
        translateAnimation.startNow();
    }

    interface OnAnimationListener{
        void onAnimationEnd();
    }
}
