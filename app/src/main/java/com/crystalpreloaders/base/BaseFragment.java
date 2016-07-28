package com.crystalpreloaders.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by owais.ali on 7/18/2016.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }

        try {
            rootView = inflater.inflate(getLayout(), container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public final void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    protected void init(){}

    public abstract int getLayout();
    public abstract String getTitle();
}
