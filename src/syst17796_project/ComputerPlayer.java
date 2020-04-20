package syst17796_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a computer player class. Computer player has skill level which is different form humanPlayer
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class ComputerPlayer extends GofishPlayer {

    Scanner input = new Scanner(System.in);

    private Level level;

    public enum Level {
        One, Two, Three, Four, Five
    }

    public ComputerPlayer(String name) {
        super(name);
    }

    public Level getLevel() {
        return this.level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public GofishCard askCard() {
        return super.getAskingCard();
    }

    /**
     * this method help computerPlayer to decide to ask which player
     *
     * @param game
     * @return
     */
    public GofishPlayer askWho(GofishGame game) {

        //get the players who are available to be asked
        ArrayList<Integer> availablePlayerNums = new ArrayList<>();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!game.getPlayers().get(i).equals(this) && ((GofishPlayer) game.getPlayers().get(i)).getActive()) {
                availablePlayerNums.add(i);
            }
        }

        //create a cards ArrayList to hold the sole cards list
        ArrayList<GofishCard> cards = new ArrayList<>();
        for (int j = 0; j < this.getHandCards().getCards().size(); j++) {
            cards.add(this.getHandCards().getCards().get(j));
        }

        //create a counts ArrayList to hold the times that which card occures
        ArrayList<Integer> counts = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < cards.size(); j++) {
            for (int k = j; k < cards.size(); k++) {
                if (cards.get(j).getRank().equals(cards.get(k).getRank())) {
                    count++;
                    if (j != k) {
                        cards.remove(k);
                        k--;
                    }
                }
            }
            counts.add(count);
            count = 0;
        }

        //sort the cards in the order of count number in counts ArrayList
        int tempNum;
        GofishCard tempGofishCard;
        if (cards.size() > 1) {
            for (int j = 0; j < cards.size() - 1; j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    if (counts.get(j) > counts.get(k)) {
                        tempNum = counts.get(j);
                        counts.set(j, counts.get(k));
                        counts.set(k, tempNum);

                        tempGofishCard = cards.get(j);
                        cards.set(j, cards.get(k));
                        cards.set(k, tempGofishCard);
                    }
                }
            }
        }

        //create a list to hold the index number of the card in cards in the order of book level
        ArrayList<Integer> playerMatchLevels = new ArrayList<>();

        //create a player list to hold the players in the order of book level
        ArrayList<GofishPlayer> matchPlayers = new ArrayList<>();

        //create a list to hold the book level of the players
        ArrayList<Integer> matchLevels = new ArrayList<>();

        //find the level of making book of each card and response player
        for (int i = 0; i < cards.size(); i++) {
            matchLevels.clear();
            for (int j = 0; j < availablePlayerNums.size(); j++) {
                int matchLevel = 1;
                for (int k = 0; k < ((GofishPlayer) game.getPlayers().get(availablePlayerNums.get(j))).getMemory().getCardsReceived().size(); k++) {
                    if ((((GofishCard) (cards.get(i))).getRank())
                            .equals(((GofishCard) (((GofishPlayer) game.getPlayers().get(availablePlayerNums.get(j))).getMemory().getCardsReceived().get(k))).getRank())) {
                        matchLevel += 4;//Here can decide which card is more import of booklevel between received cards and asked cards
                    }
                }
                for (int k = 0; k < ((GofishPlayer) game.getPlayers().get(availablePlayerNums.get(j))).getMemory().getCardsAsked().size(); k++) {
                    if ((((GofishCard) (cards.get(i))).getRank())
                            .equals(((GofishCard) (((GofishPlayer) game.getPlayers().get(availablePlayerNums.get(j))).getMemory().getCardsAsked().get(k))).getRank())) {
                        matchLevel += 2;//Here can decide which card is more import of booklevel between received cards and asked cards
                    }
                }
                matchLevels.add(matchLevel * counts.get(i));
            }

            matchPlayers.add((GofishPlayer) game.getPlayers().get(availablePlayerNums.get(matchLevels.indexOf(Collections.max(matchLevels)))));
            playerMatchLevels.add(Collections.max(matchLevels));
        }

        //sort the Cards,playerMatchLevels list and matchPlayers list by the descent order of matching level
        int temp;
        GofishPlayer tempPlayer;
        GofishCard tempCard;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i; j < cards.size(); j++) {
                if (playerMatchLevels.get(i) > playerMatchLevels.get(j)) {

                    temp = playerMatchLevels.get(i);
                    playerMatchLevels.set(i, playerMatchLevels.get(j));
                    playerMatchLevels.set(j, temp);

                    tempPlayer = (GofishPlayer) matchPlayers.get(i);
                    matchPlayers.set(i, (GofishPlayer) (matchPlayers.get(j)));
                    matchPlayers.set(j, tempPlayer);

                    tempCard = cards.get(i);
                    cards.set(i, cards.get(j));
                    cards.set(j, tempCard);
                }
            }
        }

        //based on the skill level of the computerPlayer, make a possibility list to hold weighted booking leve of each card in handCards
        Random random = new Random();
        ComputerPlayer.Level[] levels = ComputerPlayer.Level.values();
        ArrayList<Double> possibilitys = new ArrayList<>();
        double posibility;

        // make a possibility list to apply better choice for higher skill level computer player
        for (int i = 0; i < levels.length; i++) {
            if (level == levels[i]) {
                for (int j = 1; j < cards.size() + 1; j++) {
                    posibility = random.nextDouble() * Math.pow(j, (i*4));//by ajust the value of i to increase the smart level,eg.Math.pow(j,i*6)
                    possibilitys.add(posibility);

                }
            }
        }

        //find the card with highest posibility level,set it to askingCard
        this.setAskingCard((GofishCard) cards.get(possibilitys.indexOf(Collections.max(possibilitys))));

        //return which player to ask for card
        return matchPlayers.get(possibilitys.indexOf(Collections.max(possibilitys)));
    }
}
