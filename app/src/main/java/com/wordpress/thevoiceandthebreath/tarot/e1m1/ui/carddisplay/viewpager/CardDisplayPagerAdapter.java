package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page.CardDisplayFragment;

public class CardDisplayPagerAdapter extends FragmentStatePagerAdapter {

    private KeySet keyset;

    CardDisplayPagerAdapter(KeySet keySet, FragmentManager fm) {
        super(fm);
        this.keyset = keySet;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return CardDisplayFragment.newInstance(keyset.getKey(position));
    }

    @Override
    public int getCount() {
        return keyset.getSize();
    }
}