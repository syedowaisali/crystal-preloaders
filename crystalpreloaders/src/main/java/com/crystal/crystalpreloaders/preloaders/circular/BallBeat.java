package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.PropertyValuesHolder;
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
public class BallBeat extends BasePreloader {

    private static final int SPEED = 300;

    private ValueAnimator valueAnimator1, valueAnimator2;
    private int radius1, radius2;
    private int radius;
    private int opacity1, opacity2;
    private int opacity;

    public BallBeat(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        radius = Math.min(getWidth() / 2, getHeight() / 2);
        radius1 = radius;
        radius2 = radius / 2;
        opacity = 250;
        opacity1 = opacity;
        opacity2 = 50;
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        PropertyValuesHolder radiusHolder1  = PropertyValuesHolder.ofInt("radius", radius, radius / 2);
        PropertyValuesHolder opacityHolder1 = PropertyValuesHolder.ofInt("opacity", opacity, 50);

        valueAnimator1 = ValueAnimator.ofPropertyValuesHolder(radiusHolder1, opacityHolder1);
        valueAnimator1.setDuration(SPEED);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator1.setInterpolator(new AccelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius1  = (int) animation.getAnimatedValue("radius");
                opacity1 = (int) animation.getAnimatedValue("opacity");
                getTarget().invalidate();
            }
        });

        PropertyValuesHolder radiusHolder2  = PropertyValuesHolder.ofInt("radius", radius, radius / 2);
        PropertyValuesHolder opacityHolder2 = PropertyValuesHolder.ofInt("opacity", opacity, 50);

        valueAnimator2 = ValueAnimator.ofPropertyValuesHolder(radiusHolder2, opacityHolder2);
        valueAnimator2.setStartDelay(200);
        valueAnimator2.setDuration(SPEED);
        valueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator2.setInterpolator(new AccelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius2  = (int) animation.getAnimatedValue("radius");
                opacity2 = (int) animation.getAnimatedValue("opacity");
                getTarget().invalidate();
            }
        });


        // create animator list
        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator1);
        animators.add(valueAnimator2);

        return animators;
    }

    @Override
    protected void startAnimation() {
        valueAnimator1.start();
        valueAnimator2.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {
        //canvas.drawColor(Color.RED);

        fgPaint.setAlpha(opacity1);
        canvas.drawCircle(Math.min(cx, cy), cy, radius1, fgPaint);
        canvas.drawCircle(width - Math.min(cx, cy), cy, radius1, fgPaint);

        fgPaint.setAlpha(opacity2);
        canvas.drawCircle(cx, cy, radius2, fgPaint);
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
