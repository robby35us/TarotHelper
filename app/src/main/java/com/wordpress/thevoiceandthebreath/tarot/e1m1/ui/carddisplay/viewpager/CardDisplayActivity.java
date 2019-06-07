package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize.DataInitializer;

public class CardDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_card_display);

        CardDisplayPagerAdapter sectionsPagerAdapter = new CardDisplayPagerAdapter( getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.cardDisplayViewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addCards();
    }

    private void addCards() {
        DataInitializer.getFactory(this).populateDatabase();
    }
}