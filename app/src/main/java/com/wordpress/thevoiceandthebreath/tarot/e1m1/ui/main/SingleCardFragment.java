package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.transition.Scene;
import android.support.transition.Slide;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.FragmentCardDisplayBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutSingleCardMeaningBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.LayoutSingleCardReversedMeaningsBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Card;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;

import java.io.IOException;
import java.io.InputStream;

@BindingMethods({
        @BindingMethod(type = android.widget.ImageView.class,
                attribute = "srcCompat",
                method = "setImageDrawable") })
public class SingleCardFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_CARD_ID = "card_id";
    private static final String ARG_IMAGE_ROTATION = "image_rotation";

    private static final float REVERSED = 180f;
    private static final float UPRIGHT = 0f;


    private static final int CARD_ID_DEFAULT = 0;
    private FragmentCardDisplayBinding mBinding;
    private LayoutSingleCardMeaningBinding mUprightBinding;
    private LayoutSingleCardReversedMeaningsBinding mReversedBinding;

    private MutableLiveData<CardWithMeanings> mCard;
    private Card card;
    private Meaning uprightMeaning;
    private Meaning reversedMeaning;
    private int cardId;

    private float mImageRotation;
    private boolean mRotateImageOnSwitchCheckChanged;

    private Scene mUprightMeanings;
    private Scene mReversedMeanings;
    private Transition mTransition;
    private AppCompatActivity activity;
    private PageViewModel pageViewModel;


    public static SingleCardFragment newInstance(int cardPosition,
                                                 boolean reversed) {

        SingleCardFragment fragment = new SingleCardFragment();
        fragment.mCard = new MutableLiveData<>();
        fragment.cardId = cardPosition;
        fragment.mImageRotation = reversed ? REVERSED : UPRIGHT;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        if(savedInstanceState != null) {
            cardId = savedInstanceState.getInt(ARG_CARD_ID, CARD_ID_DEFAULT);
            mImageRotation = savedInstanceState.getFloat(ARG_IMAGE_ROTATION, UPRIGHT);
        } else {
            mImageRotation = UPRIGHT;
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AppCompatActivity) {
            activity = (AppCompatActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_display,
                container, false);
        mCard = pageViewModel.getCard( getActivity(), cardId);
        mCard.observe(activity, new Observer<CardWithMeanings>() {
            @Override
            public void onChanged(@Nullable CardWithMeanings cardWithMeanings) {
                if(cardWithMeanings == null)
                    return;
                mCard.removeObserver(this);
                card = cardWithMeanings.getCard();
                uprightMeaning = cardWithMeanings.getUpright();
                reversedMeaning = cardWithMeanings.getReversed();

                mBinding.setCard(card);
                bindAssetImage(mBinding.singleCardImage, card.getFileName());
                if (mUprightBinding != null)
                    mUprightBinding.setMeaning(uprightMeaning);
                if (mReversedBinding != null)
                    mReversedBinding.setMeaning(reversedMeaning);
            }
        });
        mBinding.setCard(card);
        mBinding.setDrawable(mBinding.singleCardImage.getDrawable());

        mBinding.singleCardImage.setOnClickListener(this);
        mBinding.singleCardImage.setRotation(mImageRotation);
        mRotateImageOnSwitchCheckChanged = true;



        mBinding.reversedSwitch.setChecked(mImageRotation == REVERSED);
        mBinding.reversedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mRotateImageOnSwitchCheckChanged)
                    mBinding.singleCardImage.performClick();
            }
        });


        mUprightBinding
                = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_single_card_meaning, mBinding.meaningsRoot, false);

        mReversedBinding
                = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_single_card_reversed_meanings, mBinding.meaningsRoot, false);



        // Create the scene root for the scenes in this app
        ViewGroup sceneRoot = mBinding.meaningsRoot;
        if(activity != null) {
            // Create the scenes
            mUprightMeanings
                    = Scene.getSceneForLayout(sceneRoot, R.layout.layout_single_card_meaning, activity);
            mReversedMeanings
                    = Scene.getSceneForLayout(sceneRoot, R.layout.layout_single_card_reversed_meanings, activity);

            mTransition = new Slide();
            mTransition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(@NonNull Transition transition) {
                    mBinding.singleCardImage.setClickable(false);
                    mBinding.reversedSwitch.setClickable(false);
                }

                @Override
                public void onTransitionEnd(@NonNull Transition transition) {
                    mBinding.singleCardImage.setClickable(true);
                    mBinding.reversedSwitch.setClickable(true);
                }

                @Override
                public void onTransitionCancel(@NonNull Transition transition) {

                }

                @Override
                public void onTransitionPause(@NonNull Transition transition) {

                }

                @Override
                public void onTransitionResume(@NonNull Transition transition) {

                }
            });

            if (mImageRotation == REVERSED) {
                mReversedMeanings.enter();
                mReversedBinding = LayoutSingleCardReversedMeaningsBinding.bind(mBinding.meaningsRoot.getChildAt(0));
                mReversedBinding.setMeaning(reversedMeaning);
            } else {
                mUprightMeanings.enter();
                mUprightBinding = LayoutSingleCardMeaningBinding.bind(mBinding.meaningsRoot.getChildAt(0));
                mUprightBinding.setMeaning(uprightMeaning);
            }
        }
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_CARD_ID, cardId);
        outState.putFloat(ARG_IMAGE_ROTATION, mImageRotation);
    }

    @Override
    public void onClick(View v) {
        if(mImageRotation == UPRIGHT)
            mImageRotation = REVERSED;
        else
            mImageRotation = UPRIGHT;

        v.animate().rotation(mImageRotation).setDuration(500).start();
        mRotateImageOnSwitchCheckChanged = false;
        if(mImageRotation == UPRIGHT) {
            TransitionManager.go(mUprightMeanings, mTransition);
            mUprightBinding = LayoutSingleCardMeaningBinding.bind(mBinding.meaningsRoot.getChildAt(0));
            mUprightBinding.setMeaning(uprightMeaning);

            mBinding.reversedSwitch.setChecked(false);
        } else {
            TransitionManager.go(mReversedMeanings, mTransition);
            mReversedBinding = LayoutSingleCardReversedMeaningsBinding.bind(mBinding.meaningsRoot.getChildAt(0));
            mReversedBinding.setMeaning(reversedMeaning);
            mBinding.reversedSwitch.setChecked(true);
        }
        mRotateImageOnSwitchCheckChanged = true;
    }

    private static void bindAssetImage(ImageView imageView, String filename) {
        try {
            // get input stream
            InputStream ims = imageView.getContext().getAssets().open(filename);
            // load image as Drawable
            imageView.setImageDrawable(Drawable.createFromStream(ims, null));
            ims.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
