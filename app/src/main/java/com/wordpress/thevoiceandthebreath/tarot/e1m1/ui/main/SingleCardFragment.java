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
    private SceneBindingManager sceneBindingManager;

    private MutableLiveData<CardWithMeanings> mCard;
    private int cardId;

    private SceneManager sceneManager;

    private float mImageRotation;
    private boolean mRotateImageOnSwitchCheckChanged;

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
        sceneBindingManager = new SceneBindingManager(mBinding);

        mCard = pageViewModel.getCard( getActivity(), cardId);
        mCard.observe(activity, new Observer<CardWithMeanings>() {
            @Override
            public void onChanged(@Nullable CardWithMeanings cardWithMeanings) {
                if(cardWithMeanings == null)
                    return;
                mCard.removeObserver(this);
                mBinding.setCard(cardWithMeanings.getCard());
                bindAssetImage(mBinding.singleCardImage, mBinding.getCard().getFileName());
                sceneBindingManager.initializeUprightBinding(cardWithMeanings.getUpright());
                sceneBindingManager.initializeReversedBinding(cardWithMeanings.getReversed());
            }
        });

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



        sceneManager = new SceneManager(mBinding.meaningsRoot, R.layout.layout_single_card_meaning,
                                                     R.layout.layout_single_card_reversed_meanings, activity);

        sceneManager.setTransition(new Slide(), disableEnableClicksListener);
        if (mImageRotation == REVERSED) {
            sceneManager.enterScene(SceneManager.SCENE_B);
            sceneBindingManager.bindReversed();
        } else {
            sceneManager.enterScene(SceneManager.SCENE_A);
            sceneBindingManager.bindUpright();
        }

        return mBinding.getRoot();
    }

    Transition.TransitionListener disableEnableClicksListener = new Transition.TransitionListener() {
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
        public void onTransitionCancel(@NonNull Transition transition) { }

        @Override
        public void onTransitionPause(@NonNull Transition transition) { }

        @Override
        public void onTransitionResume(@NonNull Transition transition) { }
    };

    private class SceneBindingManager {
        private FragmentCardDisplayBinding parentBinding;
        private LayoutSingleCardMeaningBinding uprightBinding;
        private LayoutSingleCardReversedMeaningsBinding reversedBinding;
        private Meaning uprightMeaning;
        private Meaning reversedMeaning;

        SceneBindingManager(FragmentCardDisplayBinding parentBinding) {
            this.parentBinding = parentBinding;
            this.uprightBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_single_card_meaning,
                                                          parentBinding.meaningsRoot, false);
            this.reversedBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_single_card_reversed_meanings,
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

    private class SceneManager {
        static final int SCENE_A = 1;
        static final int SCENE_B = 2;

        private Scene sceneA;
        private Scene sceneB;
        private Scene currentScene;
        private Transition transition;

         SceneManager(ViewGroup sceneRoot, int sceneALayout, int sceneBLayout, Context context) {
            sceneA = Scene.getSceneForLayout(sceneRoot, sceneALayout, context);
            sceneB = Scene.getSceneForLayout(sceneRoot, sceneBLayout, context);
        }

        void setTransition(Transition transition, Transition.TransitionListener listener) {
            this.transition = transition;
            this.transition.addListener(listener);
        }

        void enterScene(int sceneId) {
            if(sceneId == SCENE_A) {
                sceneA.enter();
                currentScene = sceneA;
            } else if(sceneId == SCENE_B) {
                sceneB.enter();
                currentScene = sceneB;
            }
        }

        void switchScenes() {
            Scene nextScene = currentScene == sceneA ? sceneB : sceneA;
            TransitionManager.go(nextScene, transition);
            currentScene = nextScene;
        }
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
            sceneManager.switchScenes();
            sceneBindingManager.bindUpright();
            mBinding.reversedSwitch.setChecked(false);
        } else {
            sceneManager.switchScenes();
            sceneBindingManager.bindReversed();
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
