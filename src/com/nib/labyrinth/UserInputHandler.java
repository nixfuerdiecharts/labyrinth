package com.nib.labyrinth;

import java.util.Scanner;

public class UserInputHandler {
    /**
     * Minimum dimensions for labyrinth
     */
    public static int minDimension = 1;
    /**
     * Maximum dimensions for labyrinth
     */
    public static int maxDimension = 30;
    /**
     * Reference to labyrinth
     * @see Labyrinth
     */
    private final Labyrinth labyrinth;

    /**
     * Constructor for User input handler
     * @param labyrinth Reference to labyrinth object
     */
    public UserInputHandler(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    // ***********************************
    // User input handling and validation
    // ***********************************
    /**
     * Input handler for user input
     */
    public void readLabyrinth() {
        //Scanner for reading user inputs
        Scanner scanner = new Scanner(System.in);
        String dimensions;

        //Read labyrinth dimensions
        Labyrinth.printLabyrinthDimensionRules();
        do {
            dimensions = "";
            try {
                dimensions = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error while reading labyrinth dimensions");
                Labyrinth.printLabyrinthDimensionRules();
            }
        } while (!parseLabyrinthDimensions(dimensions) || dimensions.isEmpty());

        //Read labyrinth structure line by line for every level in the labyrinth
        UserInputHandler.clearScreen();
        Labyrinth.printLabyrinthStructureRules();
        String labyrinthLine;
        for (int z = 1; z <= labyrinth.getZ(); z++) {
            for (int y = 1; y <= labyrinth.getY(); y++) {
                System.out.printf("Please insert %d. line of the %d. level%n in the Labyrinth (e.g. #.S.#)", y, z);
                do {
                    labyrinthLine = "";
                    try {
                        labyrinthLine = scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error while reading labyrinth structure");
                        Labyrinth.printLabyrinthStructureRules();
                    }
                } while (!parseLabyrinthStructure(labyrinthLine) || labyrinthLine.isEmpty());
            }
        }

        UserInputHandler.clearScreen();
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
     * Method to parse labyrinth dimensions from a given input
     *
     * @param dimensions String containing labyrinth dimensions
     * @return True if parsing of the dimensions was successful
     */
    private boolean parseLabyrinthDimensions(String dimensions) {
        boolean result = true;
        //Split single user input into strings by space
        String[] split = dimensions.split("\\s+");

        //Check if there are 3 separated strings
        if (split.length != 3) {
            result = false;
            Labyrinth.printLabyrinthDimensionRules();
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
            labyrinth.setZ(Integer.parseInt(split[0].trim()));
            labyrinth.setY(Integer.parseInt(split[1].trim()));
            labyrinth.setX(Integer.parseInt(split[2].trim()));
        }

        return result;
    }

    /**
     * Method to parse labyrinth structure from a given input
     * @param labyrinthLine A structure line for the labyrinth from user input
     * @return True if the user input corresponds to the input rules
     */
    private boolean parseLabyrinthStructure(String labyrinthLine) {
        boolean result = true;


        return true;
    }

    // ***********************************
    // Printer methods
    // ***********************************
    /**
     * Helper method to clear console screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
