package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.GetCardCount.GetCardCountInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.GetCardCount.GetCardCountOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.GetCardCount.GetCardCountUseCase;


public class CardPagerAdapter extends FragmentStatePagerAdapter implements GetCardCountOutputPort {

    private final Context mContext;
    private MutableLiveData<Integer> cardCount;

    public CardPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        cardCount = new MutableLiveData<>();
        cardCount.setValue(0);
        new GetCardCountUseCase().execute(new GetCardCountInputPort.Params(mContext), this);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return SingleCardFragment.newInstance(position, false);
    }

    @Override
    public int getCount() {
        if(cardCount.getValue() != null)
            return cardCount.getValue();
        else
            return 0;
    }

    @Override
    public void updateCardCount(int cardCount) {
        this.cardCount.setValue(cardCount);
        this.notifyDataSetChanged();
    }
}