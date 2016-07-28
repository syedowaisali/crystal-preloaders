package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.interpolators.EaseCubicInOutInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class Circular extends BasePreloader {

    private static final int MAX_ANGLE = 315;
    private static final int SPEED = 800;

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3;
    private int degree;
    private int minAngle, maxAngle, startAngle, sweepAngle;
    private RectF rectF;
    private float strokeWidth;
    private boolean firstTime = true;
    private int tempMax;

    public Circular(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        strokeWidth = getWidth() / 16;
        minAngle    = 0;
        maxAngle    = MAX_ANGLE;
        startAngle  = minAngle;
        sweepAngle  = minAngle;

        rectF       = new RectF(strokeWidth / 1.5f, strokeWidth / 1.5f, getWidth() - strokeWidth / 1.5f, getHeight() - strokeWidth / 1.5f);

        tempMax = maxAngle;
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator1 = ValueAnimator.ofInt(0, 360);
        valueAnimator1.setDuration(1500);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator1.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator1.setInterpolator(new LinearInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (int) animation.getAnimatedValue();
                //getTarget().invalidate();
            }
        });

        setAnimator2();

        // create animator list
        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator1);
        animators.add(valueAnimator2);
        animators.add(valueAnimator3);

        return animators;
    }

    private void setAnimator2(){

        if(valueAnimator2 != null){
            valueAnimator2.removeAllUpdateListeners();
            valueAnimator2.removeAllListeners();
            valueAnimator2 = null;
        }

        valueAnimator2 = ValueAnimator.ofInt(minAngle, maxAngle);
        valueAnimator2.setDuration(SPEED);
        valueAnimator2.setInterpolator(new EaseCubicInOutInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (int) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });
        valueAnimator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(! firstTime){
                    minAngle = tempMax - MAX_ANGLE;
                    maxAngle = minAngle + MAX_ANGLE;
                }
                maxAngle -= 10;

                firstTime = false;
                setAnimator3();
                valueAnimator3.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void setAnimator3(){

        if(valueAnimator3 != null){
            valueAnimator3.removeAllUpdateListeners();
            valueAnimator3.removeAllListeners();
        }

        valueAnimator3 = ValueAnimator.ofInt(minAngle, maxAngle);
        valueAnimator3.setDuration(SPEED);
        valueAnimator3.setInterpolator(new EaseCubicInOutInterpolator());
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (int) animation.getAnimatedValue();
                sweepAngle = (maxAngle + 10) - startAngle;

                getTarget().invalidate();
            }
        });
        valueAnimator3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                minAngle = 10;
                maxAngle = MAX_ANGLE;
                tempMax += (MAX_ANGLE - 10);
                setAnimator2();
                valueAnimator2.start();
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
        valueAnimator1.start();
        valueAnimator2.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(strokeWidth);
        fgPaint.setStrokeCap(Paint.Cap.ROUND);
        fgPaint.setStrokeJoin(Paint.Join.ROUND);

        canvas.save();
        canvas.rotate(degree, cx, cy);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, fgPaint);

        canvas.restore();

    }
}
