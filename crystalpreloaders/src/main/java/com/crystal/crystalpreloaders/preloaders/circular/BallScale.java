package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class BallScale extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int radius;
    private int opacity;

    public BallScale(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        PropertyValuesHolder radiusValueHolder = PropertyValuesHolder.ofInt("radius", 0, Math.min(getWidth() / 2, getHeight() / 2));
        PropertyValuesHolder opacityValueHolder = PropertyValuesHolder.ofInt("opacity", 200, 0);

        valueAnimator = ValueAnimator.ofPropertyValuesHolder(radiusValueHolder, opacityValueHolder);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius  = (int) animation.getAnimatedValue("radius");
                opacity = (int) animation.getAnimatedValue("opacity");
                getTarget().invalidate();
            }
        });

        // create animator list
        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator);

        return animators;
    }

    @Override
    protected void startAnimation() {
        valueAnimator.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        fgPaint.setAlpha(opacity);
        canvas.drawCircle(cx, cy, radius, fgPaint);

    }
}
