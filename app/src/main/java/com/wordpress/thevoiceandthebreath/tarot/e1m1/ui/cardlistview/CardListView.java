package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySetBuilder;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.viewpager.CardDisplayActivity;

import java.util.ArrayList;
import java.util.List;

import static com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview.CardGroupingExpandableListAdapter.ALL_CHILD_INDEX;
import static com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview.CardGroupingExpandableListAdapter.COURT_GROUP_INDEX;
import static com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview.CardGroupingExpandableListAdapter.MAJOR_GROUP_INDEX;
import static com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview.CardGroupingExpandableListAdapter.PIPS_GROUP_INDEX;
import static com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview.CardGroupingExpandableListAdapter.SUITS_GROUP_INDEX;

public class CardListView extends AppCompatActivity {
    private static final int FIRST_CHILD_OPTION_INDEX = 1;
    private static final int COURT_OFFSET_INDEX = 9;

    private ExpandableListView listView;
    private CardGroupingExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection_page);
        setUpListView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabClickListener);
    }

    private void setUpListView() {
        adapter = new CardGroupingExpandableListAdapter(this);

        listView = findViewById(R.id.cardGroupOptions);
        listView.setAdapter(adapter);
        listView.setOnGroupExpandListener(expandListener);
        listView.setOnGroupClickListener(groupClickListener);
        listView.setOnChildClickListener(childClickListener);
    }

    private ExpandableListView.OnGroupExpandListener expandListener
            = new ExpandableListView.OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {
            int count = listView.getCount();
            for(int i = 0; i < count; i++)
                if(i != groupPosition)
                    listView.collapseGroup(i);
        }
    };

    private ExpandableListView.OnGroupClickListener groupClickListener
            = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            if(parent.isGroupExpanded(groupPosition))
                parent.collapseGroup(groupPosition);
            else
                parent.expandGroup(groupPosition);
            return true;
        }
    };

    private ExpandableListView.OnChildClickListener childClickListener
            = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            CheckBox selectedCheckBox = v.findViewById(R.id.toggleSelection);
            if (childPosition == ALL_CHILD_INDEX)
                switchAllInGroup(groupPosition, selectedCheckBox);
            else
                switchInGroup(groupPosition, selectedCheckBox);
            return true;
        }
    };

    private void switchAllInGroup(int groupPosition, CheckBox selectedCheckBox) {
        CheckBox parentCheckBox
                = adapter.getGroupView(groupPosition).findViewById(R.id.toggleSelection);
        List<View> childViews = adapter.getChildViews(groupPosition);
        setAll(!selectedCheckBox.isChecked() ,parentCheckBox, childViews);
    }

    private void setAll(boolean setting, CheckBox parentCheckBox, List<View> childViews){
        for (View childView : childViews)
            ((CheckBox) childView.findViewById(R.id.toggleSelection)).setChecked(setting);
        parentCheckBox.setChecked(setting);
    }

    private void switchInGroup(int groupPosition, CheckBox selectedCheckBox) {
        selectedCheckBox.setChecked(!selectedCheckBox.isChecked());
        setAllCheckBox(groupPosition);
        setParentCheckBox(groupPosition);
    }


    private void setAllCheckBox(int groupPosition ) {
        CheckBox allCheckBox = adapter.getChildViews(groupPosition).get(0)
                .findViewById(R.id.toggleSelection);
        List<CheckBox> otherCheckBoxes = getOtherCheckBoxes(groupPosition);

        for (CheckBox checkBox : otherCheckBoxes)
            if (!checkBox.isChecked()) {
                allCheckBox.setChecked(false);
                return;
            }
        allCheckBox.setChecked(true);
    }

    private void setParentCheckBox(int groupPosition) {
        CheckBox parentCheckBox = adapter.getGroupView(groupPosition).findViewById(R.id.toggleSelection);
        List<CheckBox> otherCheckBoxes = getOtherCheckBoxes(groupPosition);

        for (CheckBox checkBox : otherCheckBoxes)
            if (checkBox.isChecked()) {
                parentCheckBox.setChecked(true);
                return;
            }
        parentCheckBox.setChecked(false);
    }

    private List<CheckBox> getOtherCheckBoxes(int groupPosition) {
        List<View> childViews = adapter.getChildViews(groupPosition);
        List<CheckBox> otherCheckBoxes = new ArrayList<>();
        for(int i = FIRST_CHILD_OPTION_INDEX; i < childViews.size(); i++){
            otherCheckBoxes.add((CheckBox) childViews.get(i).findViewById(R.id.toggleSelection));
        }
        return otherCheckBoxes;
    }

    private View.OnClickListener fabClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = CardDisplayActivity.getIntent(makeKeySet(), CardListView.this);
            startActivity(intent);
        }
    };

    private KeySet makeKeySet() {
        return removeRanks(removeCourt(removeSuits(
               addMinorArcana(addMajorArcana(new KeySetBuilder())))))
               .build();
    }

    private KeySetBuilder addMajorArcana(KeySetBuilder builder) {
        CheckBox parentCheckBox
                = adapter.getGroupView(MAJOR_GROUP_INDEX).findViewById(R.id.toggleSelection);
        if(parentCheckBox.isChecked())
            builder.addArcana(Arcana.MAJOR);
        return builder;
    }

    private KeySetBuilder addMinorArcana(KeySetBuilder builder) {
        CheckBox parentCheckBox
                = adapter.getGroupView(SUITS_GROUP_INDEX).findViewById(R.id.toggleSelection);
        if(parentCheckBox.isChecked())
            builder.addArcana(Arcana.MINOR);
        return builder;
    }

    private KeySetBuilder removeSuits(KeySetBuilder builder) {
        List<View> childViews = adapter.getChildViews(SUITS_GROUP_INDEX);
        for (int i = FIRST_CHILD_OPTION_INDEX; i < childViews.size(); i++) {
            CheckBox childCheckBox = childViews.get(i).findViewById(R.id.toggleSelection);
            if (!childCheckBox.isChecked())
                builder.removeSuit(Suit.values()[i - FIRST_CHILD_OPTION_INDEX]);
        }
        return builder;
    }

    private KeySetBuilder removeCourt(KeySetBuilder builder) {
        CheckBox parentCheckBox
                = adapter.getGroupView(COURT_GROUP_INDEX).findViewById(R.id.toggleSelection);
        if(!parentCheckBox.isChecked())
            builder.removeCourt();
        else
            removeUnselectedCourt(builder);
        return builder;
    }

    private void removeUnselectedCourt(KeySetBuilder builder) {
        List<View> childViews = adapter.getChildViews(COURT_GROUP_INDEX);
        for(int i = FIRST_CHILD_OPTION_INDEX; i < childViews.size(); i++ ) {
            CheckBox childCheckBox = childViews.get(i).findViewById(R.id.toggleSelection);
            if (!childCheckBox.isChecked())
                builder.removeRank(Rank.values()[i + COURT_OFFSET_INDEX]);
        }
    }

    private KeySetBuilder removeRanks(KeySetBuilder builder) {
        CheckBox parentCheckBox
                = adapter.getGroupView(PIPS_GROUP_INDEX).findViewById(R.id.toggleSelection);
        if(!parentCheckBox.isChecked())
            removeAllPips(builder);
        else
           removeUnselectedPips(builder);
        return builder;
    }

    private void removeAllPips(KeySetBuilder builder){
        for(int i = 0; i < Rank.COURT_OFFSET; i++)
            builder.removeRank(Rank.values()[i]);
    }

    private void removeUnselectedPips(KeySetBuilder builder) {
        List<View> childViews = adapter.getChildViews(PIPS_GROUP_INDEX);
        for(int i = FIRST_CHILD_OPTION_INDEX; i < childViews.size(); i++ ) {
            CheckBox childCheckBox = childViews.get(i).findViewById(R.id.toggleSelection);
            if (!childCheckBox.isChecked())
                builder.removeRank(Rank.values()[i - FIRST_CHILD_OPTION_INDEX]);
        }
    }
}
