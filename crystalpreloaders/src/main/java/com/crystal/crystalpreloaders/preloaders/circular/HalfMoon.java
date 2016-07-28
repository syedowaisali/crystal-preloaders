package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class HalfMoon extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;
    private Path path1;
    private Path path2;

    public HalfMoon(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

        float cx = getWidth() / 2;
        float cy = getHeight() / 2;

        path1 = new Path();
        path2 = new Path();

        path1.addCircle(cx, cy, Math.min(cx, cy), Path.Direction.CW);
        path2.addCircle(cx + (cx / 10), cy + (cy  / 10), Math.min(cx, cy) / 1.2f, Path.Direction.CW);
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

        canvas.save();
        canvas.rotate(degree, cx, cy);
        canvas.clipPath(path2, Region.Op.DIFFERENCE);
        canvas.drawPath(path1, fgPaint);
        canvas.restore();

    }

}
