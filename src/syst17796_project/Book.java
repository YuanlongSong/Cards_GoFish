package syst17796_project;

import syst17796_project.GofishCard.Rank;

/**
 * This is a class of book. Four same rank card will make a book
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class Book {

    private Rank rank;
    
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

    @Override
    public String toString() {
        return "Book{" + "rank=" + rank + '}';
    }
    

}
