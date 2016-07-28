package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/20/2016.
 */
public class Triplex extends BasePreloader {

    private ValueAnimator valueAnimator;
    private Path path;
    private int degree;

    public Triplex(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        path = new Path();
        drawPath1(path);
        drawPath2(path);
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(2000);
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

        //fgPaint.setStyle(Paint.Style.STROKE);

        float radius = (width / 2) - (width / 2.88f);
        float y = cy - getWidth() / 4.8f;

        canvas.drawPath(path, fgPaint);
        canvas.drawCircle(cx, y, radius, fgPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(degree + 120, cx, cy);

        canvas.drawPath(path, fgPaint);
        canvas.drawCircle(cx, y, radius, fgPaint);

        canvas.restore();

        canvas.save();
        canvas.rotate(degree + 240, cx, cy);

        canvas.drawPath(path, fgPaint);
        canvas.drawCircle(cx, y, radius, fgPaint);

        canvas.restore();


    }

    private void drawPath1(Path path){

        float x1 = getWidth() / 2;
        float y1 = getHeight() / 2 - (getHeight() / 2.75f);

        float x3 = getWidth() / 2 - (getWidth() / 3.5f);
        float y3 = getHeight() / 2 + (getHeight() / 3.8f);

        float x2 = -1 * getWidth() / 3.5f;
        float y2 = getHeight() / 25;

        // move and draw
        path.moveTo(x1, y1);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }

    private void drawPath2(Path path){

        float x1 = getWidth() / 2 - (getWidth() / 3.5f);
        float y1 = getHeight() / 2 + (getHeight() / 3.8f);

        float x3 = getWidth() / 2;
        float y3 = getHeight() / 2 - (getHeight() / 5.8f);

        float x2 = -1 * getWidth() / 7;
        float y2 = getHeight() / 5;

        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }
}
