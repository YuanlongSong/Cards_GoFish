package syst17796_project;

import java.util.ArrayList;

/**
 * This class is about the cards on deck. It contains cards and books made from these cards.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class DeckCards {

    private ArrayList<GofishCard> cards;
    private ArrayList<Book> books;

    public DeckCards() {
    }

    public ArrayList<GofishCard> getCards() {
        return cards;
    }

    /**
     *
     * @param cards
     */
    public void setCards(ArrayList<GofishCard> cards) {
        this.cards = cards;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    /**
     *
     * @param books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    /**
     * this method is used to makes a book, remove that book from dechCards 
     * @param books 
     * @return  
     */
    public ArrayList<Book> removeBooks(ArrayList<Book> books) {

        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < books.size(); j++) {
                if (this.books.get(i).equals(books.get(j))) {
                    this.books.remove(books.get(i));
                    i++;
                }
            }
        }
        return this.books;
    }

    public GofishCard removeCard() {
        return cards.remove(0);
    }

}
