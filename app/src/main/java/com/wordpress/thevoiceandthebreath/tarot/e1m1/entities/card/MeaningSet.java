package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import java.util.ArrayList;
import java.util.List;

public class MeaningSet {

    private String coreMeaning;
    private List<String> additionalMeanings;
    private List<String> keywords;

    public MeaningSet(String coreMeaning, List<String> keywords) {
        this.coreMeaning = coreMeaning;
        this.additionalMeanings = new ArrayList<>();
        this.keywords = keywords;
    }

    public String getCoreMeaning() {
        return coreMeaning;
    }

    public List<String> getAdditionalMeanings() {
        return additionalMeanings;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }


    public void addAdditionalMeaning(String meaning) {
        additionalMeanings.add(meaning);
    }
}
