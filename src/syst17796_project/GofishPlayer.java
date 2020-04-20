package syst17796_project;

import ca.sheridancollege.project.*;
import java.util.ArrayList;
import syst17796_project.GofishCard.Rank;

/**
 * The class is about Go-fish game player which is extended form more abstract player.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */

public class GofishPlayer extends Player {

    private final String name;
    private HandCards handCards;
    private ArrayList<Book> books;
    private Boolean active;
    private Memory memory;
    private GofishCard askingCard;

    public GofishPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public HandCards getHandCards() {
        return handCards;
    }

    public void setHandCards(HandCards handCards) {
        this.handCards = handCards;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public GofishCard getAskingCard() {
        return askingCard;
    }

    public void setAskingCard(GofishCard askingCard) {
        this.askingCard = askingCard;
    }

    /**
     *
     * @param books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public void play() {
        // TODO - implement GofishPlayer.play
        throw new UnsupportedOperationException();
    }

    public GofishCard askCard() {
        return null;
    }

    /**
     *
     * @param card
     */
    public void addCard(GofishCard card) {
        handCards.addCard(card);
    }

    public Player askWho(Game game) {
        return null;
    }

    /**
     *
     * @param card
     * @return
     */
    public Card giveCard(GofishCard card) {
        return handCards.removeCard(card);
    }

    public GofishCard drawCard() {
        // TODO - implement GofishPlayer.drawCard
        throw new UnsupportedOperationException();
    }

    /**
     * this method removes any card which makes book from memory
     *
     * @param rank
     */
    public void removeFromSinglePlayerMemory(Rank rank) {

        for (int j = 0; j < memory.getCardsAsked().size(); j++) {
            if (((GofishCard) memory.getCardsAsked().get(j)).getRank().equals(rank)) {
                memory.getCardsAsked().remove(memory.getCardsAsked().get(j));
                j--;
            }
        }

        for (int j = 0; j < memory.getCardsReceived().size(); j++) {
            if (((GofishCard) memory.getCardsReceived().get(j)).getRank().equals(rank)) {
                memory.getCardsReceived().remove(memory.getCardsReceived().get(j));
                j--;
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * this method removes book cards from handCards
     *
     * @param books
     */
    public void removeBookCardsFromHandCards(ArrayList<Book> books) {

        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < handCards.getCards().size(); j++) {
                if (((GofishCard) handCards.getCards().get(i)).getRank().equals(books.get(j).getRank())) {
                    handCards.getCards().remove(j);
                    j--;
                }
            }
        }
    }

    /**
     * this method removes same cards as askCard from askedPlayer
     *
     * @param askCard
     */
    public void remove(GofishCard askCard) {

        for (int j = 0; j < handCards.getCards().size(); j++) {
            if (((GofishCard) handCards.getCards().get(j)).getRank().equals(askCard.getRank())) {
                handCards.getCards().remove((GofishCard) handCards.getCards().get(j));
                j--;
            }
        }
    }

    /**
     * this method is used to draw cards to full if possible and remove from deckCards
     *
     * @param size
     * @param deckCards
     * @param isGoFish
     */
    public void drawCard(int size, DeckCards deckCards, boolean isGoFish) {

        int dealLimit;
        if (size <= 3) {
            dealLimit = 7;
        } else {
            dealLimit = 5;
        }

        if (isGoFish) {
            if (!deckCards.getCards().isEmpty()) {
                handCards.addCard(deckCards.getCards().remove(0));
            }
        } else {
            for (int i = 0; i < dealLimit - handCards.getCards().size(); i++) {
                if (!deckCards.getCards().isEmpty()) {
                    handCards.addCard(deckCards.getCards().remove(0));
                }
            }

        }

    }

    public Card removeCard(GofishCard askCard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method adds new books to existing books
     *
     * @param books
     */
    public void addBooks(ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            this.books.add(books.get(i));
        }
    }

    /**
     * this method adds a card to player's memory for asked cards
     *
     * @param askCard
     */
    public void addMemoryOfCardsAsked(GofishCard askCard) {
        memory.getCardsAsked().add(askCard);
    }

   /**
     * this method adds a card to player's memory for received cards
     *
     * @param askCard
     */
    public void addMemoryOfCardsReceived(GofishCard askCard) {
        memory.getCardsReceived().add(askCard);
    }
}
