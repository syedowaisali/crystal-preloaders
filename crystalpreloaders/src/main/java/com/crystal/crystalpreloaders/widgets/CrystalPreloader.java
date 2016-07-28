package com.crystal.crystalpreloaders.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.crystal.crystalpreloaders.R;
import com.crystal.crystalpreloaders.base.BasePreloader;
import com.crystal.crystalpreloaders.preloaders.circular.BallBeat;
import com.crystal.crystalpreloaders.preloaders.circular.BallPulse;
import com.crystal.crystalpreloaders.preloaders.circular.BallPulseSync;
import com.crystal.crystalpreloaders.preloaders.circular.BallRing;
import com.crystal.crystalpreloaders.preloaders.circular.BallScale;
import com.crystal.crystalpreloaders.preloaders.circular.BallSpinFade;
import com.crystal.crystalpreloaders.preloaders.circular.Chronos;
import com.crystal.crystalpreloaders.preloaders.circular.Circular;
import com.crystal.crystalpreloaders.preloaders.circular.ExpandableBalls;
import com.crystal.crystalpreloaders.preloaders.circular.InCircle;
import com.crystal.crystalpreloaders.preloaders.rectangular.LineScale;
import com.crystal.crystalpreloaders.preloaders.rectangular.LineScalePulseOut;
import com.crystal.crystalpreloaders.preloaders.rectangular.LineScalePulseOutRapid;
import com.crystal.crystalpreloaders.preloaders.circular.Pulse;
import com.crystal.crystalpreloaders.preloaders.circular.TimeMachine;
import com.crystal.crystalpreloaders.preloaders.circular.Alternative;
import com.crystal.crystalpreloaders.preloaders.circular.Hasher;
import com.crystal.crystalpreloaders.preloaders.circular.SkypeBalls;
import com.crystal.crystalpreloaders.preloaders.circular.HalfMoon;
import com.crystal.crystalpreloaders.preloaders.circular.TornadoCircle1;
import com.crystal.crystalpreloaders.preloaders.circular.TornadoCircle2;
import com.crystal.crystalpreloaders.preloaders.circular.TornadoCircle3;
import com.crystal.crystalpreloaders.preloaders.circular.Triplex;
import com.crystal.crystalpreloaders.preloaders.circular.Ventilator;

/**
 * Created by owais.ali on 7/18/2016.
 */
public class CrystalPreloader extends View {

    ////////////////////////////////////////
    // PUBLIC CLASS CONSTANTS
    ////////////////////////////////////////

    public static final class Size{
        public static final int VERY_SMALL  = 0;
        public static final int SMALL       = 1;
        public static final int MEDIUM      = 2;
        public static final int LARGE       = 3;
        public static final int EXTRA_LARGE = 4;
    }

    public static final class Style{
        public static final int SKYPE_BALLS = 0;
        public static final int HASHER = 1;
        public static final int ALTERNATIVE = 2;
        public static final int TRIPLEX = 3;
        public static final int TIME_MACHINE = 4;
        public static final int CHRONOS = 5;
        public static final int IN_CIRCLE = 6;
        public static final int VENTILATOR = 7;
        public static final int PULSE = 8;
        public static final int HALF_MOON = 9;
        public static final int BALL_PULSE = 10;
        public static final int BALL_SCALE = 11;
        public static final int BALL_SPIN_FADE = 12;
        public static final int BALL_PULSE_SYNC = 13;
        public static final int BALL_BEAT = 14;
        public static final int LINE_SCALE = 15;
        public static final int LINE_SCALE_PULSE_OUT = 16;
        public static final int LINE_SCALE_PULSE_OUT_RAPID = 17;
        public static final int EXPANDABLE_BALLS = 18;
        public static final int CIRCULAR = 19;
        public static final int BALL_RING = 20;
        public static final int TORNADO_CIRCLE_1 = 21;
        public static final int TORNADO_CIRCLE_2 = 22;
        public static final int TORNADO_CIRCLE_3 = 23;
    }

    ////////////////////////////////////////
    // PRIVATE VAR
    ////////////////////////////////////////

    private BasePreloader loader;
    private Paint fgPaint;
    private Paint bgPaint;
    private int fgColor;
    private int bgColor;
    private int size;
    private int style;

    ////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////

    public CrystalPreloader(Context context) {
        this(context, null);
    }

    public CrystalPreloader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrystalPreloader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // prevent render is in edit mode
        //if(isInEditMode()) return;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CrystalPreloader);
        try{
            fgColor = getFgColor(array);
            bgColor = getBgColor(array);
            size    = getSize(array);
            style   = getStyle(array);
        }
        finally {
            array.recycle();
        }

        // initialize
        init();
    }

    ////////////////////////////////////////
    // PRIVATE METHODS
    ////////////////////////////////////////

    private void init(){
        fgPaint  = new Paint();
        fgPaint.setAntiAlias(true);
        fgPaint.setColor(getFgColor());
        fgPaint.setStyle(Paint.Style.FILL);
        fgPaint.setDither(true);

        bgPaint  = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(getBgColor());
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setDither(true);

        switch (getStyle()){
            case Style.SKYPE_BALLS: loader = new SkypeBalls(this, getSize()); break;
            case Style.HASHER: loader = new Hasher(this, getSize()); break;
            case Style.ALTERNATIVE: loader = new Alternative(this, getSize()); break;
            case Style.TRIPLEX: loader = new Triplex(this, getSize()); break;
            case Style.TIME_MACHINE: loader = new TimeMachine(this, getSize()); break;
            case Style.CHRONOS: loader = new Chronos(this, getSize()); break;
            case Style.IN_CIRCLE: loader = new InCircle(this, getSize()); break;
            case Style.VENTILATOR: loader = new Ventilator(this, getSize()); break;
            case Style.PULSE: loader = new Pulse(this, getSize()); break;
            case Style.HALF_MOON: loader = new HalfMoon(this, getSize()); break;
            case Style.BALL_PULSE: loader = new BallPulse(this, getSize()); break;
            case Style.BALL_SCALE: loader = new BallScale(this, getSize()); break;
            case Style.BALL_SPIN_FADE: loader = new BallSpinFade(this, getSize()); break;
            case Style.BALL_PULSE_SYNC: loader = new BallPulseSync(this, getSize()); break;
            case Style.BALL_BEAT: loader = new BallBeat(this, getSize()); break;
            case Style.LINE_SCALE: loader = new LineScale(this, getSize()); break;
            case Style.LINE_SCALE_PULSE_OUT: loader = new LineScalePulseOut(this, getSize()); break;
            case Style.LINE_SCALE_PULSE_OUT_RAPID: loader = new LineScalePulseOutRapid(this, getSize()); break;
            case Style.EXPANDABLE_BALLS: loader = new ExpandableBalls(this, getSize()); break;
            case Style.CIRCULAR: loader = new Circular(this, getSize()); break;
            case Style.BALL_RING: loader = new BallRing(this, getSize()); break;
            case Style.TORNADO_CIRCLE_1: loader = new TornadoCircle1(this, getSize()); break;
            case Style.TORNADO_CIRCLE_2: loader = new TornadoCircle2(this, getSize()); break;
            case Style.TORNADO_CIRCLE_3: loader = new TornadoCircle3(this, getSize()); break;
            default: loader = new SkypeBalls(this, getSize()); break;
        }

    }

    protected final void log(Object object){
        Log.d("CRS=>", String.valueOf(object));
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }

    ////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////

    public final int getFgColor(){
        return this.fgColor;
    }

    public final int getBgColor(){
        return this.bgColor;
    }

    public final int getSize(){
        return this.size;
    }

    public final int getStyle(){
        return this.style;
    }

    ////////////////////////////////////////
    // PROTECTED METHODS
    ////////////////////////////////////////

    protected int getFgColor(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalPreloader_crs_pl_fg_color, Color.BLACK);
    }

    protected int getBgColor(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalPreloader_crs_pl_bg_color, Color.WHITE);
    }

    protected int getSize(final TypedArray typedArray){
        return typedArray.getInt(R.styleable.CrystalPreloader_crs_pl_size, Size.SMALL);
    }

    protected int getStyle(final TypedArray typedArray){
        return typedArray.getInt(R.styleable.CrystalPreloader_crs_pl_style, Style.SKYPE_BALLS);
    }

    ////////////////////////////////////////
    // OVERRIDE METHODS
    ////////////////////////////////////////


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(loader.getWidth(), loader.getHeight());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        loader.onDraw(canvas, fgPaint, bgPaint, loader.getWidth(), loader.getHeight(), loader.getWidth() / 2, loader.getHeight() / 2);
    }
}
