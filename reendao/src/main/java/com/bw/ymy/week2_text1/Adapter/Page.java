package com.bw.ymy.week2_text1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bw.ymy.week2_text1.activity.one;
import com.bw.ymy.week2_text1.activity.three;
import com.bw.ymy.week2_text1.activity.two;

public class Page extends FragmentPagerAdapter {
    String[] str = new String[]{
            "详情","评论","价格"
    };

    public Page(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case  0:
                return  new one();

            case 1:
                return  new two();
            case 2:
                return  new three();
            default:
                return  new Fragment();
        }


    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }


    @Override
    public int getCount() {
        return str.length;
    }
}
