package com.crystal.crystalpreloaders.preloaders.circular;

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
public class InCircle extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;

    public InCircle(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(800);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (int) animation.getAnimatedValue();
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

        float radius1 = width / 2;
        float radius2 = width / 5f;

        canvas.drawCircle(cx, cy, radius1, fgPaint);

        canvas.save();
        canvas.rotate(degree, cx, cy);
        canvas.drawCircle(radius2, cy, radius2, bgPaint);
        canvas.restore();

    }
}
