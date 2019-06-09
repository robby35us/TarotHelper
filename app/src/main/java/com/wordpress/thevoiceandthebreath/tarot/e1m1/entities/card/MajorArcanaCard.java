package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Mode;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

public class MajorArcanaCard extends TarotCard {
    private Name name;
    private Number number;

    public MajorArcanaCard(MajorCardKey key){
        super(key);
        this.name = key.getName();
        this.number = key.getNumber();
    }

    public Name getName() {
        return name;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public Arcana getArcana() {
        return Arcana.MAJOR;
    }

    @Override
    public Mode getMode() {
       return Mode.MAJOR;
    }

    @Override
    public String getTitle() {
        return number.toString() + ": " + name.toString();
    }
}
