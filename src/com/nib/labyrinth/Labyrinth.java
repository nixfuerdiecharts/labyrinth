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
     * Labyrinth dimensions set by user
     */
    private int z, y, x;

    /**
     * Constructor with initialization of nested LinkedHashMap
     */
    public Labyrinth() {
        this.labyrinth = new LinkedHashMap<>();
    }

    public static void main(String[] args) {
        //Labyrinth structure
        Labyrinth labyrinth = new Labyrinth();
        //Labyrinth solver
        LabyrinthSolver labyrinthSolver = new LabyrinthSolver();
        //User input handling
        UserInputHandler userInputHandler = new UserInputHandler(labyrinth);

        //Handling of user input for labyrinth structure
        userInputHandler.readLabyrinth();
    }

    // ***********************************
    // Setter and Getter
    // ***********************************
    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // ***********************************
    // Printer methods
    // ***********************************
    /**
     * Printer method to print user input rules for labyrinth dimensions
     */
    static void printLabyrinthDimensionRules() {
        System.out.println("Please insert amount of levels, rows, columns (e.g. 3 4 5) and press enter");
        System.out.printf("All three input must be between %d and %d", UserInputHandler.minDimension, UserInputHandler.maxDimension);
    }

    /**
     * Printer method to print user input rules for labyrinth structure
     */
    static void printLabyrinthStructureRules() {

    }

    /**
     * Printer Methode to print the levels of the labrinth on screen
     */
    public void printLabyrinth() {
        //Check if Labyrinth is not set yet
        if (this.labyrinth.isEmpty()) {
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
