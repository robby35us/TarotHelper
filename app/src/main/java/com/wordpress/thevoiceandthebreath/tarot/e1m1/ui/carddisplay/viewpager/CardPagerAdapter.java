package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page.SingleCardFragment;

public class CardPagerAdapter extends FragmentStatePagerAdapter {

    CardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return SingleCardFragment.newInstance(position, false);
    }

    @Override
    public int getCount() {
        return Arcana.MAJOR_ARCANA_SIZE + Arcana.MINOR_ARCANA_SIZE;
    }
}