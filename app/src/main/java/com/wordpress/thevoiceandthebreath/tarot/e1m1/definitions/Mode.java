package com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions;

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
}
