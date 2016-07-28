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
public class BallRing extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;
    private float radius1, radius2;
    private float strokeWidth;

    public BallRing(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

        strokeWidth = Math.min(getWidth(), getHeight()) / 20;
        radius1 = Math.min(getWidth(), getHeight()) / 2 - strokeWidth / 2;
        radius2 = radius1 / 3.5f;
        radius1 = radius1 - radius2 / 2;
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(1500);
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

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(strokeWidth);

        canvas.drawCircle(cx, cy, radius1, fgPaint);

        fgPaint.setStyle(Paint.Style.FILL);

        canvas.save();
        canvas.rotate(degree, cx, cy);
        canvas.drawCircle(width - radius2, cy, radius2, fgPaint);
        canvas.restore();

    }
}
