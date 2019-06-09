package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.MeaningModel;

public class CardModel{
    private TarotCard card;
    private MeaningModel upright;
    private MeaningModel reversed;

    public CardModel(TarotCard card) {
        this.card = card;
        upright = new MeaningModel(card.getUprightMeanings());
        reversed = new MeaningModel(card.getReversedMeanings());
    }

    public String getTitle(){
        return card.getTitle();
    }

    public String getSplitTitle1() {
        if (card.getArcana() == Arcana.MAJOR)
            return getMajorTitle1();
        else
            return getMinorTitle1();
    }

    public String getSplitTitle2(){
        if(card.getArcana() == Arcana.MAJOR)
            return getMajorTitle2();
        else
            return getMinorTitle2();
    }

    public String getImageFilename() {
        if(card.getArcana() == Arcana.MAJOR)
            return getMajorFilename();
        else
            return getMinorFilename();
    }

    public MeaningModel getUprightMeanings() {
        return upright;
    }

    public MeaningModel getReversedMeanings() {
        return reversed;
    }

    private String getMajorTitle1() {
        Name name = ((MajorArcanaCard) card).getName();
        Number number = ((MajorArcanaCard) card).getNumber();
        String nameFirstWord = name.toString().split(" ")[0];
        if(nameFirstWord.equals("The") || nameFirstWord.equals("Wheel"))
            return number.toString() + ": " + nameFirstWord + " ";
        else
            return number.toString() + ": ";
    }

    private String getMajorTitle2() {
        Name name = ((MajorArcanaCard) card).getName();
        String[] split = name.toString().split(" ");
        String nameFirstWord = split[0];
        if(nameFirstWord.equals("The") || nameFirstWord.equals("Wheel")) {
            String result = split [1];
            if(split.length > 2)
                result += " " + split[2];
            return result;
        } else {
            return nameFirstWord;
        }
    }

    private String getMinorTitle1() {
        Rank rank = ((MinorArcanaCard) card).getRank();
        return rank.toString() + " of ";
    }

    private String getMinorTitle2() {
        return ((MinorArcanaCard) card).getSuit().toString();
    }

    private String getMajorFilename() {
        return ((MajorArcanaCard) card).getName().toString().toLowerCase().replace(" ", "_") + ".jpg";
    }

    private String getMinorFilename() {
        Suit suit = ((MinorArcanaCard) card).getSuit();
        Rank rank = ((MinorArcanaCard) card).getRank();
        return rank.toString().toLowerCase() + "_" + suit.toString().toLowerCase() + ".jpg";
    }
}
