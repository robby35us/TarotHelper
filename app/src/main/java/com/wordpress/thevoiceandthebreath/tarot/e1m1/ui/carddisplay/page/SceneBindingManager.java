package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.FragmentCardDisplayBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutSingleCardMeaningBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutSingleCardReversedMeaningsBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;

class SceneBindingManager {
    private FragmentCardDisplayBinding parentBinding;
    private LayoutSingleCardMeaningBinding uprightBinding;
    private LayoutSingleCardReversedMeaningsBinding reversedBinding;
    private Meaning uprightMeaning;
    private Meaning reversedMeaning;

    SceneBindingManager(FragmentCardDisplayBinding parentBinding, LayoutInflater inflater) {
        this.parentBinding = parentBinding;
        this.uprightBinding = DataBindingUtil.inflate(inflater, R.layout.layout_single_card_meaning,
                parentBinding.meaningsRoot, false);
        this.reversedBinding = DataBindingUtil.inflate(inflater, R.layout.layout_single_card_reversed_meanings,
                parentBinding.meaningsRoot, false);
    }

    void bindUpright() {
        uprightBinding = LayoutSingleCardMeaningBinding.bind(parentBinding.meaningsRoot.getChildAt(0));
        uprightBinding.setMeaning(uprightMeaning);
    }

    void bindReversed() {
        reversedBinding = LayoutSingleCardReversedMeaningsBinding.bind(parentBinding.meaningsRoot.getChildAt(0));
        reversedBinding.setMeaning(reversedMeaning);
    }

    void initializeUprightBinding(Meaning uprightMeaning) {
        uprightBinding.setMeaning(uprightMeaning);
        this.uprightMeaning = uprightMeaning;
    }

    void initializeReversedBinding(Meaning reversedMeaning) {
        reversedBinding.setMeaning(reversedMeaning);
        this.reversedMeaning = reversedMeaning;
    }
}
