package com.special.newsdemo;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.special.newsdemo.Adapter.NewsPagerAdapter;
import com.special.newsdemo.Fragment.NewsFragment;
import com.special.newsdemo.util.Utility;

public class MainActivity extends AppCompatActivity {
    private static final String[] tabMenus = Utility.getTabMenus();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.news_tab);
        viewPager = (ViewPager) findViewById(R.id.news_viewpager);
        NewsPagerAdapter newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager());
        for(int i = 0; i < tabMenus.length; i++)
            newsPagerAdapter.add(NewsFragment.newInstance(i + 1),tabMenus[i]);
        viewPager.setAdapter(newsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(Gravity.FILL);
    }
}
