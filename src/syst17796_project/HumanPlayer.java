package syst17796_project;

import java.util.Scanner;

/**
 * This is a human player class extended all the data fields and methods from GofishPlayer.
 * @author Changxin sun/Junxiu Ma/Yuanlong Sun Feb 2020
 */
public class HumanPlayer extends GofishPlayer {

    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }
}
