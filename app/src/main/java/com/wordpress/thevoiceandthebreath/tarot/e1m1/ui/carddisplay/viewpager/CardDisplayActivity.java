package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize.DataInitializer;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySet;

public class CardDisplayActivity extends AppCompatActivity {

    private static final String SERIALIZED_KEYSET_EXTRA =
            "com.wordpress.thevoiceandthebreath.tarot.elm1.ui.carddisplay.viewpager.serialized_keyset_extra";

    public static Intent getIntent(KeySet keySet, Context context)  {
        Intent intent = new Intent(context, CardDisplayActivity.class);
        intent.putExtra(SERIALIZED_KEYSET_EXTRA, keySet.serialize());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_card_display);



    }

    @Override
    protected void onResume() {
        super.onResume();
        //addCards();
        KeySet keySet = KeySet.deserialize(getIntent().getIntArrayExtra(SERIALIZED_KEYSET_EXTRA));
        CardDisplayPagerAdapter sectionsPagerAdapter
                = new CardDisplayPagerAdapter(keySet ,getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.cardDisplayViewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    private void addCards() {
        DataInitializer.getFactory(this).populateDatabase();
    }
}