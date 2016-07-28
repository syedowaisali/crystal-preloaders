package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.CycleInterpolator;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais.ali on 7/19/2016.
 */
public class SkypeBalls extends BasePreloader {

    private float radius;

    private double c1Angle;
    private double c2Angle;
    private double c3Angle;
    private double c4Angle;
    private double c5Angle;

    private float c1Radius;
    private float c2Radius;
    private float c3Radius;
    private float c4Radius;
    private float c5Radius;

    private float cRadius;

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3, valueAnimator4, valueAnimator5;
    private ValueAnimator radiusAnimatorStart1, radiusAnimatorStart2, radiusAnimatorStart3, radiusAnimatorStart4, radiusAnimatorStart5;
    private ValueAnimator radiusAnimatorEnd1, radiusAnimatorEnd2, radiusAnimatorEnd3, radiusAnimatorEnd4, radiusAnimatorEnd5;
    private ValueAnimator bounceAnimator;

    public SkypeBalls(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {
        c1Angle = 180f;
        c2Angle = 180f;
        c3Angle = 180f;
        c4Angle = 180f;
        c5Angle = 180f;
        c1Radius = getRadius();
        c2Radius = getRadius();
        c3Radius = getRadius();
        c4Radius = getRadius();
        c5Radius = getRadius();

        cRadius = getRadius();
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        radius = Math.min(cx, cy) - 3;
    }

    @Override
    protected List<ValueAnimator> setupAnimation(){

        // angle animation
        valueAnimator1 = ValueAnimator.ofFloat(180, -180f);
        valueAnimator1.setDuration(1500);
        valueAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c1Angle = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });


        valueAnimator2 = ValueAnimator.ofFloat(180, -180f);
        valueAnimator2.setDuration(1500);
        valueAnimator2.setStartDelay(100);
        valueAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c2Angle = (float) animation.getAnimatedValue();
            }
        });

        valueAnimator3 = ValueAnimator.ofFloat(180, -180f);
        valueAnimator3.setDuration(1500);
        valueAnimator3.setStartDelay(200);
        valueAnimator3.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c3Angle = (float) animation.getAnimatedValue();
            }
        });

        valueAnimator4 = ValueAnimator.ofFloat(180, -180f);
        valueAnimator4.setDuration(1500);
        valueAnimator4.setStartDelay(300);
        valueAnimator4.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c4Angle = (float) animation.getAnimatedValue();
            }
        });

        valueAnimator5 = ValueAnimator.ofFloat(180, -180f);
        valueAnimator5.setDuration(1500);
        valueAnimator5.setStartDelay(400);
        valueAnimator5.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator5.addListener(this);
        valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c5Angle = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        // radius start animation
        radiusAnimatorStart1 = ValueAnimator.ofFloat(cRadius, cRadius / 2);
        radiusAnimatorStart1.setDuration(500);
        radiusAnimatorStart1.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorStart1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c1Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorStart2 = ValueAnimator.ofFloat(cRadius, cRadius / 2);
        radiusAnimatorStart2.setDuration(500);
        radiusAnimatorStart2.setStartDelay(100);
        radiusAnimatorStart2.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorStart2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c2Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorStart3 = ValueAnimator.ofFloat(cRadius, cRadius / 2);
        radiusAnimatorStart3.setDuration(500);
        radiusAnimatorStart3.setStartDelay(200);
        radiusAnimatorStart3.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorStart3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c3Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorStart4 = ValueAnimator.ofFloat(cRadius, cRadius / 2);
        radiusAnimatorStart4.setDuration(500);
        radiusAnimatorStart4.setStartDelay(300);
        radiusAnimatorStart4.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorStart4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c4Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorStart5 = ValueAnimator.ofFloat(cRadius, cRadius / 2);
        radiusAnimatorStart5.setDuration(500);
        radiusAnimatorStart5.setStartDelay(400);
        radiusAnimatorStart5.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorStart5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c5Radius = (float) animation.getAnimatedValue();
            }
        });

        // radius end animation
        radiusAnimatorEnd1 = ValueAnimator.ofFloat(cRadius / 2, cRadius);
        radiusAnimatorEnd1.setDuration(300);
        radiusAnimatorEnd1.setStartDelay(1200);
        radiusAnimatorEnd1.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorEnd1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c1Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorEnd2 = ValueAnimator.ofFloat(cRadius / 2, cRadius);
        radiusAnimatorEnd2.setDuration(300);
        radiusAnimatorEnd2.setStartDelay(1300);
        radiusAnimatorEnd2.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorEnd2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c2Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorEnd3 = ValueAnimator.ofFloat(cRadius / 2, cRadius);
        radiusAnimatorEnd3.setDuration(300);
        radiusAnimatorEnd3.setStartDelay(1400);
        radiusAnimatorEnd3.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorEnd3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c3Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorEnd4 = ValueAnimator.ofFloat(cRadius / 2, cRadius);
        radiusAnimatorEnd4.setDuration(300);
        radiusAnimatorEnd4.setStartDelay(1500);
        radiusAnimatorEnd4.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorEnd4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c4Radius = (float) animation.getAnimatedValue();
            }
        });

        radiusAnimatorEnd5 = ValueAnimator.ofFloat(cRadius / 2, cRadius);
        radiusAnimatorEnd5.setDuration(300);
        radiusAnimatorEnd5.setStartDelay(1600);
        radiusAnimatorEnd5.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimatorEnd5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c5Radius = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        // bouncing animation
        bounceAnimator = ValueAnimator.ofFloat(cRadius, cRadius + 3);
        bounceAnimator.setDuration(500);
        bounceAnimator.setStartDelay(1800);
        bounceAnimator.setInterpolator(new CycleInterpolator(1));
        bounceAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c5Radius = (float) animation.getAnimatedValue();
                getTarget().invalidate();
            }
        });

        // creator animator list
        final List<ValueAnimator> animators = new ArrayList<>();
        animators.add(valueAnimator1);
        animators.add(valueAnimator2);
        animators.add(valueAnimator3);
        animators.add(valueAnimator4);
        animators.add(valueAnimator5);
        animators.add(radiusAnimatorStart1);
        animators.add(radiusAnimatorStart2);
        animators.add(radiusAnimatorStart3);
        animators.add(radiusAnimatorStart4);
        animators.add(radiusAnimatorStart5);
        animators.add(radiusAnimatorEnd1);
        animators.add(radiusAnimatorEnd2);
        animators.add(radiusAnimatorEnd3);
        animators.add(radiusAnimatorEnd4);
        animators.add(radiusAnimatorEnd5);
        animators.add(bounceAnimator);

        return animators;
    }

    @Override
    protected void startAnimation(){

        valueAnimator1.start();
        valueAnimator2.start();
        valueAnimator3.start();
        valueAnimator4.start();
        valueAnimator5.start();
        radiusAnimatorStart1.start();
        radiusAnimatorStart2.start();
        radiusAnimatorStart3.start();
        radiusAnimatorStart4.start();
        radiusAnimatorStart5.start();
        radiusAnimatorEnd1.start();
        radiusAnimatorEnd2.start();
        radiusAnimatorEnd3.start();
        radiusAnimatorEnd4.start();
        radiusAnimatorEnd5.start();
        bounceAnimator.start();
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        }, 600);
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        double angle1 = convertDegToRad(c1Angle);
        double x1 = cx + (radius - c1Radius) * Math.sin(angle1);
        double y1 = cy + (radius - c1Radius) * Math.cos(angle1);
        canvas.drawCircle((float)x1, (float)y1, c1Radius, fgPaint);

        double angle2 = convertDegToRad(c2Angle);
        double x2 = cx + (radius - c2Radius) * Math.sin(angle2);
        double y2 = cy + (radius - c2Radius) * Math.cos(angle2);
        canvas.drawCircle((float)x2, (float)y2, c2Radius, fgPaint);

        double angle3 = convertDegToRad(c3Angle);
        double x3 = cx + (radius - c3Radius) * Math.sin(angle3);
        double y3 = cy + (radius - c3Radius) * Math.cos(angle3);
        canvas.drawCircle((float)x3, (float)y3, c3Radius, fgPaint);

        double angle4 = convertDegToRad(c4Angle);
        double x4 = cx + (radius - c4Radius) * Math.sin(angle4);
        double y4 = cy + (radius - c4Radius) * Math.cos(angle4);
        canvas.drawCircle((float)x4, (float)y4, c4Radius, fgPaint);

        double angle5 = convertDegToRad(c5Angle);
        double x5 = cx + (radius - c5Radius) * Math.sin(angle5);
        double y5 = cy + (radius - c5Radius) * Math.cos(angle5);
        canvas.drawCircle((float)x5, (float)y5, c5Radius, fgPaint);
    }

    private float getRadius(){
        float retRadius = 0f;

        switch (getSize()){
            case CrystalPreloader.Size.VERY_SMALL: retRadius = getTarget().getResources().getDimension(R.dimen.skype_ball_size_vs); break;
            case CrystalPreloader.Size.SMALL: retRadius = getTarget().getResources().getDimension(R.dimen.skype_ball_size_s); break;
            case CrystalPreloader.Size.MEDIUM: retRadius = getTarget().getResources().getDimension(R.dimen.skype_ball_size_m); break;
            case CrystalPreloader.Size.LARGE: retRadius = getTarget().getResources().getDimension(R.dimen.skype_ball_size_l); break;
            case CrystalPreloader.Size.EXTRA_LARGE: retRadius = getTarget().getResources().getDimension(R.dimen.skype_ball_size_el); break;
        }

        return retRadius;
    }

}
