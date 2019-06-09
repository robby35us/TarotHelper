package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import java.util.List;

public class KeySet {

    private List<CardKey> keys;

    public KeySet(List<CardKey> keys){
        this.keys = keys;
    }

    public List<CardKey> getKeys() {
        return keys;
    }
}
