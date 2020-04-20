package syst17796_project;

import ca.sheridancollege.project.Card;

/**
 * The class is about Go-fish game card. It has 52 cards in four suits and 13 ranks.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class GofishCard extends Card {

    private Rank rank;
    private Suit suit;

    public enum Rank {
        One, Two, Three, Four, Five, Six, Seven,
        Eight, Nine, Ten, Eleven, Twelve, Thirteen
    };

    public enum Suit {
        CLUB, DIAMONDS, HEART, SPADE
    };

    public GofishCard() {
    }

    public Rank getRank() {
        return this.rank;
    }

    /**
     *
     * @param rank
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    /**
     *
     * @param suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Rank :  "+ rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GofishCard) {
            return ((GofishCard) obj).getRank() == this.getRank() && ((GofishCard) obj).getSuit() == this.getSuit();
        }
        return false;
    }

}
