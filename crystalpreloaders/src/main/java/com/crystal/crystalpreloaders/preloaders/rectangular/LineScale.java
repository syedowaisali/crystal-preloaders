package com.crystal.crystalpreloaders.preloaders.rectangular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class LineScale extends BasePreloader {

    private static final int TOTAL_LINES = 5;
    private static float STROKE_WIDTH;

    private ValueAnimator[] valueAnimators;
    private float minScale, maxScale;
    private float[] x;
    private float[] yScale;
    private int[] delays;

    public LineScale(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

        float width = getWidth();
        float cx = width / 2;

        STROKE_WIDTH = width / 8f;

        minScale = 0.5f;
        maxScale = 1f;

        x = new float[TOTAL_LINES];
        x[0] = STROKE_WIDTH / 2;
        x[1] = cx / 2 + x[0] / 2;
        x[2] = cx;
        x[3] = cx + cx / 2 - x[0] / 2;
        x[4] = width - STROKE_WIDTH / 2;

        yScale = new float[TOTAL_LINES];
        for(int i = 0; i < TOTAL_LINES; i++){
            yScale[i] = maxScale;
        }

        delays = new int[TOTAL_LINES];
        delays[0] = 0;
        delays[1] = 200;
        delays[2] = 350;
        delays[3] = 500;
        delays[4] = 650;

        valueAnimators = new ValueAnimator[TOTAL_LINES];
    }

    @Override
    protected List<ValueAnimator> setupAnimation() {

        for(int i = 0; i < TOTAL_LINES; i++){
            setAnimator(i);
        }

        return Arrays.asList(valueAnimators);
    }

    private void setAnimator(final int index){

        valueAnimators[index] = ValueAnimator.ofFloat(maxScale, minScale);
        valueAnimators[index].setStartDelay(delays[index]);
        valueAnimators[index].setDuration(650);
        valueAnimators[index].setRepeatCount(ValueAnimator.INFINITE);
        valueAnimators[index].setRepeatMode(ValueAnimator.REVERSE);
        valueAnimators[index].setInterpolator(new AccelerateInterpolator());
        valueAnimators[index].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yScale[index] = (float) animation.getAnimatedValue();
                if(index == 0 || index == valueAnimators.length){
                    getTarget().invalidate();
                }
            }
        });
    }

    @Override
    protected void startAnimation() {

        for (int i = 0; i < TOTAL_LINES; i++){
            valueAnimators[i].start();
        }
    }

    @Override
    public synchronized void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        fgPaint.setStrokeWidth(STROKE_WIDTH);
        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeJoin(Paint.Join.ROUND);
        fgPaint.setStrokeCap(Paint.Cap.ROUND);

        for(int i = 0; i < TOTAL_LINES; i++){
            canvas.save();
            canvas.scale(1, yScale[i], cx, cy);
            canvas.drawLine(x[i], x[0], x[i], height - x[0], fgPaint);
            canvas.restore();
        }

    }

    @Override
    public int getWidth() {
        float width = 0;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: width = getTarget().getResources().getDimension(R.dimen.width_line_scale_vs); break;
            case CrystalPreloader.Size.SMALL: width = getTarget().getResources().getDimension(R.dimen.width_line_scale_s); break;
            case CrystalPreloader.Size.MEDIUM: width = getTarget().getResources().getDimension(R.dimen.width_line_scale_m); break;
            case CrystalPreloader.Size.LARGE: width = getTarget().getResources().getDimension(R.dimen.width_line_scale_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: width = getTarget().getResources().getDimension(R.dimen.width_line_scale_el); break;
        }

        return (int)width;
    }

    @Override
    public int getHeight() {
        float height = 0;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: height = getTarget().getResources().getDimension(R.dimen.height_line_scale_vs); break;
            case CrystalPreloader.Size.SMALL: height = getTarget().getResources().getDimension(R.dimen.height_line_scale_s); break;
            case CrystalPreloader.Size.MEDIUM: height = getTarget().getResources().getDimension(R.dimen.height_line_scale_m); break;
            case CrystalPreloader.Size.LARGE: height = getTarget().getResources().getDimension(R.dimen.height_line_scale_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: height = getTarget().getResources().getDimension(R.dimen.height_line_scale_el); break;
        }

        return (int)height;
    }
}
