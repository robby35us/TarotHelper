package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.MutableLiveData;
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
import android.widget.ImageView;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.databinding.FragmentCardDisplayBinding;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.CardWithMeanings;

import java.io.IOException;
import java.io.InputStream;

public class CardDisplayFragment extends Fragment implements View.OnClickListener{
    // Argument Constants for re-instantiating the fragment after swiping away and then back
    private static final String ARG_CARD_ID = "card_id";
    private static final String ARG_IMAGE_ROTATION = "image_rotation";
    // other constants
    private static final int CARD_ID_DEFAULT = 0;
    private static final float REVERSED = 180f;
    private static final float UPRIGHT = 0f;

    // holds all the views in the fragment
    private FragmentCardDisplayBinding mBinding;

    // for managing animation and content of swapping out upright/reversed information
    private SceneManager sceneManager;
    private SceneBindingManager sceneBindingManager;

    private MutableLiveData<CardWithMeanings> mCard;
    private int cardId;

    // for tracking image rotation and synchronizing the reversed switch
    private float mImageRotation;
    private boolean mRotateImageOnSwitchCheckChanged;

    //the parent activity
    private AppCompatActivity activity;
    // this fragment's view model
    private CardDisplayViewModel pageViewModel;


    public static CardDisplayFragment newInstance(int cardPosition,
                                                  boolean reversed) {
        CardDisplayFragment fragment = new CardDisplayFragment();
        fragment.mCard = new MutableLiveData<>();
        fragment.cardId = cardPosition;
        fragment.mImageRotation = reversed ? REVERSED : UPRIGHT;
        return fragment;
    }

    /*
        Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(CardDisplayViewModel.class);
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
        outState.putInt(ARG_CARD_ID, cardId);
        outState.putFloat(ARG_IMAGE_ROTATION, mImageRotation);
    }

    /*
     * implement View.OnClickListener interface
     */
    @Override
    public void onClick(View v) {
        v.setClickable(false);
        rotateImageAndTransitionScene();
        toggleReversedSwitch();
    }


    private void restoreFragmentFromSavedInstanceState(Bundle savedInstanceState) {
        cardId = savedInstanceState.getInt(ARG_CARD_ID, CARD_ID_DEFAULT);
        mImageRotation = savedInstanceState.getFloat(ARG_IMAGE_ROTATION, UPRIGHT);
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
        if (mImageRotation == REVERSED) {
            sceneManager.enterScene(SceneManager.SCENE_B);
            sceneBindingManager.bindReversed();
        } else {
            sceneManager.enterScene(SceneManager.SCENE_A);
            sceneBindingManager.bindUpright();
        }
    }

    private void getLiveDataCardFromDatabase() {
        mCard = pageViewModel.getCard( activity, cardId);
        mCard.observe(activity, cardWithMeaningsObserver);
    }

    private void setupViews() {
        setUpImageView();
        setUpReversedSwitch();
    }

    private void setUpImageView() {
        mBinding.singleCardImage.setOnClickListener(this);
        mBinding.singleCardImage.setRotation(mImageRotation);
    }

    private void setUpReversedSwitch() {
        mRotateImageOnSwitchCheckChanged = true;
        mBinding.reversedSwitch.setChecked(mImageRotation == REVERSED);
        mBinding.reversedSwitch.setOnCheckedChangeListener(reverseSwitchListener);
    }

    private void rotateImageAndTransitionScene() {
        toggleRotationConstant();
        startRotationAnimation(mBinding.singleCardImage);
        handleSceneTransition();
    }

    private void toggleRotationConstant() {
        mImageRotation = mImageRotation == UPRIGHT? REVERSED : UPRIGHT;
    }

    private void startRotationAnimation(View view) {
        view.animate().rotation(mImageRotation).setDuration(500).start();
    }

    private void handleSceneTransition() {
        sceneManager.switchScenes();
        if(mImageRotation == UPRIGHT)
            sceneBindingManager.bindUpright();
        else
            sceneBindingManager.bindReversed();
    }

    private void toggleReversedSwitch() {
        mRotateImageOnSwitchCheckChanged = false;
        mBinding.reversedSwitch.setChecked(!mBinding.reversedSwitch.isChecked());
        mRotateImageOnSwitchCheckChanged = true;
    }

    /*
        Observers and listeners
     */
    private Observer<CardWithMeanings> cardWithMeaningsObserver = new Observer<CardWithMeanings>() {
        @Override
        public void onChanged(@Nullable CardWithMeanings cardWithMeanings) {
            if(cardWithMeanings == null)
                return;
            mCard.removeObserver(this);
            setViewsToCard(cardWithMeanings);
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

    // Private method for the Observer

    private void setViewsToCard(CardWithMeanings cardWithMeanings) {
        mBinding.setCard(cardWithMeanings.getCard());
        bindAssetImage(mBinding.singleCardImage, mBinding.getCard().getFileName());
        sceneBindingManager.initializeUprightBinding(cardWithMeanings.getUpright());
        sceneBindingManager.initializeReversedBinding(cardWithMeanings.getReversed());
    }

    /*
     * Used to set the drawable used for an image view
     */
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
