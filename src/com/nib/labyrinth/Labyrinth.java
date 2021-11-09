package com.nib.labyrinth;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Class to capsule labyrinth functionalities
 */
public class Labyrinth {
    public static int minDimension = 1;
    public static int maxDimension = 30;
    /**
     * Nested LinkedHashMap for storing z,y,x and content of each 3d point in 3d labyrinth
     */
    private LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<Integer, String>>> labyrinth;
    /**
     * Dimensions set by user
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

        //Handling of user input for labyrinth structure
        readLabyrinth(labyrinth);


    }

    /**
     * Input handler for user input
     */
    public static void readLabyrinth(Labyrinth labyrinth) {
        //Scanner for reading user inputs
        Scanner scanner = new Scanner(System.in);
        String dimensions;

        //Read labyrinth dimensions
        printDimensionRules();
        do {
            dimensions = "";
            try {
                dimensions = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error while reading labyrinth dimensions");
                printDimensionRules();
            }
        } while (!labyrinth.parseDimensions(dimensions) || dimensions.isEmpty());

        //Read labyrinth structure
        for (int z = 0; z < labyrinth.z; z++) {
            for (int y = 0; y < labyrinth.y; y++) {
                //TODO: read line by line for every level
            }
        }

        clearScreen();
    }

    /**
     * Method to examinate whether a string is numeric or not
     *
     * @param s string to check whether it's numeric or not
     * @return true if string is numeric
     */
    private static boolean isNumeric(String s) {
        boolean result = true;

        if (s.isEmpty()) {
            result = false;
        } else if (s.charAt(0) == '-' && s.length() == 1) {
            result = false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * Printer method to print user input rules for labyrinth dimensions
     */
    private static void printDimensionRules() {
        System.out.println("Please insert amount of levels, rows, columns (e.g. 3 4 5) and press enter");
        System.out.println("All three input must be between 0 and 30");
    }

    /**
     * Helper method to clear console screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Method to parse labyrinth dimensions from a given input
     *
     * @param dimensions String containing labyrinth dimensions
     * @return True if parsing of the dimensions was successful
     */
    private boolean parseDimensions(String dimensions) {
        boolean result = true;
        //Split single user input into strings by space
        String[] split = dimensions.split("\\s+");

        //Check if there are 3 separated strings
        if (split.length != 3) {
            result = false;
            printDimensionRules();
        } else {
            //Check if all 3 strings are numeric
            for (String s : split) {
                if (!isNumeric(s.trim())) {
                    result = false;
                } else if (!(Integer.parseInt(s.trim()) >= minDimension) || !(Integer.parseInt(s.trim()) <= maxDimension)) {
                    result = false;
                }
            }
        }

        if (result) {
            this.z = Integer.parseInt(split[0].trim());
            this.y = Integer.parseInt(split[1].trim());
            this.x = Integer.parseInt(split[2].trim());
        }

        return result;
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
