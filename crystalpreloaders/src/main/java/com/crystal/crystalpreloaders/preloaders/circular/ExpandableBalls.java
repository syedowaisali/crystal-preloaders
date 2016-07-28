package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class ExpandableBalls extends BasePreloader {

    private static final int DELAY = 80;
    private static final int SPEED = 600;
    private static final int TOTAL_BALLS = 8;

    private ValueAnimator valueAnimatorMCStart, valueAnimatorMCReverse;
    private ValueAnimator[] valueAnimatorsStart;
    private ValueAnimator[] valueAnimatorsReverse;
    private float startY, endY;
    private float[] yt;
    private float mcRadius;
    private float ccMinRadius, ccMaxRadius, ccRadius;

    public ExpandableBalls(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

        float width = getWidth();
        float height = getHeight();
        float cx = width / 2;
        float cy = height / 2;

        yt = new float[TOTAL_BALLS];
        for(int i = 0; i < TOTAL_BALLS; i++){
            yt[i] = cy;
        }

        mcRadius = cx / 6;

        startY = cy;
        endY = mcRadius;

        ccMaxRadius = cx / 3;
        ccMinRadius = mcRadius;
        ccRadius = ccMaxRadius;

        valueAnimatorsStart = new ValueAnimator[TOTAL_BALLS];
        valueAnimatorsReverse = new ValueAnimator[TOTAL_BALLS];
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        for(int i = 0; i < TOTAL_BALLS; i++){
            setAnimator(i);
        }

        return Arrays.asList(valueAnimatorsStart);
    }

    private void setAnimator(final int index){

        valueAnimatorMCStart = ValueAnimator.ofFloat(ccMaxRadius, ccMinRadius);
        valueAnimatorMCStart.setDuration(700);
        valueAnimatorMCStart.setInterpolator(new AccelerateInterpolator());
        valueAnimatorMCStart.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ccRadius = (float) animation.getAnimatedValue();
                //getTarget().invalidate();

            }
        });

        valueAnimatorMCReverse = ValueAnimator.ofFloat(ccMinRadius, ccMaxRadius);
        valueAnimatorMCReverse.setDuration(700);
        valueAnimatorMCReverse.setInterpolator(new AccelerateInterpolator());
        valueAnimatorMCReverse.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ccRadius = (float) animation.getAnimatedValue();
                //getTarget().invalidate();

            }
        });

        valueAnimatorsStart[index] = ValueAnimator.ofFloat(startY, endY);
        valueAnimatorsStart[index].setStartDelay(index * DELAY);
        valueAnimatorsStart[index].setDuration(SPEED);
        valueAnimatorsStart[index].setInterpolator(new AccelerateInterpolator());
        valueAnimatorsStart[index].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yt[index] = (float) animation.getAnimatedValue();
                getTarget().invalidate();

            }
        });
        valueAnimatorsStart[index].addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(index == TOTAL_BALLS - 1){
                    for(int i = 0; i < TOTAL_BALLS; i++){
                        valueAnimatorsReverse[i].start();
                        valueAnimatorMCReverse.start();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimatorsReverse[index] = ValueAnimator.ofFloat(endY, startY);
        valueAnimatorsReverse[index].setStartDelay(index * DELAY);
        valueAnimatorsReverse[index].setDuration(SPEED);
        valueAnimatorsReverse[index].setInterpolator(new AccelerateInterpolator());
        valueAnimatorsReverse[index].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yt[index] = (float) animation.getAnimatedValue();
                getTarget().invalidate();

            }
        });
        valueAnimatorsReverse[index].addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(index == TOTAL_BALLS - 1){
                    for(int i = 0; i < TOTAL_BALLS; i++){
                        valueAnimatorsStart[i].start();
                        valueAnimatorMCStart.start();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void startAnimation() {

        for (int i = 0; i < TOTAL_BALLS; i++){
            valueAnimatorsStart[i].start();
        }

        valueAnimatorMCStart.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        canvas.drawCircle(cx, cy, ccRadius, fgPaint);

        for (int i = 0; i < TOTAL_BALLS; i++){
            canvas.save();
            canvas.rotate(i * 45, cx, cy);
            if(i % 2 == 1){
                yt[i] = Math.max(yt[i], cx / 2.5f);
            }
            canvas.drawCircle(cx, yt[i], mcRadius, fgPaint);
            canvas.restore();
        }


    }
}
