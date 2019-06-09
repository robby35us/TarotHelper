package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

class MeaningsBank {
    private MeaningsBank() {}

    static String getCoreMeaning(Number number, Suit suit, Rank rank) {
        if (number != null) {
            switch (number) {
                case ZERO:
                    return "The moment before the first step is taken";
                case ONE:
                    return "Using knowledge, resources, and will to create change in the world";
                case TWO:
                    return "Something that can only be understood through experience";
                case THREE:
                    return "Abundance and creation";
                case FOUR:
                    return "Creating order and stability";
                case FIVE:
                    return "Living faith in everyday life";
                case SIX:
                    return "Making a decision that makes your heart glad";
                case SEVEN:
                    return "The triumph of will in difficult circumstances";
                case EIGHT:
                    return "Calm control that soothes the situation";
                case NINE:
                    return "Retreating from distractions to determine your own truth";
                case TEN:
                    return "Random change is at hand";
                case ELEVEN:
                    return "The consequences of your actions are at hand";
                case TWELVE:
                    return "Willing surrender to an experience or situation";
                case THIRTEEN:
                    return "An ending that makes transformation possible";
                case FOURTEEN:
                    return "The right thing at the right time in the right place";
                case FIFTEEN:
                    return "A choice, action, or situation contrary to your best interest";
                case SIXTEEN:
                    return "An unexpected event that changes everything";
                case SEVENTEEN:
                    return "Guidance, serenity, and hope";
                case EIGHTEEN:
                    return "A situation of flux and uncertainty, either fraught with deception or revealing important truths";
                case NINETEEN:
                    return "Clarity that brings joy";
                case TWENTY:
                    return "Hearing and heeding a call";
                case TWENTY_ONE:
                    return "Successful completion";
            }
        } else if (suit != null && rank != null) {
            switch (suit) {
                case WANDS:
                    switch (rank) {
                        case ACE:
                            return "An opportunity to take action";

                        case TWO:
                            return "Gathering energy while refining your vision";

                        case THREE:
                            return "Active waiting";

                        case FOUR:
                            return "Celebrating the culmination of events or the manifestation of a goal";

                        case FIVE:
                            return "Conflict";

                        case SIX:
                            return "Recognition of achievement";

                        case SEVEN:
                            return "Defensiveness";

                        case EIGHT:
                            return "Swift movement";

                        case NINE:
                            return "Preparing for the next challenge";

                        case TEN:
                            return "Carrying a large burden or many burdens";

                        case PAGE:
                            return "One who is assessing something in the areas of will, inspiration, or passion";

                        case KNIGHT:
                            return "One who acts in the realm of will, inspiration, or passion";

                        case QUEEN:
                            return "One who develops and cares for others in the realm of will, inspiration, or passion";

                        case KING:
                            return "One who has authority, makes decisions, or is a professional in the realm of will, inspiration, or passion";

                    }
                    break;


                case CUPS:
                    switch (rank) {
                        case ACE:
                            return "An opportunity for an emotional experience or growth";

                        case TWO:
                            return "Deep emotional connection or attraction";

                        case THREE:
                            return "A spontaneous, unexpected joy or pleasure";

                        case FOUR:
                            return "Dissatisfaction with reality";

                        case FIVE:
                            return "Experience of loss and grief";

                        case SIX:
                            return "Happy memories";

                        case SEVEN:
                            return "Dreams and desires";

                        case EIGHT:
                            return "Leaving something behind to search for something else";

                        case NINE:
                            return "Material, emotional, and physical well-being";

                        case TEN:
                            return "Happy home";

                        case PAGE:
                            return "One who is assessing something in the area of emotions, art, or relationships";

                        case KNIGHT:
                            return "One who acts in the realm of emotions, art, or relationships";

                        case QUEEN:
                            return "One who develops and cares for others in the realm of emotions, art, or relationships";

                        case KING:
                            return "One who has authority, makes, decisions, or is a professional in the realm of emotions, art, or relationships";
                    }
                    break;


                case SWORDS:
                    switch (rank) {
                        case ACE:
                            return "An opportunity for a new way of thinking";

                        case TWO:
                            return "A conflict between heart and mind";

                        case THREE:
                            return "Sorrow caused by knowledge";

                        case FOUR:
                            return "Respite from troubles";

                        case FIVE:
                            return "A victory tinged with defeat";

                        case SIX:
                            return "Leaving a situation with help";

                        case SEVEN:
                            return "Someone has taken something";

                        case EIGHT:
                            return "A precarious situation";

                        case NINE:
                            return "The power of worry and regret";

                        case TEN:
                            return "Surrender to unpleasant or unfortunate circumstances";

                        case PAGE:
                            return "One who is assessing something in the area of ideas, systems, or communication";

                        case KNIGHT:
                            return "One who acts in the realm of ideas, systems, or communication";

                        case QUEEN:
                            return "One who develops and cares for others in the realm of ideas, systems, or communication";

                        case KING:
                            return "One who has authority, makes decisions, or is a professional in the realm of ideas, systems, or communication";

                    }
                    break;


                case PENTACLES:
                    switch (rank) {
                        case ACE:
                            return "An opportunity for prosperity";

                        case TWO:
                            return "Maintaining balance";

                        case THREE:
                            return "A goal is manifesting.";

                        case FOUR:
                            return "Gathering power";

                        case FIVE:
                            return "Physical need";

                        case SIX:
                            return "Flowing material energy";

                        case SEVEN:
                            return "Appraising results of efforts";

                        case EIGHT:
                            return "Working carefully";

                        case NINE:
                            return "Accomplishment";

                        case TEN:
                            return "Stable and abundant life";

                        case PAGE:
                            return "One who is assessing something in the area of the physical world, resources, or finances";

                        case KNIGHT:
                            return "One who acts in the realm of the physical world, resources, or finances";

                        case QUEEN:
                            return "One who develops and cares for others in the realm of the physical world, resources, or finances";

                        case KING:
                            return "One who has authority, makes decisions, or is a professional in the realm of the physical world, resources, or finances";

                    }
                    break;

            }
        }
        return null;
    }

    static String[] getKeywords(Number number, Suit suit, Rank rank) {
        if (number != null) {
            switch (number) {
                case ZERO:
                    return new String[]{"Beginnings", "Innocence", "Clear Conscience", ""};
                case ONE:
                    return new String[]{"Power", "Adroitness", "Construction", ""};
                case TWO:
                    return new String[]{"Intuition", "Clairvoyance", "Premonition", "Perceptivity"};
                case THREE:
                    return new String[]{"Fertility", "Creativity", "Maternity", ""};
                case FOUR:
                    return new String[]{"Authority", "Government", "Paternity", ""};
                case FIVE:
                    return new String[]{"Tradition", "Conventionalism", "Institutionalism", ""};
                case SIX:
                    return new String[]{"Passion", "Partnership", "The Primordial Choice", ""};
                case SEVEN:
                    return new String[]{"Victory", "Control", "Jurisdiction", ""};
                case EIGHT:
                    return new String[]{"Compassion", "Force", "Fortitude", "Security"};
                case NINE:
                    return new String[]{"Wisdom", "Erudition", "Mentorship", ""};
                case TEN:
                    return new String[]{"Kismet", "Destiny", "Luck", "Serendipity"};
                case ELEVEN:
                    return new String[]{"Equilibrium", "Equity", "Fairness", "Legality"};
                case TWELVE:
                    return new String[]{"Self-Sacrifice", "Suspension", "Prophesy", "Grace"};
                case THIRTEEN:
                    return new String[]{"Transformation", "Mortality", "Suffering", ""};
                case FOURTEEN:
                    return new String[]{"Balance", "Forbearance", "Attune", "Harmonize"};
                case FIFTEEN:
                    return new String[]{"Bondage", "Temptation", " Seduction", ""};
                case SIXTEEN:
                    return new String[]{"Hubris", "Sudden Fall", "Ego", "Catastrophe"};
                case SEVENTEEN:
                    return new String[]{"Hope", "Optimism", "Inspiration", "Visionary"};
                case EIGHTEEN:
                    return new String[]{"Reflection", "Changes", "Imagination", "Speculation"};
                case NINETEEN:
                    return new String[]{"Attainment", "Glory", "Majesty", "Celebrity", "Eminence"};
                case TWENTY:
                    return new String[]{"Awakening", "Epiphany", "Spiritual Realization", ""};
                case TWENTY_ONE:
                    return new String[]{"Fulfilment", "Completion", "Masterpiece", "Consummation"};
            }
        } else {
            switch (suit) {
                case WANDS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Venture", "Opportunity", "", ""};
                        case TWO:
                            return new String[]{"Accomplishment", "Aspiration", "", ""};
                        case THREE:
                            return new String[]{"Validation", "Effort", "Productivity", ""};
                        case FOUR:
                            return new String[]{"Prosperity", "Concord", "Familial Bliss", ""};
                        case FIVE:
                            return new String[]{"Competition", "Rivalry", "Contention", ""};
                        case SIX:
                            return new String[]{"Success", "Victory", "Exalt", "Tribute"};
                        case SEVEN:
                            return new String[]{"Resistance", "Opposition", "Tenacity", ""};
                        case EIGHT:
                            return new String[]{"Action", "Vigor", "Alacrity", "Acceleration"};
                        case NINE:
                            return new String[]{"Defensive", "Protective", "", ""};
                        case TEN:
                            return new String[]{"Burdened", "Travail", "Exhaustion", "Labor"};
                        case PAGE:
                            return new String[]{"Motivated", "Energetic", "Receipt of Project news", ""};
                        case KNIGHT:
                            return new String[]{"Impetuous", "Competitive", "Hot-Headed", ""};
                        case QUEEN:
                            return new String[]{"Self-Assured", "Naturalist", "Down-to-Earth", ""};
                        case KING:
                            return new String[]{"Authoritarian", "Demanding", "Noble", "Honest"};
                    }
                case CUPS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Abundance", "Affirmation", "Fountainhead", ""};
                        case TWO:
                            return new String[]{"Friendship", "Affinity", "Cooperation", ""};
                        case THREE:
                            return new String[]{"Celebration", "Jubilee", "Merriment", ""};
                        case FOUR:
                            return new String[]{"Introspection", "Reflection", "Contemplation", ""};
                        case FIVE:
                            return new String[]{"Loss", "Mourning", "Regrets", ""};
                        case SIX:
                            return new String[]{"Nostalgia", "Childhood", "Reminiscence", "Memories"};
                        case SEVEN:
                            return new String[]{"Temptation", "Superficiality", "Enticement", "Decoys"};
                        case EIGHT:
                            return new String[]{"Abandonment", "Detachment", "Spiritual Journey", ""};
                        case NINE:
                            return new String[]{"Wishful", "Wish Granted", "Comfort", "Material Success"};
                        case TEN:
                            return new String[]{"Happiness", "Joy", "Felicity", ""};
                        case PAGE:
                            return new String[]{"Dreamer", "Sensitive", "Poetic", "Receipt of Personal News"};
                        case KNIGHT:
                            return new String[]{"Charming", "Affable", "Equanimity", ""};
                        case QUEEN:
                            return new String[]{"Nurturing", "Warm", "Tender", "Empathic"};
                        case KING:
                            return new String[]{"Diplomatic", "Caring", "Fatherhood", ""};
                    }
                case SWORDS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Conquest", "Valiance", "", ""};
                        case TWO:
                            return new String[]{"Stalemate", "Catch-22", "Difficult Choice", ""};
                        case THREE:
                            return new String[]{"Strife", "Loss", "Heartbreak", ""};
                        case FOUR:
                            return new String[]{"Repose", "Recovery", "Illness", ""};
                        case FIVE:
                            return new String[]{"Corruption", "Profiteering", "Cunning", "Machiavellian"};
                        case SIX:
                            return new String[]{"Journey", "Pilgrimage", "Baggage", ""};
                        case SEVEN:
                            return new String[]{"Impulsiveness", "Spontaneity", "Deception", ""};
                        case EIGHT:
                            return new String[]{"Imprisonment", "Confinement", "Captivity", "Constraints"};
                        case NINE:
                            return new String[]{"Haunted", "Grieving", "Insomnia", "Troubled"};
                        case TEN:
                            return new String[]{"Retaliation", "Reprisals", "Revenge", "Treachery"};
                        case PAGE:
                            return new String[]{"Ambitions", "Judicious", "Communication", ""};
                        case KNIGHT:
                            return new String[]{"Aggressive", "Charge", "Warrior", ""};
                        case QUEEN:
                            return new String[]{"Astute", "High-Achieving", "Executive", "Striking"};
                        case KING:
                            return new String[]{"Intellectual", "Analytical", "Stern", "Orderly"};
                    }
                case PENTACLES:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Offer", "Investment", "Small Financial Gain", ""};
                        case TWO:
                            return new String[]{"Multitasking", "Juggling", "Prospects", "Possibilities"};
                        case THREE:
                            return new String[]{"Endorsement", "Recognition", "Fraternity", ""};
                        case FOUR:
                            return new String[]{"Miser", "Frugality", "", ""};
                        case FIVE:
                            return new String[]{"Destitution", "Privation", "Hardships", ""};
                        case SIX:
                            return new String[]{"Charity", "Benevolence", "Alms", "Philanthropy"};
                        case SEVEN:
                            return new String[]{"Promotion", "Harvest", "Fiscal Responsibility", ""};
                        case EIGHT:
                            return new String[]{"Aspiration", "Apprenticeship", "Natural Gifts in Craft", ""};
                        case NINE:
                            return new String[]{"Prosperity", "Refinement", "Independence", ""};
                        case TEN:
                            return new String[]{"Stability", "Prestige", "Aristocracy", "Urbanity"};
                        case PAGE:
                            return new String[]{"Practical", "Stylish", "Scholarly", "Receipt of Financial News"};
                        case KNIGHT:
                            return new String[]{"Methodical", "Rational", "Stable", "Reliable"};
                        case QUEEN:
                            return new String[]{"Charitable", "Noble", "Opulence", ""};
                        case KING:
                            return new String[]{"Powerful", "Enterprising", "Established", ""};
                    }
            }
        }
        return null;
    }

    static String getReversedCore(Number number, Suit suit, Rank rank) {
        if (number != null) {
            switch (number) {
                case ZERO:
                    return "Need for planning before an action";
                case ONE:
                    return "Bocked energy, resulting in weakness or misuse of power";
                case TWO:
                    return "The need for communication, sharing ideas and knowledge";
                case THREE:
                    return "Thoughtfulness and caution";
                case FOUR:
                    return "Immaturity or freedom from responsibilities";
                case FIVE:
                    return "Nonconformity, rebellion, making your own way";
                case SIX:
                    return "Conflict between emotions and ideas or values";
                case SEVEN:
                    return "Weak will, delays or setbacks";
                case EIGHT:
                    return "Weakness and doubt";
                case NINE:
                    return "Involvement in society";
                case TEN:
                    return "A change we cannot predict or control";
                case ELEVEN:
                    return "Unwillingness to look honestly at an issue";
                case TWELVE:
                    return "Influence of social expectations or believes";
                case THIRTEEN:
                    return "Fear of change";
                case FOURTEEN:
                    return "Excessive behavior or going to extremes";
                case FIFTEEN:
                    return "Steps toward liberation";
                case SIXTEEN:
                    return "An unexpected event that changes things to a lesser degree";
                case SEVENTEEN:
                    return "Doubt, pessimism or a false hope";
                case EIGHTEEN:
                    return "Something waning or on the way out";
                case NINETEEN:
                    return "Happiness mixed with sadness ";
                case TWENTY:
                    return "Doubt that blocks acceptance of change or possibility";
                case TWENTY_ONE:
                    return "Stagnation or delays rather than failure";
            }
        } else if (suit != null && rank != null) {
            switch (suit) {
                case WANDS:
                    switch (rank) {
                        case ACE:
                            return "Hesitation";
                        case TWO:
                            return "A move into new territory of a person's life";
                        case THREE:
                            return "The end of adversity";
                        case FOUR:
                            return "Celebration and joy";
                        case FIVE:
                            return "Focusing energy or enthusiasm in productive ways";
                        case SIX:
                            return "Negativity, pessimism, self-doubt";
                        case SEVEN:
                            return "Feeling overwhelmed";
                        case EIGHT:
                            return "Delays and lack of focus";
                        case NINE:
                            return "Giving up on a defensive position";
                        case TEN:
                            return "Release of responsibility";
                        case PAGE:
                            return "Uncertainty, hesitancy, caution";
                        case KNIGHT:
                            return "Overconfidence";
                        case QUEEN:
                            return "Generous and good in a crisis";
                        case KING:
                            return "Being tested or confined";
                    }
                    break;


                case CUPS:
                    switch (rank) {
                        case ACE:
                            return "Blocked Happiness";
                        case TWO:
                            return "A relationship that is less significant than expected";
                        case THREE:
                            return "Strain between friends";
                        case FOUR:
                            return "Taking a risk or seeing an offer that was previously ignored";
                        case FIVE:
                            return "Recognition of something valuable";
                        case SIX:
                            return "Focus on present situations";
                        case SEVEN:
                            return "Taking action on fantasies";
                        case EIGHT:
                            return "Recognizing the value of a situation and how to improve it";
                        case NINE:
                            return "Truth and loyalty ahead of personal satisfaction";
                        case TEN:
                            return "Difficulty appreciating previous blessings";
                        case PAGE:
                            return "One who is troubled by the imagination or subconscious";
                        case KNIGHT:
                            return "Being provoked into action";
                        case QUEEN:
                            return "Getting lost in inner self";
                        case KING:
                            return "Release of hidden emotions";
                    }
                    break;


                case SWORDS:
                    switch (rank) {
                        case ACE:
                            return "Confusion, difficulty thinking clearly";
                        case TWO:
                            return "Becoming involved with others";
                        case THREE:
                            return "Recovery from sorrow or release of pain";
                        case FOUR:
                            return "A return to activity";
                        case FIVE:
                            return "Moving beyond a disturbing loss or defeat";
                        case SIX:
                            return "Speaking up";
                        case SEVEN:
                            return "Willingness to consult with others before taking action";
                        case EIGHT:
                            return "Helplessness or confusion";
                        case NINE:
                            return "Beginnings of recovery";
                        case TEN:
                            return "Relief from suffering";
                        case PAGE:
                            return "Learning to relax and trust people";
                        case KNIGHT:
                            return "Fighting for its own sake";
                        case QUEEN:
                            return "Manipulation and control";
                        case KING:
                            return "Serving one's self rather than society";
                    }
                    break;


                case PENTACLES:
                    switch (rank) {
                        case ACE:
                            return "Conflicts around money and security";
                        case TWO:
                            return "Juggling aspects of work, life, or a tight budget";
                        case THREE:
                            return "Mediocrity in work or elsewhere in daily life";
                        case FOUR:
                            return "Letting go of possessions to open up to others and new experiencesf";
                        case FIVE:
                            return "Relief from suffering";
                        case SIX:
                            return "Self-sufficiency";
                        case SEVEN:
                            return "Work resumed";
                        case EIGHT:
                            return "Frustration or dissatisfaction with a situation";
                        case NINE:
                            return "Lack of discipline";
                        case TEN:
                            return "Discovery of deeper meaning in life";
                        case PAGE:
                            return "Trouble that stems from outside pressure ";
                        case KNIGHT:
                            return "Allowing others to take advantage";
                        case QUEEN:
                            return "Detachment from nature";
                        case KING:
                            return "Worries about money or physical insecurity";

                    }
                    break;

            }
        }
        return null;
    }

    static String[] getReversedKeywords(Number number, Suit suit, Rank rank) {
        if (number != null) {
            switch (number) {
                case ZERO:
                    return new String[]{"Vanity", "Foolishness", "Indiscretion", "Inanity"};
                case ONE:
                    return new String[]{"Destruction", "Bane", "Untapped Potential", ""};
                case TWO:
                    return new String[]{"Deceit", "Duplicity", "Hypocrisy", "Secrets", ""};
                case THREE:
                    return new String[]{"Barren", "Lacuna", "Cavity", "Omission"};
                case FOUR:
                    return new String[]{"Passivity", "Overthrow", "Coup d'etat", ""};
                case FIVE:
                    return new String[]{"Non-conformity", "Unorthodox", "Bohemian", "Existentialist"};
                case SIX:
                    return new String[]{"Temptation", "Interference", "Moral Quandary", ""};
                case SEVEN:
                    return new String[]{"Riot", "Turbulence", "Wrong Direction", ""};
                case EIGHT:
                    return new String[]{"Brute", "Carnal", "Physical", "Obdurate"};
                case NINE:
                    return new String[]{"Ignorance", "Immaturity", "Petulant", "Unreasonable"};
                case TEN:
                    return new String[]{"Flux", "Unrest", "Mischance", "Misfortune"};
                case ELEVEN:
                    return new String[]{"Prejudice", "Bias", "Chauvinism", "Injustice"};
                case TWELVE:
                    return new String[]{"Selfishness", "Martyrdom", "Persecution", ""};
                case THIRTEEN:
                    return new String[]{"Conclusion", "Cessation", "Denouement", "Windup"};
                case FOURTEEN:
                    return new String[]{"Excess", "Overflow", "", ""};
                case FIFTEEN:
                    return new String[]{"Defeat", "Release", "Severance", ""};
                case SIXTEEN:
                    return new String[]{"Oppression", "Cataclysm", "", ""};
                case SEVENTEEN:
                    return new String[]{"Pessimism", "Negativity", "Maladies", ""};
                case EIGHTEEN:
                    return new String[]{"Inconstancy", "Risky", "Confusion", "Uncertainty"};
                case NINETEEN:
                    return new String[]{"Disorientation", "Quiescence", "", ""};
                case TWENTY:
                    return new String[]{"Misinterpretation", "Misreckoning", "", ""};
                case TWENTY_ONE:
                    return new String[]{"Hiatus", "Agnosticism", "Skeptic", "Lacking Fulfillment"};
            }
        } else {
            switch (suit) {
                case WANDS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Indolence", "Procrastination", "", ""};
                        case TWO:
                            return new String[]{"Impatience", "Haste", "Rush", ""};
                        case THREE:
                            return new String[]{"Negligence", "Careless", "Setbacks", "Mistakes"};
                        case FOUR:
                            return new String[]{"Attunement", "Harmony", "Rite of Passage", ""};
                        case FIVE:
                            return new String[]{"Opportunity", "Reconciliation", "", ""};
                        case SIX:
                            return new String[]{"Postponement", "Pending Advancement", "", ""};
                        case SEVEN:
                            return new String[]{"Indecision", "Vulnerability", "Lacking Confidence", ""};
                        case EIGHT:
                            return new String[]{"Altercation", "Regression", "Rashness", ""};
                        case NINE:
                            return new String[]{"Exhaustion", "Battle", "Depletion", ""};
                        case TEN:
                            return new String[]{"Accountability", "Reassessment", "", ""};
                        case PAGE:
                            return new String[]{"Passivity", "Inarticulate", "", ""};
                        case KNIGHT:
                            return new String[]{"Insecurity", "Hesitation", "", ""};
                        case QUEEN:
                            return new String[]{"Capitulation", "Yielding", "Lacking Virtue", ""};
                        case KING:
                            return new String[]{"Tyranny", "Despotic", "Disciplinarian", "Doctrinaire"};
                    }
                case CUPS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Superficiality", "Rejection", "Promiscuity", ""};
                        case TWO:
                            return new String[]{"Imbalance", "Quarreling", "Misunderstandings", ""};
                        case THREE:
                            return new String[]{"Hedonism", "Indugence", "Sensualist", ""};
                        case FOUR:
                            return new String[]{"Regeneration", "Emergence", "", ""};
                        case FIVE:
                            return new String[]{"Restoration", "Acceptance", "", ""};
                        case SIX:
                            return new String[]{"Clinging", "Attachment", "Naivete", "Childishness"};
                        case SEVEN:
                            return new String[]{"Determination", "Decision", "Purpose Revealed", ""};
                        case EIGHT:
                            return new String[]{"Epicurean", "Peripatetic", "Drifting", "Aimless"};
                        case NINE:
                            return new String[]{"Overindulgence", "Greed", "", ""};
                        case TEN:
                            return new String[]{"Discord", "Broken Family", "Shattered Values", ""};
                        case PAGE:
                            return new String[]{"Quixotic", "Dreamer", "", ""};
                        case KNIGHT:
                            return new String[]{"Discouragement", "Ennui", "Unmotivated", ""};
                        case QUEEN:
                            return new String[]{"Manipulation", "Jealousy", "", ""};
                        case KING:
                            return new String[]{"Overwrought", "Distracted", "", ""};
                    }
                case SWORDS:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Disruption", "Entropy", "Chaos", "Complications", "Anarchy"};
                        case TWO:
                            return new String[]{"Settlement", "Decision", "Arbitration", ""};
                        case THREE:
                            return new String[]{"Disorder", "Hurt", "", ""};
                        case FOUR:
                            return new String[]{"Caution", "Social Unrest", "", ""};
                        case FIVE:
                            return new String[]{"Recompense", "Contrition", "Penance", ""};
                        case SIX:
                            return new String[]{"Afflicted", "Trapped", "Yearning Escape", ""};
                        case SEVEN:
                            return new String[]{"Reparation", "Forfeiture", "Requital", ""};
                        case EIGHT:
                            return new String[]{"Emancipation", "Release", "Clemency", ""};
                        case NINE:
                            return new String[]{"Recuperation", "Healing", "Infidelity", ""};
                        case TEN:
                            return new String[]{"Endurance", "Comeback", "", ""};
                        case PAGE:
                            return new String[]{"Inhibition", "Self-Conscious", "", ""};
                        case KNIGHT:
                            return new String[]{"Warpath", "Aggression", "Hostility", ""};
                        case QUEEN:
                            return new String[]{"Spite", "Snobbery", "Elitism", ""};
                        case KING:
                            return new String[]{"Malice", "Unyielding", "", ""};
                    }
                case PENTACLES:
                    switch (rank) {
                        case ACE:
                            return new String[]{"Disillusionment", "Financial Disappointment", "", ""};
                        case TWO:
                            return new String[]{"Overwhelmed", "Must Prioritize", "", ""};
                        case THREE:
                            return new String[]{"Mediocrity", "Displacement", "", ""};
                        case FOUR:
                            return new String[]{"Recession", "Obstacles", "", ""};
                        case FIVE:
                            return new String[]{"Rehabilitation", "Refurbish", "Improvement", ""};
                        case SIX:
                            return new String[]{"Ungracious", "Indigence", "Financial Hardship", ""};
                        case SEVEN:
                            return new String[]{"Anxiety", "Denial", "Non-Confrontation", ""};
                        case EIGHT:
                            return new String[]{"Squandering", "Trifle", "Idle", ""};
                        case NINE:
                            return new String[]{"Dissatisfaction", "Depression", "", ""};
                        case TEN:
                            return new String[]{"Disengagement", "Ostracism", "Familial disunion", ""};
                        case PAGE:
                            return new String[]{"Materialism", "Superficiality", "Receipt of Financial News", ""};
                        case KNIGHT:
                            return new String[]{"Inertia", "Timidity", "", ""};
                        case QUEEN:
                            return new String[]{"Dependence", "Indebtedness", "Self-Preservation", ""};
                        case KING:
                            return new String[]{"Sophomoric", "Stoic", "", ""};
                    }
            }
        }
        return null;
    }

}
