package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {

    ArrayList<String> equations = new ArrayList<>();

    public Algorithm() {
    }

    // reads in previously generated file of all possible Nerdle equations
    public void generateEqList() throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/ishaansareen/Documents/GitHub/final-project-choi-ishdafish24/nerdle_wordlist.txt"));
        while (s.hasNextLine()) {
            equations.add(s.nextLine());
        }
        s.close();
    }


    public ArrayList<String> returnEqList() {
        return equations;
    }

    // removes equations from the list that contain characters that were marked as black
    public void removeBlackEquations(ArrayList<String> blackChars) {
        for (int i = 0; i < blackChars.size(); i++) {
            for (int j = 0; j < equations.size(); j++) {
                String[] str = equations.get(j).split("");
                for (int k = 0; k < str.length; k++) {
                    if (str[k].equals(blackChars.get(i))) {
                        equations.remove(j);
                        j--;
                        break;
                    }
                }
            }
        }
    }

    // removes all equations that didn't have the green characters in the correct place
    public void removeGreenEquations(ArrayList<String> greenChars) {
        for (int i = 0; i < greenChars.size(); i += 2) {
            for (int j = 0; j < equations.size(); j++) {
                String[] str = equations.get(j).split("");
                if (!(str[Integer.parseInt(greenChars.get(i + 1)) % 8].equals(greenChars.get(i)))) {
                    equations.remove(j);
                    j--;
                }
            }
        }
    }

    // removes equations which had the purple characters in the wrong place
    public void removePurpleEquations(ArrayList<String> purpleChars) {
        for (int i = 0; i < purpleChars.size(); i += 2) {
            for (int j = 0; j < equations.size(); j++) {
                String[] str = equations.get(j).split("");
                if ((str[Integer.parseInt(purpleChars.get(i + 1)) % 8].equals(purpleChars.get(i)))) {
                    equations.remove(j);
                    j--;
                }
            }
        }
    }
}






