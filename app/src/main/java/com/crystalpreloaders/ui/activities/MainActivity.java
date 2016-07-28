package com.crystalpreloaders.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crystalpreloaders.R;
import com.crystalpreloaders.adapters.PreloaderPagerAdapter;
import com.crystalpreloaders.base.BaseFragment;
import com.crystalpreloaders.ui.fragments.CircularFragment;
import com.crystalpreloaders.ui.fragments.RectangularFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        final TabLayout tabLayout = getView(R.id.tabLayout);
        final ViewPager viewPager = getView(R.id.viewPager);

        // set fragments list
        final List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new CircularFragment());
        fragments.add(new RectangularFragment());

        // create view pager adapter
        PreloaderPagerAdapter adapter = new PreloaderPagerAdapter(getSupportFragmentManager(), fragments);

        // set adapter to pager
        viewPager.setAdapter(adapter);

        // set view pager to tab layout
        tabLayout.setupWithViewPager(viewPager);
    }

    public final <T> T getView(int resId){
        return (T)findViewById(resId);
    }
}
