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
public class BallPulse extends BasePreloader {

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3;
    private int radius1, radius2, radius3;
    private int radius;

    public BallPulse(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        radius = Math.min(getWidth() / 2, getHeight() / 2);
        radius1 = radius;
        radius2 = radius;
        radius3 = radius;
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator1 = ValueAnimator.ofInt(radius, 0);
        valueAnimator1.setDuration(500);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator1.setInterpolator(new AccelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius1 = (int) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        valueAnimator2 = ValueAnimator.ofInt(radius, 0);
        valueAnimator2.setStartDelay(250);
        valueAnimator2.setDuration(500);
        valueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator2.setInterpolator(new AccelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius2 = (int) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        valueAnimator3 = ValueAnimator.ofInt(radius, 0);
        valueAnimator3.setStartDelay(500);
        valueAnimator3.setDuration(500);
        valueAnimator3.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator3.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator3.setInterpolator(new AccelerateInterpolator());
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius3 = (int) animation.getAnimatedValue();
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
        //canvas.drawColor(Color.RED);

        canvas.drawCircle(Math.min(cx, cy), cy, radius1, fgPaint);
        canvas.drawCircle(cx, cy, radius2, fgPaint);
        canvas.drawCircle(width - Math.min(cx, cy), cy, radius3, fgPaint);
    }

    @Override
    public int getHeight() {
        float height = 0;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_vs); break;
            case CrystalPreloader.Size.SMALL: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_s); break;
            case CrystalPreloader.Size.MEDIUM: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_m); break;
            case CrystalPreloader.Size.LARGE: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: height = getTarget().getResources().getDimension(R.dimen.height_ball_pulse_el); break;
        }

        return (int)height;
    }
}
