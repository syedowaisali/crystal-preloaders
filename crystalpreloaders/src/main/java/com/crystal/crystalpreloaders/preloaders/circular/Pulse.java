package com.crystal.crystalpreloaders.preloaders.circular;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

import com.crystal.crystalpreloaders.base.BasePreloader;

import java.util.List;

/**
 * Created by owais.ali on 7/21/2016.
 */
public class Pulse extends BasePreloader implements Runnable {

    private int pos;
    private float[] radius;
    private boolean on;
    private Handler handler;

    public Pulse(View target, int size) {
        super(target, size);
    }

    @Override
    protected void init() {

        handler = new Handler();

        on = true;
        float width = getWidth();

        radius = new float[5];
        radius[0] = width / 2 - (width / 15f / 1.9f);
        radius[1] = width / 2.8f;
        radius[2] = width / 4f;
        radius[3] = width / 7f;
        radius[4] = width / 30f;

    }

    @Override
    protected List<ValueAnimator> setupAnimation() {
        handler.postDelayed(this, 0);
        return null;
    }

    @Override
    protected void startAnimation() {

    }

    @Override
    public void run() {
        pos = (pos < 4) ? ++pos : 0;
        getTarget().invalidate();
        handler.postDelayed(this, 50);
    }

    @Override
    public void onDraw(Canvas canvas, Paint fgPaint, Paint bgPaint, float width, float height, float cx, float cy) {

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(width / 15f);

        bgPaint.setStyle(fgPaint.getStyle());
        bgPaint.setStrokeWidth(fgPaint.getStrokeWidth());

        if(on){

            for(int i = 0; i <= pos; i++){
                int p = Math.abs(i - (radius.length - 1));
                canvas.drawCircle(cx, cy, radius[p], fgPaint);
            }

            if(pos == 4){
                on = false;
            }
        }
        else{

            for(int i = 0; i <= pos; i++){
                canvas.drawCircle(cx, cy, radius[i], bgPaint);
            }

            for(int i = pos + 1; i <= radius.length - 1; i++){
                canvas.drawCircle(cx, cy, radius[i], fgPaint);
            }

            if(pos == 4){
                on = true;
            }
        }
    }
}
