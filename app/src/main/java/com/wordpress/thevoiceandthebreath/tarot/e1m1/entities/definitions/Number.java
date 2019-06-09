package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions;

public enum Number {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX,
    SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN,
    FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN,
    NINETEEN, TWENTY, TWENTY_ONE;

    @Override
    public String toString() {
        switch (this) {
            case ZERO:
                return "0";
            case ONE:
                return "I";
            case TWO:
                return "II";
            case THREE:
                return "III";
            case FOUR:
                return "IV";
            case FIVE:
                return "V";
            case SIX:
                return "VI";
            case SEVEN:
                return "VII";
            case EIGHT:
                return "VIII";
            case NINE:
                return "IX";
            case TEN:
                return "X";
            case ELEVEN:
                return "XI";
            case TWELVE:
                return "XII";
            case THIRTEEN:
                return "XIII";
            case FOURTEEN:
                return "XIV";
            case FIFTEEN:
                return "XV";
            case SIXTEEN:
                return "XVI";
            case SEVENTEEN:
                return "XVII";
            case EIGHTEEN:
                return "XVIII";
            case NINETEEN:
                return "XIX";
            case TWENTY:
                return "XX";
            case TWENTY_ONE:
                return "XXI";
        }

        return super.toString();
    }
}
