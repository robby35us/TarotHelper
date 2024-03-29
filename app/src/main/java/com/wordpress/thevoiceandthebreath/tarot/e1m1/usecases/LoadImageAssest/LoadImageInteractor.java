package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class LoadImageInteractor implements LoadImageInputPort {

    @Override
    public void execute(Params params, LoadImageOutputPort outputPort) {
        try {
            loadAndOutputDrawable(params, outputPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAndOutputDrawable(Params params, LoadImageOutputPort outputPort)
            throws IOException {
        InputStream ims = params.getAssets().open(params.getFilename());

        Drawable image = Drawable.createFromStream(ims, null);
        outputPort.acceptDrawable(image);

        ims.close();
    }
}
