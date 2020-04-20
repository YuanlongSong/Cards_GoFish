package syst17796_project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main class to play a GOFISH poker game. It is the view part of a MVC design mode.
 *
 * @author Changxin Sun
 * @author Junxiu Ma
 * @author Yuanlong Song
 */
public class SYST17796_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // have a boolean variable as answer of having card from opponent
        boolean hasNewGame = true;
        boolean hasSameTurn;
        GofishCard askedCard;
        Scanner input = new Scanner(System.in);

        while (hasNewGame) {
            // create a new game
            System.out.print("Input your game name:  ");
            GofishGame game = new GofishGame(inputValidString(2, 20));

            System.out.println();

            // add players to the game and show the play turn
            // choose how many players and then know how many cards to deal to each player
            System.out.print("Choose quantity of players:  ");
            int playerQty = inputValid(game.getMinPlayer(), game.getMaxPlayer());
            System.out.println();

            // choose how many human players and set names
            System.out.print("How many human players?  ");
            int humanPlayerQty = inputValid(1, playerQty);
            ArrayList<String> humanPlayerNames = new ArrayList<>();
            for (int i = 0; i < humanPlayerQty; i++) {
                System.out.print("enter the human player " + (i + 1) + " name: ");
                humanPlayerNames.add(inputValidString(2, 10));
            }
            System.out.println();

            //set the ComputerPlayers skill level
            ArrayList<ComputerPlayer.Level> computerPlayerLevels = new ArrayList<>();
            for (int i = 0; i < playerQty - humanPlayerQty; i++) {
                System.out.print("enter the skill level of computer player(between 1-5):  ");
                computerPlayerLevels.add((ComputerPlayer.Level.values())[inputValid(1, 5) - 1]);
            }
            System.out.println();

            //add players to the game
            game.addPlayers(playerQty, humanPlayerQty, humanPlayerNames, computerPlayerLevels);
            System.out.println();

            // decide and show the play turn
            game.decideTurn();
            System.out.println("The play turn is as below");
            for (int i = 0; i < game.getPlayers().size(); i++) {
                System.out.println("   " + (i + 1) + " : " + ((GofishPlayer) game.getPlayers().get(i)));
            }
            System.out.println();

            // add cards to deck and shuffle the cards
            System.out.println("Now setup deck and shuffle cards");
            game.addDeckCards();

            // deal cards to players
            System.out.println("Now deals cards");
            game.dealCards();
            System.out.println();

            System.out.println("#################################################################################");
            System.out.println("Press any key to start");
            input.nextLine();

            System.out.println("NOW *********** STARR");
            System.out.println("#################################################################################");

            //use hasNewRound to start a new round or stop this game
            boolean hasNewRound = true;
            while (hasNewRound) {
                //use for-loop to make players play by turn
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    hasSameTurn = true;
                    while (hasSameTurn && hasNewRound) {
                        if (((GofishPlayer) game.getPlayers().get(i)).getActive()) {

                            //player in i position will play card. 
                            GofishPlayer askingPlayer = (GofishPlayer) game.getPlayers().get(i);

                            //computerPlayer ask card
                            if (askingPlayer instanceof ComputerPlayer) {

                                //ask player to confirm to play your turn
                                System.out.println("*************************************************************");
                                System.out.println();

                                //-----------------------------------------draw card for asking player with empty handCards------------
                                //if asking player has no handCards,draw a card
                                if (askingPlayer.getHandCards().getCards().isEmpty()) {

                                    System.out.println("Before the askingPlayer draw a card, the deckCards:");
                                    for (int j = 0; j < game.getDeckCards().getCards().size(); j++) {
                                        System.out.println("DeckCard " + j + ":" + game.getDeckCards().getCards().get(j));
                                    }

                                    GofishCard drawCard = game.getDeckCards().getCards().remove(0);

                                    System.out.println("Before the askingPlayer draw a card, the handCards:");
                                    for (int j = 0; j < askingPlayer.getHandCards().getCards().size(); j++) {
                                        System.out.println("handCard " + j + ":" + askingPlayer.getHandCards().getCards().get(j));
                                    }

                                    askingPlayer.getHandCards().getCards().add(drawCard);

                                    System.out.println("The card drawed is :" + drawCard);

                                    System.out.println("After the askingPlayer draw a card, the deckCards:");
                                    for (int j = 0; j < game.getDeckCards().getCards().size(); j++) {
                                        System.out.println("DeckCard " + j + ":" + game.getDeckCards().getCards().get(j));
                                    }

                                    System.out.println("After the askingPlayer draw a card, the handCards:");
                                    for (int j = 0; j < askingPlayer.getHandCards().getCards().size(); j++) {
                                        System.out.println("handCard " + j + ":" + askingPlayer.getHandCards().getCards().get(j));
                                    }
                                }
                                //end-----------------------------------------draw card for asking player with empty handCards------------

                                //After drawing a card,check and set potential inactive player
                                checkAndSetInactive(game);

                                                                //decide who to ask
                                System.out.println("Now it's " + askingPlayer + "'s turn:");
                                System.out.println();
                                GofishPlayer askedPlayer = ((ComputerPlayer) askingPlayer).askWho(game);
                                System.out.println();
                                
                                //ask for a Card as askCard
                                askedCard = (GofishCard) (askingPlayer.askCard());
                                System.out.println(((ComputerPlayer) askingPlayer) + "  is asking  " + askedPlayer + " for Card : " + askedCard);
                                System.out.println("*************************************************************");
                                System.out.println("press any key to continue");
                                input.nextLine();

                                //continue the turn or finish the turn
                                hasSameTurn = game.playAskCard(askingPlayer, askedPlayer, askedCard);

                            } //humanPlayer ask card
                            else {

                                //ask player to confirm to play your turn
                                System.out.println("*************************************************************");
                                System.out.println(game.getPlayers().get(i) + ", Do you want to start asking card?   Press any key to start");
                                input.nextLine();

                                //if asking player has no handCards,draw a card
                                if (askingPlayer.getHandCards().getCards().isEmpty()) {
                                    askingPlayer.getHandCards().getCards().add(game.getDeckCards().getCards().remove(0));
                                }

                                //After drawing a card,check and set potential inactive player
                                checkAndSetInactive(game);

                                //decide who to ask
                                System.out.println("Now it's " + askingPlayer + "'s turn:");

                                //show cards in your hand
                                System.out.println("    Cards in your hand:");
                                for (int j = 0; j < askingPlayer.getHandCards().getCards().size(); j++) {
                                    System.out.println("        Card " + (j + 1) + " : " + askingPlayer.getHandCards().getCards().get(j));
                                }

                                System.out.println("    choose a player to ask card from below players:");
                                //show available players to be asked
                                ArrayList<Integer> availablePlayerNums = new ArrayList<>();
                                int k = 1;
                                for (int j = 0; j < game.getPlayers().size(); j++) {
                                    if (!game.getPlayers().get(j).equals(askingPlayer) && ((GofishPlayer) game.getPlayers().get(j)).getActive()) {
                                        System.out.println("        Player number " + (k++) + " : " + game.getPlayers().get(j));
                                        availablePlayerNums.add(j);
                                    }
                                }

                                //num is the index of the askedPlayer in players
                                System.out.print("Enter player number:");
                                int num;
                                num = inputValid(1, k - 1) - 1;
                                GofishPlayer askedPlayer = (GofishPlayer) game.getPlayers().get(availablePlayerNums.get(num));

                                //ask for a Card
                                System.out.print("Enter card number:");
                                askedCard = askingPlayer.getHandCards().getCards().get(inputValid(1, askingPlayer.getHandCards().getCards().size()) - 1);
                                System.out.println(askingPlayer + "  is asking  " + askedPlayer + " for card: " + askedCard);
                                System.out.println("*************************************************************");
                                System.out.println();

                                //continue the turn or finish the turn
                                hasSameTurn = game.playAskCard(askingPlayer, askedPlayer, askedCard);
                            }

                        } else {
                            hasSameTurn = false;
                        }
                    }//this the end of while(hasSameTurn&&hasNewRound)

                    //if this round finish,set i =-1 t start a new round
                    if (i == game.getPlayers().size() - 1) {
                        i = -1;
                    }
                    //check if all player are inactive
                    boolean gameConinue = false;
                    for (int j = 0; j < game.getPlayers().size(); j++) {
                        gameConinue = gameConinue || (((GofishPlayer) game.getPlayers().get(j)).getHandCards().getCards().size() > 0);
                    }

                    //if all players are inactive, set i larger than size to stop this round and set hasNewRound as false to stop this game
                    if (!gameConinue) {
                        i = game.getPlayers().size();
                        hasNewRound = false;
                    } else {
                    }
                }//this the end of for-loop of keeping playing by turn in one round
            }//this is the end of while(hasNewRound)
            //declare winner
            game.declareWinner();

            // prompt player to play again
            System.out.println("Do you want to play again?");
            if (input.nextLine().equalsIgnoreCase("no")) {
                hasNewGame = false;
            }
        }//this is the end of while(hasNewGame)

        // end the game
        System.out.println(
                "**************************************************************");
        System.out.println(
                "End game");
        System.out.println(
                "**************************************************************");
    }

    /**
     *
     * @param game
     */
    public static void checkAndSetInactive(GofishGame game) {
        if (game.getDeckCards().getCards().isEmpty()) {
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (((GofishPlayer) game.getPlayers().get(i)).getHandCards().getCards().isEmpty()) {
                    ((GofishPlayer) game.getPlayers().get(i)).setActive(false);
                }
            }
        }
    }

    /**
     * use to input valid number between min and max
     *
     * @param min
     * @param max
     * @return
     */
    public static int inputValid(int min, int max) {
        int num;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                num = input.nextInt();
                if (num >= min && num <= max) {
                    break;
                }
            } catch (Exception e) {

            }
            System.out.println("Input a valid number between " + min + " and " + max);
            input.nextLine();
        }
        return num;
    }

    /**
     * use to input valid length String
     *
     * @param min
     * @param max
     * @return
     */
    public static String inputValidString(int min, int max) {

        Scanner input = new Scanner(System.in);
        String str;
        while (true) {
            str = input.nextLine();
            if (str.length() >= min && str.length() <= max) {
                break;
            }
            System.out.println("Input a valid string with length between " + min + " and " + max);
        }
        return str;
    }
}
