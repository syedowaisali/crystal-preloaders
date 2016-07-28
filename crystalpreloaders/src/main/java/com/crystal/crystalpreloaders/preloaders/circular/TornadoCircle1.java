package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class TornadoCircle1 extends BasePreloader {

    private static final int TOTAL_CIRCLE = 5;
    private static final int SPEED = 2000;
    private static final int DELAY = 60;

    private ValueAnimator[] valueAnimators;
    private int[] degrees;
    private RectF[] rectFs;
    private float strokeWidth;
    private Handler handler;

    public TornadoCircle1(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        float width = Math.min(getWidth(), getHeight());
        float cx = width / 2;

        strokeWidth = width / 25;

        rectFs  = new RectF[TOTAL_CIRCLE];
        degrees = new int[TOTAL_CIRCLE];

        for(int i = 0; i < TOTAL_CIRCLE; i++){
            float gap = cx - (i * cx / 6);
            gap -= strokeWidth / 2;
            rectFs[Math.abs(i - (TOTAL_CIRCLE - 1))] = new RectF(cx - gap, cx - gap, cx + gap, cx + gap);
        }

        valueAnimators = new ValueAnimator[TOTAL_CIRCLE];
        handler = new Handler();
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        for(int i = 0; i < TOTAL_CIRCLE; i++){
            setAnimator(i);
        }

        return Arrays.asList(valueAnimators);
    }

    private void setAnimator(final int index){

        valueAnimators[index] = ValueAnimator.ofInt(0, 360);
        valueAnimators[index].setStartDelay(index * DELAY);
        valueAnimators[index].setDuration(SPEED);
        valueAnimators[index].setInterpolator(new OvershootInterpolator());
        valueAnimators[index].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees[index] = (int) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        if(index == TOTAL_CIRCLE - 1){
            valueAnimators[index].addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startAnimation();
                        }
                    }, 300);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    @Override
    protected void startAnimation() {

        for (int i = 0; i < TOTAL_CIRCLE; i++){
            valueAnimators[i].start();
        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(strokeWidth);

        for(int i = 0; i < TOTAL_CIRCLE; i++){
            canvas.save();
            canvas.rotate(degrees[i], cx, cy);
            canvas.drawArc(rectFs[i], 180, 180, false, fgPaint);
            canvas.restore();
        }

    }
}
