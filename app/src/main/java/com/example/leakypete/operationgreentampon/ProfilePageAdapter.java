package com.example.leakypete.operationgreentampon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by That Sexy Taylor on 6/19/2017.
 */

class ProfilePageAdapter extends FragmentPagerAdapter {


    public ProfilePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PostsFragment PostsFragment = new PostsFragment();
                return PostsFragment;


            case 1:
                CommentsFragment CommentsFragment = new CommentsFragment();
                return CommentsFragment;



            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int postition){

        switch (postition){
            case 0:
                return "Profile";
            case 1:
                return "Comments";


            default:
                return null;
        }

    }
}
