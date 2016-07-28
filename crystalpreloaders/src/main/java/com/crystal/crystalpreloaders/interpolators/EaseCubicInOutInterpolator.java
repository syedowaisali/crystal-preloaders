package com.crystal.crystalpreloaders.interpolators;

import android.view.animation.Interpolator;

/**
 * Created by owais.ali on 7/27/2016.
 */
public class EaseCubicInOutInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float input) {
        if((input /= 0.5f) < 1) {
            return 0.5f * input * input * input;
        }
        return 0.5f * ((input -= 2) * input * input + 2);
    }
}
