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
 * Created by owais.ali on 7/20/2016.
 */
public class Alternative extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;
    private RectF outerRectF, innerRectF;

    public Alternative(View target, int size) {
        super(target, size);
        init();
    }

    @Override
    protected void init(){
        float innerRadius = getWidth() / 1.45f;
        outerRectF = new RectF(0, 0, getWidth(), getHeight());
        innerRectF = new RectF(getWidth() / 2 - innerRadius / 2, getWidth() / 2 - innerRadius / 2, getWidth() / 2 + innerRadius / 2, getWidth() / 2 + innerRadius / 2);
    }

    @Override
    protected List<ValueAnimator> setupAnimation(){

        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (int) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator);

        return animators;
    }

    @Override
    protected void startAnimation(){
        valueAnimator.start();
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        canvas.save();
        canvas.rotate(degree, cx, cy);

        canvas.drawArc(outerRectF, 0, 45, true, fgPaint);
        canvas.drawArc(outerRectF, 45, 45, true, bgPaint);
        canvas.drawArc(outerRectF, 90, 45, true, fgPaint);
        canvas.drawArc(outerRectF, 135, 45, true, bgPaint);
        canvas.drawArc(outerRectF, 180, 45, true, fgPaint);
        canvas.drawArc(outerRectF, 225, 45, true, bgPaint);
        canvas.drawArc(outerRectF, 270, 45, true, fgPaint);
        canvas.drawArc(outerRectF, 315, 45, true, bgPaint);

        canvas.drawCircle(cx, cy, width / 2.7f, bgPaint);

        canvas.restore();

        canvas.save();
        canvas.rotate(-degree, cx, cy);

        canvas.drawArc(innerRectF, 0, 45, true, bgPaint);
        canvas.drawArc(innerRectF, 45, 45, true, fgPaint);
        canvas.drawArc(innerRectF, 90, 45, true, bgPaint);
        canvas.drawArc(innerRectF, 135, 45, true, fgPaint);
        canvas.drawArc(innerRectF, 180, 45, true, bgPaint);
        canvas.drawArc(innerRectF, 225, 45, true, fgPaint);
        canvas.drawArc(innerRectF, 270, 45, true, bgPaint);
        canvas.drawArc(innerRectF, 315, 45, true, fgPaint);

        canvas.restore();

        canvas.drawCircle(cx, cy, cx / 8, fgPaint);
    }

}
