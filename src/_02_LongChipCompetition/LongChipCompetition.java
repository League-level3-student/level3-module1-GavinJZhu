package _02_LongChipCompetition;

import java.util.ArrayList;
import java.util.Random;

public class LongChipCompetition {
    /*
     * The Beatles are eating lunch and playing a game to see who has the
     * longest chip. (In England, french fries are called "chips".)
     * Find the Beatle with the longest chip. You may not edit the Chip or
     * Beatle classes. Make sure to initialize The Beatles before you start
     * your search.
     */
    private ArrayList<Beatle> theBeatles = new ArrayList<Beatle>();

    public static void main(String[] args) {
        LongChipCompetition lcc = new LongChipCompetition();
        String beatleName = "Hopefully you don't see this in the console!!";
        double largestChip = 0;
        lcc.initializeBeatles();
        for (int i = 0; i < lcc.theBeatles.size(); i++) {
            for (Chip chip : lcc.theBeatles.get(i).getChips()){
                if (chip.getLength() > largestChip){
                    largestChip = chip.getLength();
                    beatleName = lcc.theBeatles.get(i).getName();
                }
            }
        }
        System.out.println(beatleName+ " has the largest chip, at a whopping size of "+largestChip+"!");
    }

    private void initializeBeatles() {
        Beatle george = new Beatle("George");
        Beatle john = new Beatle("John");
        Beatle paul = new Beatle("Paul");
        Beatle ringo = new Beatle("Ringo");
        theBeatles.add(george);
        theBeatles.add(john);
        theBeatles.add(paul);
        theBeatles.add(ringo);
    }

    public ArrayList<Beatle> getTheBand(){
        return theBeatles;
    }
}

class Beatle {
    private String name;
    private ArrayList<Chip> chips = new ArrayList<Chip>();

    public Beatle(String name) {
        this.name = name;
        initializePlateOfChips();
    }

    private void initializePlateOfChips() {
        int numberOfChips = new Random().nextInt(100);
        double largestChip = 0;
        for (int i = 0; i < numberOfChips; i++) {
            double randomChip = new Random().nextDouble() * 10;
            Chip newChip = new Chip(randomChip);
            chips.add(newChip);
            if (newChip.getLength() > largestChip){
                largestChip = newChip.getLength();
            }
        }
        System.out.println("Largest Chip's Size: "+largestChip);
    }

    public ArrayList<Chip> getChips() {
        return this.chips;
    }

    public String getName() {
        return this.name;
    }
}

class Chip {
    private double length;

    public double getLength() {
        return length;
    }

    Chip(double d) {
        this.length = d;
    }
}
