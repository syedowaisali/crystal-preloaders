package com.crystalpreloaders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crystalpreloaders.base.BaseFragment;

import java.util.List;

/**
 * Created by owais.ali on 7/15/2016.
 */
public class PreloaderPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;

    public PreloaderPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
