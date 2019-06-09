package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Orientation;

public abstract class CardKey {
    private Arcana arcana;
    private Orientation orientation;

    public CardKey(Arcana arcana) {
        this.arcana = arcana;
        this.orientation = Orientation.Upright;
    }

    public Arcana getArcana() {
        return arcana;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void toggleOrientation(){
        orientation = orientation == Orientation.Upright ? Orientation.Reversed: Orientation.Upright;
    }

    public abstract CardKey copyKey();
}
