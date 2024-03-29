package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

public class MajorCardKey extends CardKey {
    private Number number;
    private Name name;


    public MajorCardKey(Number number) {
        super(Arcana.MAJOR);
        this.number = number;
        this.name = Name.values()[number.ordinal()];
    }

    public Number getNumber() {
        return number;
    }

    public Name getName() {
        return name;
    }

    @Override
    public CardKey copyKey() {
        return new MajorCardKey(number);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == MajorCardKey.class)
            return this.number == ((MajorCardKey) obj).getNumber();
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() == MajorCardKey.class)
            return Integer.compare(this.getNumber().ordinal(), ((MajorCardKey) o).getNumber().ordinal());
        return -1; // not majorCardKey
    }
}
