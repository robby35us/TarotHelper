package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions;

public enum Name {
    THE_FOOL, THE_MAGICIAN, THE_HIGH_PRIESTESS, THE_EMPRESS, THE_EMPEROR, THE_HIEROPHANT, THE_LOVERS,
    THE_CHARIOT, STRENGTH, THE_HERMIT, WHEEL_OF_FORTUNE, JUSTICE, THE_HANGED_MAN, DEATH,
    TEMPERANCE, THE_DEVIL, THE_TOWER, THE_STAR, THE_MOON, THE_SUN, JUDGEMENT, THE_WORLD;

    @Override
    public String toString() {
        switch (this) {
            case THE_FOOL:
                return "The Fool";
            case THE_MAGICIAN:
                return "The Magician";
            case THE_HIGH_PRIESTESS:
                return "The High Priestess";
            case THE_EMPRESS:
                return "The Empress";
            case THE_EMPEROR:
                return "The Emperor";
            case THE_HIEROPHANT:
                return "The Hierophant";
            case THE_LOVERS:
                return "The Lovers";
            case THE_CHARIOT:
                return "The Chariot";
            case STRENGTH:
                return "Strength";
            case THE_HERMIT:
                return "The Hermit";
            case WHEEL_OF_FORTUNE:
                return "Wheel of Fortune";
            case JUSTICE:
                return "Justice";
            case THE_HANGED_MAN:
                return "The Hanged Man";
            case DEATH:
                return "Death";
            case TEMPERANCE:
                return "Temperance";
            case THE_DEVIL:
                return "The Devil";
            case THE_TOWER:
                return "The Tower";
            case THE_STAR:
                return "The Star";
            case THE_MOON:
                return "The Moon";
            case THE_SUN:
                return "The Sun";
            case JUDGEMENT:
                return "Judgement";
            case THE_WORLD:
                return "The World";
        }

        return super.toString();
    }
}