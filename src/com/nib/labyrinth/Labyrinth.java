package com.nib.labyrinth;

import java.util.LinkedHashMap;

/**
 * Class to capsule labyrinth functionalities
 */
public class Labyrinth {
    /**
     * Nested LinkedHashMap for storing z,y,x and content of each 3d point in 3d labyrinth
     */
    private LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<Integer, String>>> labyrinth;

    /**
     * Constructor with initialization of nested LinkedHashMap
     */
    public Labyrinth() {
        this.labyrinth = new LinkedHashMap<>();
    }

    /**
     * Printer Methode to print the levels of the labrinth on screen
     */
    public void printLabyrinth() {
        if(this.labyrinth.isEmpty()) {
            System.out.print("Labyrinth is not set up yet");
            return;
        }

        for (int z : labyrinth.keySet()) {
            for (int y : labyrinth.get(z).keySet()) {
                for (int x : labyrinth.get(z).get(y).keySet()) {
                    System.out.printf("%s", labyrinth.get(z).get(y).get(x));
                }
                System.out.printf("%n");
            }
            System.out.printf("%n");
        }
    }

}
