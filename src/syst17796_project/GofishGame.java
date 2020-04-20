package syst17796_project;

import ca.sheridancollege.project.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * THis class will stimulate main function of Go-fish game. It is the control part of the app.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class GofishGame extends Game {

    final int minPlayer = 3;
    final int maxPlayer = 5;
    private ArrayList<Player> players;
    private DeckCards deckCards;

    public GofishGame(String name) {
        super(name);
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public DeckCards getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(DeckCards deckCards) {
        this.deckCards = deckCards;
    }

    @Override
    public void play() {
        // TODO - implement GofishGame.play
        throw new UnsupportedOperationException();
    }

    /**
     * this method is used to add cards to the game
     */
    public void addDeckCards() {

        //initialize the deckcards
        ArrayList<GofishCard> cardsForDeck = new ArrayList<>();
        ArrayList<Book> booksForDeck = new ArrayList<>();
        deckCards = new DeckCards();
        deckCards.setCards(cardsForDeck);
        deckCards.setBooks(booksForDeck);
        this.setDeckCards(deckCards);

        // create cards and books of gofish card,then add to deckCards
        GofishCard.Suit[] suits = GofishCard.Suit.values();
        GofishCard.Rank[] ranks = GofishCard.Rank.values();
        for (GofishCard.Rank rank : ranks) {
            for (GofishCard.Suit suit : suits) {
                GofishCard card = new GofishCard();
                ((GofishCard) card).setRank(rank);
                ((GofishCard) card).setSuit(suit);
                deckCards.getCards().add(card);
            }
            Collections.shuffle(deckCards.getCards());
            Book book = new Book();
            book.setRank(rank);
            deckCards.getBooks().add(book);
        }
    }

    /**
     * this method is used to add players to the game
     *
     * @param playerQty
     * @param humanPlayerQty
     * @param humanPlayerNames
     * @param computerPlayerLevels
     */
    public void addPlayers(int playerQty, int humanPlayerQty, ArrayList<String> humanPlayerNames, ArrayList<ComputerPlayer.Level> computerPlayerLevels) {

        players = new ArrayList<>();
        for (int i = 0; i < playerQty; i++) {

            //add computerPlayers
            if (i < (playerQty - humanPlayerQty)) {
                GofishPlayer player = new ComputerPlayer("Computer" + (i + 1));

                player.setActive(Boolean.TRUE);

                ArrayList<Book> books = new ArrayList<>();
                player.setBooks(books);

                ArrayList<GofishCard> cardsForHandCards = new ArrayList<>();
                HandCards handCards = new HandCards();
                handCards.setCards(cardsForHandCards);
                player.setHandCards(handCards);

                GofishCard askCard = new GofishCard();
                player.setAskingCard(askCard);

                ArrayList<GofishCard> cardsAsked = new ArrayList<>();
                ArrayList<GofishCard> cardsReceived = new ArrayList<>();
                Memory memory = new Memory();
                memory.setCardsAsked(cardsAsked);
                memory.setCardsReceived(cardsReceived);
                player.setMemory(memory);

                ((ComputerPlayer) player).setLevel(computerPlayerLevels.get(i));

                players.add(player);
            } else {

                //add humanPlayers
                GofishPlayer player = new HumanPlayer(humanPlayerNames.get(i - (playerQty - humanPlayerQty)));

                player.setActive(Boolean.TRUE);

                ArrayList<Book> books = new ArrayList<>();
                player.setBooks(books);

                ArrayList<GofishCard> cardsForHandCards = new ArrayList<>();
                HandCards handCards = new HandCards();
                handCards.setCards(cardsForHandCards);
                player.setHandCards(handCards);

                GofishCard askCard = new GofishCard();
                player.setAskingCard(askCard);

                ArrayList<GofishCard> cardsAsked = new ArrayList<>();
                ArrayList<GofishCard> cardsReceived = new ArrayList<>();
                Memory memory = new Memory();
                memory.setCardsAsked(cardsAsked);
                memory.setCardsReceived(cardsReceived);
                player.setMemory(memory);

                players.add(player);
            }
        }
    }

    /**
     * the method declares winners
     */
    @Override
    public void declareWinner() {

        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < this.getPlayers().size(); i++) {
            scores.add(((GofishPlayer) players.get(i)).getBooks().size());
        }
        Collections.sort(scores);// sort the scores list from min to max                                           
        int winnerScore = scores.get(scores.size() - 1);// find the winner's score

        // put all the winners in the arraylist winners
        ArrayList<GofishPlayer> winners = new ArrayList<>();
        for (int i = 0; i < this.getPlayers().size(); i++) {
            if (((GofishPlayer) this.getPlayers().get(i)).getBooks().size() == winnerScore) {
                winners.add((GofishPlayer) this.getPlayers().get(i));
            }
        }

        // print out winners
        System.out.println("The winners are below:");
        for (int l = 0; l < winners.size(); l++) {
            System.out.println(winners.get(l) + ": score is " + winners.get(l).getBooks().size());
        }
    }

    /**
     * this method decides the players playing turn
     */
    public void decideTurn() {
        //shuffle the players in random order to play
        Collections.shuffle(players);
    }

    /**
     * this method deals cards
     */
    public void dealCards() {
        int dealLimit;
        if (players.size() <= 3) {
            dealLimit = 7;
        } else {
            dealLimit = 5;
        }
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < dealLimit; j++) {
                ((GofishPlayer) players.get(i)).addCard(deckCards.getCards().remove(0));
            }
        }
    }

    public Book makeBook() {
        // TODO - implement GofishGame.makeBook
        throw new UnsupportedOperationException();
    }

    /**
     * this method show players score
     */
    public void showScore() {
        for (int l = 0; l < this.getPlayers().size(); l++) {
            System.out.println("Player: " + (GofishPlayer) this.getPlayers().get(l) + "'s score is "
                    + ((GofishPlayer) this.getPlayers().get(l)).getBooks().size());
        }
    }

    public GofishPlayer setInactive() {
        // TODO - implement GofishGame.setInactive
        throw new UnsupportedOperationException();
    }

    /**
     * this method remove askCard from askedPlayer and add to askingPlayer
     *
     * @param askCard
     * @param askingPlayer
     * @param askedPlayer
     */
    public void giveAndAddCard(GofishCard askCard, GofishPlayer askingPlayer, GofishPlayer askedPlayer) {

        for (int i = 0; i < askedPlayer.getHandCards().getCards().size(); i++) {
            if (((GofishCard) (askedPlayer.getHandCards().getCards().get(i))).getRank().equals(askCard.getRank())) {
                askingPlayer.addCard(askedPlayer.getHandCards().getCards().remove(i));
                i--;
            }
        }
    }

    /**
     * this method remove book cards form all players memory
     *
     * @param books
     */
    public void removeFromPlayersMemory(ArrayList<Book> books) {

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < books.size(); j++) {
                ((GofishPlayer) players.get(i)).removeFromSinglePlayerMemory(books.get(j).getRank());
            }
        }
    }

    /**
     * this method will make a player playing a calling card turn
     *
     * @param askingPlayer
     * @param askedPlayer
     * @param askCard
     * @return
     */
    public boolean playAskCard(GofishPlayer askingPlayer, GofishPlayer askedPlayer, GofishCard askCard) {
        boolean haveCard = false;
        boolean continueTurn;

        //check if the askedPlayer has the asked card
        for (int j = 0; j < askedPlayer.getHandCards().getCards().size(); j++) {
            if (((GofishCard) askedPlayer.getHandCards().getCards().get(j)).getRank().equals(askCard.getRank())) {
                haveCard = true;
            }
        }

        //if the askedPlayer has the askCard
        if (haveCard) {

            System.out.println(askedPlayer + " is saying that I have the askCard! Give you the card: Rank " + askCard.getRank());

            //add askCard to askingPlayer's memory
            askingPlayer.addMemoryOfCardsReceived(askCard);

            //remove askedCards from askedPlayer and add to askingPlayer
            giveAndAddCard(askCard, askingPlayer, askedPlayer);

            //askedPlay draw cards            
            askedPlayer.drawCard(players.size(), deckCards, false);

            //After giving a card,check and set potential inactive player
            SYST17796_Project.checkAndSetInactive(this);

            //try to make books
            ArrayList<Book> books = askingPlayer.getHandCards().makeBook();
            if (books.isEmpty()) {
                continueTurn = true;
            } //can make book
            else {

                //add books to askingPlayer                                 
                askingPlayer.addBooks(books);

                //remove books from deckCards
                deckCards.removeBooks(books);

                //show askingPlayer' books
                System.out.println("--------------------------------------------------------------------");
                showBooks();

                //show scores of all players
                System.out.println("--------------------------------------------------------------------");
                showPoints();
                System.out.println();
                System.out.println("--------------------------------------------------------------------");

                //remove the cards which make books from all players' memory
                this.removeFromPlayersMemory(books);

                //askingPlayer draw cards to full if possible and remove cards from deckCards
                askingPlayer.drawCard(players.size(), deckCards, false);

                //After giving a card,check and set potential inactive player
                SYST17796_Project.checkAndSetInactive(this);

                //any card left in asking player after making books will continue the turn
                if (askingPlayer.getHandCards().getCards().size() > 0) {
                    continueTurn = true;
                } else {

                    //set askingPlayer as inactive
                    askingPlayer.setActive(false);

                    //change turn if there is any card left in other players
                    continueTurn = false;
                    for (int j = 0; j < this.getPlayers().size(); j++) {
                        continueTurn = continueTurn || ((GofishPlayer) this.getPlayers().get(j)).getHandCards().getCards().size() > 0;
                    }
                }
            }

        } //if the askedPlayer does not have the askCard
        else {

            //askedPlayer say "Go--Fish"
            System.out.println(askedPlayer + " say : GoFish");
            System.out.println("");

            //add the askCard to askingPlayer's memory
            askingPlayer.addMemoryOfCardsAsked(askCard);

            boolean isGofish = true;

            while (true) {
                //askingPlay draw cards
                askingPlayer.drawCard(players.size(), deckCards, isGofish);

                //After giving a card,check and set potential inactive player
                SYST17796_Project.checkAndSetInactive(this);

                //if askingPlayer has no cards in hand, set it as inactive
                if (askingPlayer.getHandCards().getCards().isEmpty()) {
                    askingPlayer.setActive(false);
                    break;
                }

                //try to make books
                ArrayList<Book> books = askingPlayer.getHandCards().makeBook();
                if (books.isEmpty()) {
                    //continueTurn = false;
                    break;
                } //can make book
                else {

                    //add books to askingPlayer                                     
                    askingPlayer.addBooks(books);

                    //remove books from deckCards
                    deckCards.removeBooks(books);

                    //show askingPlayer' books
                    System.out.println("--------------------------------------------------------------------");
                    showBooks();

                    //show scores of all players
                    System.out.println("--------------------------------------------------------------------");
                    showPoints();
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");

                    //remove the cards which make books from all players' memory
                    this.removeFromPlayersMemory(books);

                    //set isGoifish=fals to draw full card
                    isGofish = false;
                }
            }

            //no card left in deckCards,askingPlayer give turn to next player
            continueTurn = false;
        }
        return continueTurn;
    }

    public void showPoints() {
        System.out.println("No show the scores of all players:");
        for (int j = 0; j < players.size(); j++) {
            System.out.println((j + 1) + ". " + players.get(j) + ": " + ((GofishPlayer) players.get(j)).getBooks().size() + "   ");
        }

    }
    
     public void showBooks() {
        System.out.println("No show the scores of all players:");
        for (int j = 0; j < players.size(); j++) {
            System.out.println((j + 1) + ". " + players.get(j) + ": " + ((GofishPlayer) players.get(j)).getBooks().size() + "   ");
        }

    }
}
