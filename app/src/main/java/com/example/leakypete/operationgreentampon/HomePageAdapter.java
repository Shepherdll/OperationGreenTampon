package com.example.leakypete.operationgreentampon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by That Sexy Taylor on 6/19/2017.
 */

class HomePageAdapter extends FragmentPagerAdapter {


    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TopFragment TopFragment = new TopFragment();
                return TopFragment;


            case 1:
                TrendingFragment TrendingFragment = new TrendingFragment();
                return TrendingFragment;

            case 2:
                NewFragment NewFragment = new NewFragment();
                return NewFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int postition){

      switch (postition){
          case 0:
              return "Top";
          case 1:
              return "Trending";
          case 2:
              return "New";

          default:
              return null;
      }

    }
}
