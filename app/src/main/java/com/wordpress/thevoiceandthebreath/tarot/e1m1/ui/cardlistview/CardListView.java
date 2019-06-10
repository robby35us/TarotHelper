package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager.CardDisplayActivity;

import java.util.ArrayList;
import java.util.List;

public class CardListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_view);

        List<CardKey> keyList = new ArrayList<>(Arcana.MAJOR_ARCANA_SIZE);
        for(Number number : Number.values()) {
            keyList.add(new MajorCardKey(number));
        }

        Intent intent = CardDisplayActivity.getIntent(new KeySet(keyList), this);
        startActivity(intent);
    }


}
