package ustc.var.com.myapplication001;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ustc.var.com.myapplication001.image.ImageFragment;
import ustc.var.com.myapplication001.news.NewsFragment;

/**
 *
 * Created by GRY on 2017/10/18.
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTablayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.news));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.image));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.weather));
        mTablayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        List<Fragment> fragments=new ArrayList<>();
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);
        myPagerAdapter.addFragment(new NewsFragment(),getString(R.string.news));
        myPagerAdapter.addFragment(new ImageFragment(),getString(R.string.image));
//        myPagerAdapter.addFragment(new BlankFragment(),getString(R.string.image));
        myPagerAdapter.addFragment(new WeatherFragment(),getString(R.string.weather));
        viewPager.setAdapter(myPagerAdapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mFragmentTitles=new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            mFragments=fragments;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
