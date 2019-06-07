package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.util;

import android.content.Context;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.view.ViewGroup;

public class SceneManager {
    public static final int SCENE_A = 1;
    public static final int SCENE_B = 2;

    private Scene sceneA;
    private Scene sceneB;
    private Scene currentScene;
    private Transition transition;

    public SceneManager(ViewGroup sceneRoot, int sceneALayout, int sceneBLayout, Context context) {
        sceneA = Scene.getSceneForLayout(sceneRoot, sceneALayout, context);
        sceneB = Scene.getSceneForLayout(sceneRoot, sceneBLayout, context);
    }

    public void setTransition(Transition transition, Transition.TransitionListener listener) {
        this.transition = transition;
        this.transition.addListener(listener);
    }

    public void enterScene(int sceneId) {
        currentScene = sceneId == SCENE_A ? sceneA : sceneB;
        currentScene.enter();
    }

    public void switchScenes() {
        Scene nextScene = currentScene == sceneA ? sceneB : sceneA;
        TransitionManager.go(nextScene, transition);
        currentScene = nextScene;
    }
}