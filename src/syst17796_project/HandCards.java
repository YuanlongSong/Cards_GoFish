package syst17796_project;

import java.util.ArrayList;
import syst17796_project.GofishCard.Rank;

/**
 * This class is about the cards in player's hand. It can make books.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class HandCards{

    private ArrayList<GofishCard> cards;

    public HandCards() {
    }

    public ArrayList<GofishCard> getCards() {
        return this.cards;
    }

    /**
     *
     * @param cards
     */
    public void setCards(ArrayList<GofishCard> cards) {
        this.cards = cards;
    }

    public void addCard(GofishCard card) {
        cards.add(card);
    }

    /**
     *
     * @param gofishCard
     * @return
     */
    public GofishCard removeCard(GofishCard gofishCard) {
        return cards.remove(0);
    }

    /**
     * This method will make books and remove cards from handCards
     * @return
     */
    public ArrayList<Book> makeBook() {

        ArrayList<Book> books = new ArrayList<>();

        //find a book and add to books, then remove the cards in book from handCards
        int countForBook = 0;
        for (int i = 0; i < cards.size(); i++) {
            
            for (int j = i; j < cards.size(); j++) {
                if ((((GofishCard) (cards.get(i))).getRank())
                        .equals(((GofishCard) (cards.get(j))).getRank())) {
                    countForBook++;
                    if (countForBook == 4) {
                        Book book = new Book();
                        book.setRank(((GofishCard) (cards.get(i))).getRank());
                        books.add(book);
                        Rank rank = book.getRank();

                        //remove cards from handCards
                        for (int k = 0; k < cards.size(); k++) {
                            if (rank.equals(((GofishCard) cards.get(k)).getRank())) {
                                cards.remove(cards.get(k));
                                k--;
                            }
                        }
                    }
                }
            }
            countForBook = 0;
        }
        return books;
    }
}
