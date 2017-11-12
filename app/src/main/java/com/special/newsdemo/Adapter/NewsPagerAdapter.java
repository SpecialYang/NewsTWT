package com.special.newsdemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Special on 2017/11/12.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabMenus = new ArrayList<>();

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void add(Fragment fragment, String title){
        fragments.add(fragment);
        tabMenus.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabMenus.get(position);
    }
}
