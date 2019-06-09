package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions;

public enum Mode {
    ACE, PIP, COURT, MAJOR;

    @Override
    public String toString() {
        switch (this) {
            case ACE:
                return "Ace";
            case PIP:
                return "Pip Card";
            case COURT:
                return "Court Card";
            case MAJOR:
                return "Major Arcana";
        }
        return super.toString();
    }

    public static Mode getModeFromRank(Rank rank) {
        switch (rank) {
            case ACE:
                return Mode.ACE;
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                return Mode.PIP;
            case PAGE:
            case KNIGHT:
            case QUEEN:
            case KING:
                return Mode.COURT;
        }
        return null;
    }
}
