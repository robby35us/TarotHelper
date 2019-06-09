package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeySetShuffler {
    private static final int REVERSAL_RANDOM_UPPER_BOUND = 4;
    private static final int REVERSAL_RANDOM_LOWER_BOUND = 0;
    private static final SecureRandom random = new SecureRandom();

    private KeySetShuffler(){}

    public static KeySet shuffleSet(KeySet set) {
        List<CardKey> resultList = new ArrayList<>(set.getKeys());
        Collections.shuffle(resultList, random);
        return new KeySet(resultList);
    }

    public static KeySet shuffleSetWithReversals(KeySet set) {
        List<CardKey> resultList = new ArrayList<>();
        for(CardKey key : set.getKeys()) {
            resultList.add(key.copyKey());
            if(random.nextInt(REVERSAL_RANDOM_UPPER_BOUND) == REVERSAL_RANDOM_LOWER_BOUND)
                resultList.get(resultList.size()  - 1 ).toggleOrientation();
        }
        return shuffleSet(new KeySet(resultList));
    }
}
