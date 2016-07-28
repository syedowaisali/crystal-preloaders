package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class BallSpinFade extends BasePreloader {

    private static final int DELAY = 300;
    private PropertyValuesHolder radiusValueHolder;
    private PropertyValuesHolder opacityValueHolder;
    private ValueAnimator[] valueAnimators;
    private int radius;
    private int[] circleRadius;
    private int[] circleOpacity;

    public BallSpinFade(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        radius = Math.min(getWidth(), getHeight()) / 9;
        circleRadius = new int[10];
        circleOpacity = new int[10];
        valueAnimators = new ValueAnimator[10];
        for(int i = 0; i < circleRadius.length; i++){
            circleRadius[i] = radius;
            circleOpacity[i] = 250;
        }
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        radiusValueHolder = PropertyValuesHolder.ofInt("radius", radius, 5);
        opacityValueHolder = PropertyValuesHolder.ofInt("opacity", 250, 20);

        for(int i = 0; i < valueAnimators.length; i++){
            setAnimator(i);
        }



        return Arrays.asList(valueAnimators);
    }

    private void setAnimator(final int index){

        valueAnimators[index] = ValueAnimator.ofPropertyValuesHolder(radiusValueHolder, opacityValueHolder);
        valueAnimators[index].setStartDelay(index * DELAY);
        valueAnimators[index].setDuration(1000);
        valueAnimators[index].setRepeatCount(ValueAnimator.INFINITE);
        valueAnimators[index].setRepeatMode(ValueAnimator.REVERSE);
        valueAnimators[index].setInterpolator(new AccelerateInterpolator());
        valueAnimators[index].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleRadius[index]  = (int) animation.getAnimatedValue("radius");
                circleOpacity[index] = (int) animation.getAnimatedValue("opacity");
                if(index == 0 || index == valueAnimators.length){
                    getTarget().invalidate();
                }
            }
        });
    }

    @Override
    protected void startAnimation() {

        for (ValueAnimator valueAnimator : valueAnimators) {
            valueAnimator.start();
        }
    }

    @Override
    public synchronized void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        for(int i = 0; i < valueAnimators.length; i++){
            canvas.save();
            canvas.rotate(i * 45, cx, cy);
            fgPaint.setAlpha(circleOpacity[i]);
            canvas.drawCircle(radius , cy, circleRadius[i], fgPaint);
            canvas.restore();
        }

    }
}
