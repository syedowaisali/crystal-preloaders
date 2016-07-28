package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class TimeMachine extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;
    private RectF outerRectF, centerRectF, innerRectF;

    public TimeMachine(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        float width = getWidth();
        float height = getHeight();
        float cx = width / 2;
        float cy = height / 2;

        outerRectF  = new RectF(0, 0, width, height);
        centerRectF = new RectF(cx - cx / 1.3f, cx - cx / 1.3f, cy + cy / 1.3f, cy + cy / 1.3f);
        innerRectF  = new RectF(cx - cx / 1.7f, cx - cx / 1.7f, cy + cy / 1.7f, cy + cy / 1.7f);
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(1000);
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


        canvas.drawArc(outerRectF, 0, 360, true, fgPaint);
        canvas.drawArc(centerRectF, 0, 360, true, bgPaint);
        canvas.drawArc(innerRectF, 0, 360, true, fgPaint);

        canvas.save();
        canvas.rotate(degree, cx, cy);

        canvas.drawArc(outerRectF, 180, 45, true, bgPaint);
        canvas.drawArc(centerRectF, 180, 45, true, fgPaint);
        canvas.drawArc(innerRectF, 180, 45, true, bgPaint);

        canvas.restore();
    }
}
