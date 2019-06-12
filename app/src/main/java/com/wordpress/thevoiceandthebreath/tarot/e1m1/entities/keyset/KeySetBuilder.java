package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KeySetBuilder {

    private Set<CardKey> keys;

    public KeySetBuilder() {
        keys = new TreeSet<>();
    }

    public KeySetBuilder(KeySet keySet) {
        keys = new TreeSet<>(keySet.getKeys());
    }

    public KeySet build() {
        List<CardKey> keyList = new ArrayList<>(keys);
        return new KeySet(keyList);
    }

    public KeySetBuilder addArcana(Arcana arcana){
        if(arcana == Arcana.MAJOR)
            for(Number number: Number.values())
                keys.add(new MajorCardKey(number));
        else
            for(Suit suit: Suit.values())
                addSuit(suit);
        return this;
    }

    public KeySetBuilder addSuit(Suit suit) {
        for(Rank rank: Rank.values())
            keys.add(new MinorCardKey(suit, rank));
        return this;
    }

    public KeySetBuilder addRank(Rank rank) {
        for(Suit suit: Suit.values())
            keys.add(new MinorCardKey(suit, rank));
        return this;
    }

    public KeySetBuilder addCourt() {
        for(int i = Rank.PAGE.ordinal(); i <= Rank.KING.ordinal(); i++)
            addRank(Rank.values()[i]);
        return this;
    }

    public KeySetBuilder addCard(MajorCardKey cardKey) {
        keys.add(cardKey);
        return this;
    }

    public KeySetBuilder addCard(MinorCardKey cardKey) {
        keys.add(cardKey);
        return this;
    }

    public KeySetBuilder removeArcana(Arcana arcana) {
        keys.removeAll(new KeySetBuilder().addArcana(arcana).keys);
        return this;
    }

    public KeySetBuilder removeSuit(Suit suit) {
        keys.removeAll(new KeySetBuilder().addSuit(suit).keys);
        return this;
    }

    public KeySetBuilder removeRank(Rank rank) {
        keys.removeAll(new KeySetBuilder().addRank(rank).keys);
        return this;
    }

    public KeySetBuilder removeCourt() {
        keys.removeAll(new KeySetBuilder().addCourt().keys);
        return this;
    }

    public KeySetBuilder removeCard(MajorCardKey cardKey) {
        keys.remove(cardKey);
        return this;
    }

    public KeySetBuilder removeCard(MinorCardKey cardKey) {
        keys.remove(cardKey);
        return this;
    }
}
