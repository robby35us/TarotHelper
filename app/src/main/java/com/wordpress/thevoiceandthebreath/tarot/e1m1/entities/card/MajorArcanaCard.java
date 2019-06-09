package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Mode;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

public class MajorArcanaCard extends TarotCard {
    private Name name;
    private Number number;

    public MajorArcanaCard(Number number){
        this.name = Name.values()[number.ordinal()];
        this.number = number;
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
