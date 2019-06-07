package com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui;

import android.content.res.AssetManager;

public interface LoadImageInputPort {

    void execute(Params params, LoadImageOutputPort outputPort);

    class Params {
        private AssetManager assets;
        private String filename;

        public Params(AssetManager assets, String filename) {
            this.assets = assets;
            this.filename = filename;
        }

        public AssetManager getAssets() {
            return assets;
        }

        public String getFilename() {
            return filename;
        }
    }
}