package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.transition.Transition;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.CompoundButton;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.FragmentCardDisplayBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Orientation;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.util.SceneBindingManager;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.util.SceneManager;

public class CardDisplayFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_SERIALIZED_CARD_KEY = "serialized_card_key";

    private static final float REVERSED_ROTATION = 180f;

    private FragmentCardDisplayBinding mBinding;

    private SceneManager sceneManager;
    private SceneBindingManager sceneBindingManager;

    private CardKey key;

    private boolean mRotateImageOnSwitchCheckChanged;

    private AppCompatActivity activity;
    private CardDisplayViewModel viewModel;


    public static CardDisplayFragment newInstance(CardKey key) {
        CardDisplayFragment fragment = new CardDisplayFragment();
        fragment.key = key;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CardDisplayViewModel.class);
        if(savedInstanceState != null)
            restoreFragmentFromSavedInstanceState(savedInstanceState);
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
        initializePage();
        return mBinding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(ARG_SERIALIZED_CARD_KEY, KeySet.serializeSingle(key));
    }

    @Override
    public void onClick(View v) {
        v.setClickable(false);
        rotateImageAndTransitionScene();
        toggleReversedSwitch();
    }


    private void restoreFragmentFromSavedInstanceState(Bundle savedInstanceState) {
         key = KeySet.deserializeSingle(savedInstanceState.getIntArray(ARG_SERIALIZED_CARD_KEY));
    }

    private void initializePage() {
        setupScenes();
        enterScene();
        getLiveDataCardFromDatabase();
        setupViews();
    }

    private void setupScenes() {
        sceneBindingManager = new SceneBindingManager(mBinding, getLayoutInflater());
        sceneManager = new SceneManager(mBinding.meaningsRoot, R.layout.layout_card_meaning_upright,
                R.layout.layout_card_meaning_reversed, activity);
        sceneManager.setTransition(new Slide(), disableEnableClicksListener);
    }

    private void enterScene() {
        if (key.getOrientation() == Orientation.Reversed) {
            sceneManager.enterScene(SceneManager.SCENE_B);
            sceneBindingManager.bindReversed();
        } else {
            sceneManager.enterScene(SceneManager.SCENE_A);
            sceneBindingManager.bindUpright();
        }
    }

    private void getLiveDataCardFromDatabase() {
        LiveData<CardModel> mCard = viewModel.getCard( activity, key);
        mCard.observe(activity, cardWithMeaningsObserver);
    }

    private void setupViews() {
        setUpImageView();
        setUpReversedSwitch();
    }

    private void setUpImageView() {
        mBinding.singleCardImage.setOnClickListener(this);
        mBinding.singleCardImage.setRotation(key.getOrientation().ordinal() * REVERSED_ROTATION);
    }

    private void setUpReversedSwitch() {
        mRotateImageOnSwitchCheckChanged = true;
        mBinding.reversedSwitch.setChecked(key.getOrientation() == Orientation.Reversed);
        mBinding.reversedSwitch.setOnCheckedChangeListener(reverseSwitchListener);
    }

    private void rotateImageAndTransitionScene() {
        toggleRotationConstant();
        startRotationAnimation(mBinding.singleCardImage);
        handleSceneTransition();
    }

    private void toggleRotationConstant() {
       key.toggleOrientation();
    }

    private void startRotationAnimation(View view) {
        view.animate().rotation(key.getOrientation().ordinal() * REVERSED_ROTATION).setDuration(500).start();
    }

    private void handleSceneTransition() {
        sceneManager.switchScenes();
        if(key.getOrientation() == Orientation.Upright)
            sceneBindingManager.bindUpright();
        else
            sceneBindingManager.bindReversed();
    }

    private void toggleReversedSwitch() {
        mRotateImageOnSwitchCheckChanged = false;
        mBinding.reversedSwitch.setChecked(!mBinding.reversedSwitch.isChecked());
        mRotateImageOnSwitchCheckChanged = true;
    }

    private Observer<CardModel> cardWithMeaningsObserver = new Observer<CardModel>() {

        @Override
        public void onChanged(@Nullable CardModel card) {
            if(card == null)
                return;
            setViewsFromCard(card);
        }
    };

    private CompoundButton.OnCheckedChangeListener reverseSwitchListener
                            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mRotateImageOnSwitchCheckChanged) {
                rotateImageAndTransitionScene();
            }
        }
    };


    private Transition.TransitionListener disableEnableClicksListener = new Transition.TransitionListener() {
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

    private void setViewsFromCard(CardModel card) {
        mBinding.setCard(card);
        requestDrawableForImage();
        initializeSceneBindings(card);
    }

    private void requestDrawableForImage(){
        LiveData<Drawable> drawableLiveData = viewModel.loadImage(activity.getAssets(), mBinding.getCard().getImageFilename());
        drawableLiveData.observe(activity, drawableObserver);
    }

    private void initializeSceneBindings(CardModel card) {
        sceneBindingManager.initializeUprightBinding(card.getUprightMeanings());
        sceneBindingManager.initializeReversedBinding(card.getReversedMeanings());
    }

    private Observer<Drawable> drawableObserver = new Observer<Drawable>() {
        @Override
        public void onChanged(@Nullable Drawable drawable) {
            mBinding.singleCardImage.setImageDrawable(drawable);
        }
    };
}
