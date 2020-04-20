package syst17796_project;

import java.util.ArrayList;

/**
 * This class represents the memory of a human being. All the asking and replying information is stored in the memory of each player.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class Memory {

    private ArrayList<GofishCard> cardsAsked;
    private ArrayList<GofishCard> cardsReceived;

    public Memory() {
    }

    public ArrayList<GofishCard> getCardsAsked() {
        return this.cardsAsked;
    }

    /**
     *
     * @param cardsAsked
     */
    public void setCardsAsked(ArrayList<GofishCard> cardsAsked) {
        this.cardsAsked = cardsAsked;
    }

    public ArrayList<GofishCard> getCardsReceived() {
        return this.cardsReceived;
    }

    /**
     *
     * @param cardsReceived
     */
    public void setCardsReceived(ArrayList<GofishCard> cardsReceived) {
        this.cardsReceived = cardsReceived;
    }

}
