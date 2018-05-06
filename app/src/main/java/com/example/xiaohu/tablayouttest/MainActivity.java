package com.example.xiaohu.tablayouttest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }
    private void initData() {
        tabs.add("新消息");
        tabs.add("朋友圈");

        fragments.add(new TabFragment(this,tabs.get(0)));
        fragments.add(new SecondFragment(this,tabs.get(1)));

    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tayLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.divider)); //设置分割线的样式
       // linearLayout.setDividerPadding(dip2px(20)); //设置分割线间隔
    }

    //像素单位转换
    public int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    class TabAdapter extends FragmentPagerAdapter {
        public TabAdapter(FragmentManager fm) {
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

        //显示标签上的文字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
