package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class BallPulseSync extends BasePreloader {

    private static final int SPEED = 400;

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3;
    private float radius;
    private float fromY, toY;
    private float y1, y2, y3;

    public BallPulseSync(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        radius = Math.min(getWidth() / 2, getHeight() / 2f / 1.7f);
        fromY = getHeight() - radius;
        toY   = radius;
        y1 = getHeight() - radius;
        y2 = getHeight() - radius;
        y3 = getHeight() - radius;
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator1 = ValueAnimator.ofFloat(fromY, toY);
        valueAnimator1.setDuration(SPEED);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator1.setInterpolator(new AccelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y1 = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        valueAnimator2 = ValueAnimator.ofFloat(fromY, toY);
        valueAnimator2.setStartDelay(150);
        valueAnimator2.setDuration(SPEED);
        valueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator2.setInterpolator(new AccelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y2 = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        valueAnimator3 = ValueAnimator.ofFloat(fromY, toY);
        valueAnimator3.setStartDelay(300);
        valueAnimator3.setDuration(SPEED);
        valueAnimator3.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator3.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator3.setInterpolator(new AccelerateInterpolator());
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y3 = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        // create animator list
        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator1);
        animators.add(valueAnimator2);
        animators.add(valueAnimator3);

        return animators;
    }

    @Override
    protected void startAnimation() {
        valueAnimator1.start();
        valueAnimator2.start();
        valueAnimator3.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        canvas.drawCircle(radius, y1, radius, fgPaint);
        canvas.drawCircle(cx, y2, radius, fgPaint);
        canvas.drawCircle(width - radius, y3, radius, fgPaint);
    }

    @Override
    public int getHeight() {
        float height = 0;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_sync_vs); break;
            case CrystalPreloader.Size.SMALL: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_sync_s); break;
            case CrystalPreloader.Size.MEDIUM: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_sync_m); break;
            case CrystalPreloader.Size.LARGE: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_sync_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_sync_el); break;
        }

        return (int)height;
    }
}
