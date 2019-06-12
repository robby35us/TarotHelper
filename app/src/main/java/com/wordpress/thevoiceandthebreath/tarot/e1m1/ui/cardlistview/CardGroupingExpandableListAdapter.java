package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.cardlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardGroupingExpandableListAdapter extends BaseExpandableListAdapter {
    static final int MAJOR_GROUP_INDEX = 0;
    static final int SUITS_GROUP_INDEX = 1;
    static final int COURT_GROUP_INDEX = 2;
    static final int PIPS_GROUP_INDEX = 3;
    static final int ALL_CHILD_INDEX = 0;

    private List<String> parentOptions;
    private Map<String, View> parentViews;

    private Map<String, List<String>> childOptions;
    private Map<String, List<View>> childViews;

    CardGroupingExpandableListAdapter(Context context) {
        parentViews = new HashMap<>();
        parentOptions = new ArrayList<>();
        childViews = new HashMap<>();
        childOptions = new HashMap<>();
        new OptionsListCreator(context).createOptionsList();
    }

    @Override
    public int getGroupCount() {
        return parentOptions.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String parentString = parentOptions.get(groupPosition);
        List<String> childStrings =  childOptions.get(parentString);
        if(childStrings != null)
            return childStrings.size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentOptions.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String parentString = parentOptions.get(groupPosition);
        List<String> childStrings = childOptions.get(parentString);
        if(childStrings != null)
            return childStrings.get(childPosition);
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return parentViews.get(parentOptions.get(groupPosition));
    }

    public View getGroupView(int groupPositions) {
        return  parentViews.get(parentOptions.get(groupPositions));
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<View> groupChildViews = childViews.get(parentOptions.get(groupPosition));
        if(groupChildViews != null)
            return groupChildViews.get(childPosition);
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    List<View> getChildViews(int groupPosition) {
        return childViews.get(parentOptions.get(groupPosition));
    }

    private class OptionsListCreator {
        private static final String ALL = "All";
        private static final String MAJOR_ARCANA = "MajorArcana";
        private static final String MINOR_ARCANA_SUITS = "Minor Arcana - Suits";
        private static final String MINOR_ARCANA_COURT = "Minor Arcana - Court";
        private static final String MINOR_ARCANA_PIPS = "Minor Arcana - Pips";

        private LayoutInflater inflater;

        OptionsListCreator(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        void createOptionsList() {
            putMajorArcanaGroup();
            putSuitsGroup();
            putCourtGroup();
            putPipsGroup();
        }

        private void putMajorArcanaGroup() {
            List<String> options = new ArrayList<>();
            options.add(ALL);
            putGroup(MAJOR_ARCANA, options);
        }

        private void putSuitsGroup() {
            List<String> options = new ArrayList<>();
            options.add(ALL);
            for (Suit suit : Suit.values())
                options.add(suit.toString());
            putGroup(MINOR_ARCANA_SUITS, options);
        }

        private void putCourtGroup() {
            List<String> options = new ArrayList<>();
            options.add(ALL);
            for (int i = Rank.COURT_OFFSET; i < Rank.values().length; i++)
                options.add(Rank.values()[i].toString());
            putGroup(MINOR_ARCANA_COURT, options);
        }

        private void putPipsGroup() {
            List<String> options = new ArrayList<>(Rank.COURT_OFFSET + 1);
            options.add(ALL);
            for(int i =0; i < Rank.COURT_OFFSET; i++)
                options.add(Rank.values()[i].toString());
            putGroup(MINOR_ARCANA_PIPS, options);
        }

        private void putGroup(String parent, List<String> options) {
            intializeOptions(parent, options);
            inflateParent(parent);
            inflateChildren(parent, options);
        }

        private void intializeOptions(String parent, List<String> options) {
            parentOptions.add(parent);
            childOptions.put(parent, options);
        }

        private void inflateParent(String parent){
            View parentView = inflater.inflate(R.layout.layout_card_grouping, null);
            TextView tv = parentView.findViewById(R.id.optionText);
            tv.setText(parent);
            parentViews.put( parent, parentView);
        }

        private void inflateChildren(String parent, List<String> options) {
            List<View> views = new ArrayList<>();
            for(int i = 0; i < options.size(); i++) {
                views.add(inflateChild(options.get(i)));
            }
            childViews.put(parent,views);
        }

        private View inflateChild(String option) {
            View child = inflater.inflate(R.layout.layout_card_grouping_child, null);
            TextView groupLabelTV = child.findViewById(R.id.optionText);
            groupLabelTV.setText(option);
            return child;
        }
    }
}

