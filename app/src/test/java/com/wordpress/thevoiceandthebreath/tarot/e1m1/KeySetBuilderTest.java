package com.wordpress.thevoiceandthebreath.tarot.e1m1;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.KeySetBuilder;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MinorCardKey;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class KeySetBuilderTest {

    private KeySetBuilder builder;

    @Before
    public void construct_KeySetBuilder() {
        builder = new KeySetBuilder();
    }

    @Test
    public void build_EmptyKeySet() {
        assertThat(builder.build(), notNullValue());
    }

    @Test
    public void majorArcanaSize_22() {
        assertThat(builder.addArcana(Arcana.MAJOR).build().getSize(), equalTo(Arcana.MAJOR_ARCANA_SIZE) );
    }

    @Test
    public void majorArcanaBuildOutput_Sorted() {
        List<CardKey> keys = builder.addArcana(Arcana.MAJOR).build().getKeys();
        for(int i = 0 ; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MajorCardKey.class, equalTo(true));
            assertThat(((MajorCardKey) keys.get(i)).getNumber(), equalTo(Number.values()[i]));
        }
    }

    @Test
    public void minorArcanaSize_56() {
        assertThat(builder.addArcana(Arcana.MINOR).build().getSize(),equalTo(Arcana.MINOR_ARCANA_SIZE));
    }

    @Test
    public void minorArcanaBuildOutput_Sorted() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR).build().getKeys();
        for(int i = 0 ; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.values()[i/ Rank.NUM_RANKS]));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
    }

    @Test
    public void suitSize_NumRanks() {
        List<CardKey> keys = builder.addSuit(Suit.WANDS).build().getKeys();
        assertThat(keys.size(), equalTo(Rank.NUM_RANKS));
    }

    @Test
    public void buildSuit_Sorted() {
        List<CardKey> keys = builder.addSuit(Suit.PENTACLES).build().getKeys();
        for(int i = 0; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, equalTo(true));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i]));
        }
    }

    @Test
    public void majorArcana_SortedAfterKeysRemoved() {
        List<CardKey> keys = builder.addArcana(Arcana.MAJOR)
                                    .addArcana(Arcana.MINOR)
                                    .removeArcana(Arcana.MINOR)
                                    .build().getKeys();
        for(int i = 0 ; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MajorCardKey.class, equalTo(true));
            assertThat(((MajorCardKey) keys.get(i)).getNumber(), equalTo(Number.values()[i]));
        }
    }

    @Test
    public void minorArcana_SortedAfterKeysRemoved() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR)
                .addArcana(Arcana.MAJOR)
                .removeArcana(Arcana.MAJOR)
                .build().getKeys();
        for(int i = 0 ; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.values()[i/ Rank.NUM_RANKS]));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
    }
    @Test
    public void minorArcanaSuits_SortedAfterWandsRemoved() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR)
                .removeSuit(Suit.WANDS)
                .build().getKeys();
        for(int i = Rank.NUM_RANKS ; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i - Rank.NUM_RANKS)).getSuit(), equalTo(Suit.values()[i/ Rank.NUM_RANKS]));
            assertThat(((MinorCardKey) keys.get(i - Rank.NUM_RANKS)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
    }

    @Test
    public void minorArcanaSuits_SortedAfterCupsRemoved() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR)
                .removeSuit(Suit.CUPS)
                .build().getKeys();
        int i = 0;
        for(; i < Rank.NUM_RANKS; i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.values()[i/ Rank.NUM_RANKS]));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
        for(; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.values()[(i + Rank.NUM_RANKS)/ Rank.NUM_RANKS]));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[(i + Rank.NUM_RANKS)% Rank.NUM_RANKS]));
        }
    }

    @Test
    public void integrationTest() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR)
                                    .removeSuit(Suit.WANDS)
                                    .removeSuit(Suit.CUPS)
                                    .removeSuit(Suit.PENTACLES)
                                    .removeCourt()
                                    .build().getKeys();
        assertThat(keys.size(), equalTo(10));
        for(int i = 0; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.SWORDS));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
    }


    @Test
    public void wandsOnly() {
        List<CardKey> keys = builder.addArcana(Arcana.MINOR)
                .removeSuit(Suit.CUPS)
                .removeSuit(Suit.SWORDS)
                .removeSuit(Suit.PENTACLES)
                .build().getKeys();
        assertThat(keys.size(), equalTo(Rank.NUM_RANKS));
        for(int i = 0; i < keys.size(); i++) {
            assertThat(keys.get(i).getClass() == MinorCardKey.class, is(true));
            assertThat(((MinorCardKey) keys.get(i)).getSuit(), equalTo(Suit.WANDS));
            assertThat(((MinorCardKey) keys.get(i)).getRank(), equalTo(Rank.values()[i% Rank.NUM_RANKS]));
        }
    }

}