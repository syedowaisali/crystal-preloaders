package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/19/2016.
 */
public class Hasher extends BasePreloader {

    private ValueAnimator valueAnimator;
    private int degree;
    private Path path;
    private float factor;

    public Hasher(View target, int size) {
        super(target, size);
        init();
    }

    @Override
    protected void init(){
        factor = getFactor();
        path = new Path();
        drawPath1(path, factor);
        drawPath2(path, factor);
        drawPath3( path, factor);
        drawPath4(path, factor);
    }

    @Override
    protected List<ValueAnimator> setupAnimation(){

        valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(1500);
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
        //canvas.drawColor(Color.YELLOW);

        fgPaint.setStyle(Paint.Style.FILL);
        fgPaint.setStrokeWidth(3f);
        fgPaint.setAntiAlias(true);

        canvas.save();
        canvas.scale(0.75f, 0.75f, cx, cy);
        canvas.rotate(degree, cx, cy);

        canvas.drawPath(path, fgPaint);
        canvas.restore();

        canvas.drawCircle(cx, cx, factor + factor - factor / 8, bgPaint);
        canvas.drawCircle(cx, cx, factor + factor - factor / 8 - 4, fgPaint);
        canvas.drawCircle(cx, cx, factor / 3, bgPaint);

    }

    private int getFactor(){
        float retFactor = 10;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: retFactor = getTarget().getResources().getDimension(R.dimen.hasher_factor_vs); break;
            case CrystalPreloader.Size.SMALL: retFactor = getTarget().getResources().getDimension(R.dimen.hasher_factor_s); break;
            case CrystalPreloader.Size.MEDIUM: retFactor = getTarget().getResources().getDimension(R.dimen.hasher_factor_m); break;
            case CrystalPreloader.Size.LARGE: retFactor = getTarget().getResources().getDimension(R.dimen.hasher_factor_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: retFactor = getTarget().getResources().getDimension(R.dimen.hasher_factor_el); break;
        }

        return (int)retFactor;
    }

    private void drawPath1(Path path, float factor){

        float x1 = getWidth();
        float y1 = 0f;

        float x3 = getWidth() / 2;
        float y3 = getHeight() / 2 + factor;

        float x2 = getWidth() - factor * 2;
        float y2 = getHeight() / 2;

        // move and draw
        path.moveTo(getWidth(), 0f);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }

    private void drawPath2(Path path, float factor){

        float x1 = getWidth() / 2;
        float y1 = getHeight() / 2 + factor;

        float x3 = 0f;
        float y3 = getHeight();

        float x2 = factor * 2;
        float y2 = getHeight() / 2 + factor * 2;

        // move and draw
        //path.moveTo(getWidth() / 2, getHeight() / 2 + factor);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }

    private void drawPath3(Path path, float factor){

        float x1 = 0;
        float y1 = getHeight();

        float x3 = getWidth() / 2;
        float y3 = getHeight() / 2 - factor;

        float x2 = factor * 2;
        float y2 = getHeight() / 2;

        // move and draw
        //path.moveTo(0, getHeight());
        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }

    private void drawPath4(Path path, float factor){

        float x1 = getWidth() / 2;
        float y1 = getHeight() / 2 - factor;

        float x3 = getWidth();
        float y3 = 0;

        float x2 = getWidth() - factor * 2;
        float y2 = getHeight() / 2 - factor * 2;

        // move and draw
        //path.moveTo(getWidth() / 2, getHeight() / 2 - factor);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
    }
}
