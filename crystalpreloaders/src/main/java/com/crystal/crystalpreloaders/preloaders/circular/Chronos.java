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
public class Chronos extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;

    public Chronos(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(7000);
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
        float radius2 = width / 2.5f;
        float radius3 = width / 3f;

        canvas.drawCircle(cx, cy, radius1, fgPaint);
        canvas.drawCircle(cx, cy, radius2, bgPaint);
        canvas.drawCircle(cx, cy, radius3, fgPaint);

        bgPaint.setStrokeWidth(3f);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStrokeJoin(Paint.Join.BEVEL);

        float x1  = cx - (radius1 - radius3);
        float y1  = 0;
        float yy1 = radius1 - radius2;

        float y2  = radius1 - radius2;
        float xx2 = cx + (radius1 - radius3);
        float yy2 = 0f;

        float x3  = cx - (radius1 - radius3);
        float y3  = radius1 - radius3;
        float yy3 = cy - (radius1 - radius2 / 1.5f);

        float y4  = cy - (radius1 - radius2 / 1.5f);
        float xx4 = cx + (radius1 - radius3);
        float yy4 = radius1 - radius3;

        canvas.save();
        canvas.rotate(degree, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 45, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();
        canvas.rotate(-degree * 2 + 45, cx, cy);

        canvas.drawLine(x3, y3, cx, yy3, bgPaint);
        canvas.drawLine(cx, y4, xx4, yy4, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 90, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 135, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(-degree * 2 + 135, cx, cy);

        canvas.drawLine(x3, y3, cx, yy3, bgPaint);
        canvas.drawLine(cx, y4, xx4, yy4, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 180, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 225, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();
        canvas.rotate(-degree * 2 + 225, cx, cy);

        canvas.drawLine(x3, y3, cx, yy3, bgPaint);
        canvas.drawLine(cx, y4, xx4, yy4, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 270, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();

        canvas.rotate(degree + 315, cx, cy);

        canvas.drawLine(x1, y1, cx, yy1, bgPaint);
        canvas.drawLine(cx, y2, xx2, yy2, bgPaint);

        canvas.restore();
        canvas.save();
        canvas.rotate(-degree * 2 + 315, cx, cy);

        canvas.drawLine(x3, y3, cx, yy3, bgPaint);
        canvas.drawLine(cx, y4, xx4, yy4, bgPaint);

        canvas.restore();

        /*bgPaint.setStrokeWidth(2f);
        fgPaint.setStrokeWidth(2f);

        canvas.save();
        canvas.rotate(degree, cx, cy);

        for(int i = 180; i <= 225; i++){
            double ang = convertDegToRad(i);

            float startX = (float) (cx + radius1 * Math.sin(ang));
            float startY = (float) (cy - radius1 * Math.cos(ang));

            float stopX = (float) (cx + (radius1 - radius1) * Math.sin(ang));
            float stopY = (float) (cy - (radius1 - radius1) * Math.cos(ang));

            canvas.drawLine(startX, startY, stopX, stopY, bgPaint);
        }

        for(int i = 180; i <= 225; i++){
            double ang = convertDegToRad(i);

            float startX = (float) (cx + radius2 * Math.sin(ang));
            float startY = (float) (cy - radius2 * Math.cos(ang));

            float stopX = (float) (cx + (radius2 - (radius1 - radius2)) * Math.sin(ang));
            float stopY = (float) (cy - (radius2 - (radius1 - radius2)) * Math.cos(ang));

            canvas.drawLine(startX, startY, stopX, stopY, fgPaint);
        }

        canvas.restore();*/
    }
}
