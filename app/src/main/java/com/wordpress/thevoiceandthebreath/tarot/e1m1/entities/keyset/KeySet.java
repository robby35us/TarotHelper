package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Orientation;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

import java.util.ArrayList;
import java.util.List;

public class KeySet {


    private List<CardKey> keys;

    public KeySet(List<CardKey> keys){
        this.keys = keys;
    }

    public List<CardKey> getKeys() {
        return keys;
    }

    public int getSize() {
        return keys.size();
    }

    public CardKey getKey(int i) {
        return keys.get(i);
    }

    public int[] serialize() {
        return KeySetSerializer.serialize(keys);
    }

    public static int[] serializeSingle(CardKey cardKey) {
        List<CardKey> singleKeyList = new ArrayList<>();
        singleKeyList.add(cardKey);
        return KeySetSerializer.serialize(singleKeyList);
    }

    public static KeySet deserialize(int[] serialization) {
        return new KeySet(KeySetSerializer.deserialize(serialization));
    }

    public static CardKey deserializeSingle(int[] serialization) {
        return KeySetSerializer.deserialize(serialization).get(0);
    }


    private static class KeySetSerializer {
        private static final int NUM_DATA_POINTS = 5;
        private static final int ORIENTATION_OFFSET = 0;
        private static final int ARCANA_OFFSET = 1;
        private static final int NUMBER_OFFSET = 2;
        private static final int SUIT_OFFSET = 3;
        private static final int RANK_OFFSET = 4;

        static int[] serialize(List<CardKey> keys) {
            int[] output = new int[keys.size() * NUM_DATA_POINTS];

            for(int i = 0; i < output.length; i += NUM_DATA_POINTS) {
                CardKey key = keys.get(i / NUM_DATA_POINTS);
                putKeyData(key, i, output);
            }

            return output;
        }

        static List<CardKey>  deserialize(int[] serialization) {
            List<CardKey> keys = new ArrayList<>(serialization.length / NUM_DATA_POINTS);

            for(int i = 0; i < serialization.length; i += NUM_DATA_POINTS)
                keys.add(extractKey(i, serialization));

            return keys;
        }


        private static void putKeyData(CardKey key, int i, int[] output) {
            putParentClassData(key, i, output);
            if(key.getArcana() == Arcana.MAJOR) {
                putMajorCardData((MajorCardKey)key, i, output);
            } else
                putMinorCardData((MinorCardKey) key, i, output);
        }

        private static void putParentClassData(CardKey key, int i, int[] output) {
            output[i + ORIENTATION_OFFSET] = key.getOrientation().ordinal();
            output[i + ARCANA_OFFSET] = key.getArcana().ordinal();
        }

        private static void putMajorCardData(MajorCardKey key, int i, int[] output) {
            output[i + NUMBER_OFFSET] = key.getNumber().ordinal();
            output[i + SUIT_OFFSET] = -1;
            output[i + RANK_OFFSET] = -1;
        }

        private static void putMinorCardData(MinorCardKey key, int i, int[] output) {
            output[i + NUMBER_OFFSET] = -1;
            output[i + SUIT_OFFSET] = key.getSuit().ordinal();
            output[i + RANK_OFFSET] = key.getRank().ordinal();
        }

        private static CardKey extractKey(int i, int[] serialization) {
            CardKey key;
            if(serialization[i + ARCANA_OFFSET] == Arcana.MAJOR.ordinal())
                key = extractMajorKey(i, serialization);
            else
                key = extractMinorKey(i, serialization);
            setOrientation(key, i, serialization);
            return key;
        }

        private static MajorCardKey extractMajorKey(int i, int[] serialization) {
            Number number = Number.values()[serialization[i + NUMBER_OFFSET]];
            return new MajorCardKey(number);
        }

        private static MinorCardKey extractMinorKey(int i, int[] serialization) {
            Suit suit = Suit.values()[serialization[i + SUIT_OFFSET]];
            Rank rank = Rank.values()[serialization[i + RANK_OFFSET]];
            return new MinorCardKey(suit, rank);
        }

        private static void setOrientation(CardKey key, int i, int[] serialization) {
            Orientation orientation = Orientation.values()[serialization[i + ORIENTATION_OFFSET]];
            if(orientation == Orientation.Reversed)
                key.toggleOrientation();
        }
    }
}
