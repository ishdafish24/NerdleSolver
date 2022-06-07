package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // default guesses || use every character
        String primaryGuess = "9*8-7=65";
        String[] splitPrimary = primaryGuess.split("");
        String secondaryGuess = "0+12/3=4";
        String[] splitSecondary = secondaryGuess.split("");

        // instantiates necessary classes
        Automation automator = new Automation();
        Algorithm algorithm = new Algorithm();
        Parser parser = new Parser();

        // sets up automation
        automator.setup();
        automator.launchApp();
        automator.clearCookies();
        automator.createBoard();

        // deploys first equation and cuts down equation list based on parsed html
        automator.writeEquation(splitPrimary[0],splitPrimary[1],splitPrimary[2],splitPrimary[3],splitPrimary[4],splitPrimary[5],splitPrimary[6],splitPrimary[7]);
        automator.deployEquation();
        automator.extractHTML(parser);
        algorithm.generateEqList();

        // deploys second equation
        automator.writeEquation(splitSecondary[0],splitSecondary[1],splitSecondary[2],splitSecondary[3],splitSecondary[4],splitSecondary[5],splitSecondary[6],splitSecondary[7]);
        automator.deployEquation();
        automator.extractHTML(parser);

        // parses static instance of website to eliminate equations
        parser.determineInitialColors();
        algorithm.removeBlackEquations(parser.findBlackCharacters());
        algorithm.removeGreenEquations(parser.findGreenCharacters());
        algorithm.removePurpleEquations(parser.findPurpleCharacters());

        // deploys final guess
        ArrayList<String> thirdGuess = algorithm.returnEqList();
        String g = thirdGuess.get(0);
        String[] gSplit = g.split("");
        automator.writeEquation(gSplit[0], gSplit[1],gSplit[2],gSplit[3],gSplit[4],gSplit[5],gSplit[6],gSplit[7]);
        automator.deployEquation();
    }

}


