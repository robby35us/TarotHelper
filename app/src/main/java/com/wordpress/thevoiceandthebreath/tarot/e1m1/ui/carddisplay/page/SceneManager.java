package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.content.Context;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.view.ViewGroup;

class SceneManager {
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
        currentScene = sceneId == SCENE_A ? sceneA : sceneB;
        currentScene.enter();
    }

    void switchScenes() {
        Scene nextScene = currentScene == sceneA ? sceneB : sceneA;
        TransitionManager.go(nextScene, transition);
        currentScene = nextScene;
    }
}