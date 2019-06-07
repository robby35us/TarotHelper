package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.util;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.FragmentCardDisplayBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutCardMeaningReversedBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutCardMeaningUprightBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;

public class SceneBindingManager {
    private FragmentCardDisplayBinding parentBinding;
    private LayoutCardMeaningUprightBinding uprightBinding;
    private LayoutCardMeaningReversedBinding reversedBinding;
    private Meaning uprightMeaning;
    private Meaning reversedMeaning;

    public SceneBindingManager(FragmentCardDisplayBinding parentBinding, LayoutInflater inflater) {
        this.parentBinding = parentBinding;
        this.uprightBinding = DataBindingUtil.inflate(inflater, R.layout.layout_card_meaning_upright,
                parentBinding.meaningsRoot, false);
        this.reversedBinding = DataBindingUtil.inflate(inflater, R.layout.layout_card_meaning_reversed,
                parentBinding.meaningsRoot, false);
    }

    public void bindUpright() {
        uprightBinding = LayoutCardMeaningUprightBinding.bind(parentBinding.meaningsRoot.getChildAt(0));
        uprightBinding.setMeaning(uprightMeaning);
    }

    public void bindReversed() {
        reversedBinding = LayoutCardMeaningReversedBinding.bind(parentBinding.meaningsRoot.getChildAt(0));
        reversedBinding.setMeaning(reversedMeaning);
    }

    public void initializeUprightBinding(Meaning uprightMeaning) {
        uprightBinding.setMeaning(uprightMeaning);
        this.uprightMeaning = uprightMeaning;
    }

    public void initializeReversedBinding(Meaning reversedMeaning) {
        reversedBinding.setMeaning(reversedMeaning);
        this.reversedMeaning = reversedMeaning;
    }
}
