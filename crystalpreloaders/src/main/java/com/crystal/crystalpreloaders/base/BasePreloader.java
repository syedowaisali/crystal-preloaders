package com.crystal.crystalpreloaders.base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.List;


/**
 * Created by owais.ali on 7/19/2016.
 */
public abstract class BasePreloader implements ValueAnimator.AnimatorUpdateListener, ValueAnimator.AnimatorListener {

    private final View mTarget;
    private final int mSize;

    protected BasePreloader(View target, int size){
        mTarget = target;
        mSize   = size;
        init();
        List<ValueAnimator> animators = setupAnimation();
        startAnimation();
    }

    protected View getTarget(){
        return mTarget;
    }

    protected int getSize(){
        return mSize;
    }

    public int getWidth(){
        float width = 100;
        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: width = getTarget().getResources().getDimension(R.dimen.width_vs); break;
            case CrystalPreloader.Size.SMALL: width = getTarget().getResources().getDimension(R.dimen.width_s); break;
            case CrystalPreloader.Size.MEDIUM: width = getTarget().getResources().getDimension(R.dimen.width_m); break;
            case CrystalPreloader.Size.LARGE: width = getTarget().getResources().getDimension(R.dimen.width_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: width = getTarget().getResources().getDimension(R.dimen.width_el); break;
        }

        return (int)width;
    }

    public int getHeight(){
        float height = 0;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: height = getTarget().getResources().getDimension(R.dimen.height_vs); break;
            case CrystalPreloader.Size.SMALL: height = getTarget().getResources().getDimension(R.dimen.height_s); break;
            case CrystalPreloader.Size.MEDIUM: height = getTarget().getResources().getDimension(R.dimen.height_m); break;
            case CrystalPreloader.Size.LARGE: height = getTarget().getResources().getDimension(R.dimen.height_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: height = getTarget().getResources().getDimension(R.dimen.height_el); break;
        }

        return (int)height;
    }

    protected double convertDegToRad(double angle){
        return (angle * Math.PI / 180f);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }

    protected final void log(Object object){
        Log.d("CRS=>", String.valueOf(object));
    }

    protected abstract void init();
    protected abstract List<ValueAnimator> setupAnimation();
    protected abstract void startAnimation();
    public abstract void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy);
}
