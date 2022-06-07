

package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    String html = "";
    String[] colors = new String[16];
    String[] placements = {"9","*","8","-","7","=","6","5","0","+","1","2","/","3","=", "4"};
    Document doc = Jsoup.parse(html);

    public Parser(){}


    public static void main(String[] args) throws IOException {
    }

    // Uses JSoup to manipulate html into a parseable state
    public void setHTMLandDoc(String s){
        html = s;
        doc = Jsoup.parse(html);
    }

    // after the initial guesses are placed, method stores all the tiles' colors in an array
    public void determineInitialColors(){
        Elements grid = doc.select("#root > div > div.pb-grid");
        String s = grid.html();
        int i = 0;
        int object = 0;
        while (i < s.length()){
            if (s.substring(i,i+5).equals("bg-[#")){
                colors[object] = s.substring(i+4,i+10);
                object++;
            }
            i++;
            if (object == 16 || i == s.length()-4){
                break;
            }
        }
    }

    // finds which characters are marked as black
    public ArrayList<String> findBlackCharacters(){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("#16180")){
                indexes.add(i);
            }
        }

        ArrayList<String> ret = new ArrayList<>();
        for (Integer i : indexes){
            ret.add(placements[i]);
        }
        return ret;
    }

    // finds characters marked as green and stores their respective location
    public ArrayList<String> findGreenCharacters(){
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Integer> keys = new ArrayList<>();


        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("#39887")){
                keys.add(i);
            }
        }
        for (Integer in : keys){
            values.add(placements[in]);
        }


        ArrayList<String> combination = new ArrayList<>(); // array storing both keys and values
        int location = 0;
        while (location < keys.size()){
            combination.add(values.get(location)); // values
            combination.add(String.valueOf(keys.get(location))); // keys
            location++;
        }
        return combination;
    }

    // finds characters marked as purple and stores their respective location
    public ArrayList<String> findPurpleCharacters() {
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Integer> keys = new ArrayList<>();


        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("#82045")){
                keys.add(i);
            }
        }
        for (Integer in : keys){
            values.add(placements[in]);
        }


        ArrayList<String> combination = new ArrayList<>(); // stores both keys and values
        int location = 0;
        while (location < keys.size()){
            combination.add(values.get(location)); // values
            combination.add(String.valueOf(keys.get(location))); // keys
            location++;
        }
        return combination;
    }

}


