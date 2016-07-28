package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class Ventilator extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int pos;

    public Ventilator(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 15);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pos = (int) animation.getAnimatedValue();
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

        fgPaint.setStrokeWidth(width / 18);
        fgPaint.setStrokeCap(Paint.Cap.ROUND);

        for(int i = 0; i < 15; i++){
            canvas.save();
            canvas.rotate(i * 24f, cx, cy);

            if(i == pos){
                LinearGradient linearGradient1 = new LinearGradient(cx, cy - (cx / 3.5f), (width / 3), 10, Color.GRAY, bgPaint.getColor(), Shader.TileMode.CLAMP);
                fgPaint.setShader(linearGradient1);
            }
            else{
                LinearGradient linearGradient1 = new LinearGradient(cx, cy - (cx / 3.5f), (width / 3), 10, fgPaint.getColor(), bgPaint.getColor(), Shader.TileMode.CLAMP);
                fgPaint.setShader(linearGradient1);
            }

            canvas.drawLine(cx, cy - (cx / 3.5f), (width / 3), 10, fgPaint);
            canvas.restore();
        }
    }
}
