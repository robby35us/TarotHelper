package com.wordpress.thevoiceandthebreath.tarot.e1m1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize.DataInitializer;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.main.CardPagerAdapter;

public class DisplayCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_card);

        CardPagerAdapter sectionsPagerAdapter = new CardPagerAdapter( getSupportFragmentManager());

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