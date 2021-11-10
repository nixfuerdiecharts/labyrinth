package com.nib.labyrinth;

import java.util.Scanner;

public class UserInputHandler {
    /**
     * Minimum dimensions for labyrinth
     */
    public static int minDimension = 0;
    /**
     * Maximum dimensions for labyrinth
     */
    public static int maxDimension = 30;
    /**
     * Reference to labyrinth
     *
     * @see Labyrinth
     */
    private final Labyrinth labyrinth;
    /**
     * Variable to check if start point was set
     */
    private boolean startSet;
    /**
     * Variable to check if end point was set
     */
    private boolean endSet;

    /**
     * Constructor for User input handler
     *
     * @param labyrinth Reference to labyrinth object
     */
    public UserInputHandler(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        this.startSet = false;
        this.endSet = false;
    }

    // ***********************************
    // User input handling and validation
    // ***********************************
    /**
     * Method to handle the user inputs for the dimensions
     *
     * @return True if the dimensions allow another labyrinth structure. False if user does not want to give more labyrinths to the program
     */
    public boolean readDimensions() {
        boolean result = true;
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



        //Check if end condition of input is reached by the user inputs
        if (labyrinth.getZ() == 0 && labyrinth.getY() == 0 && labyrinth.getX() == 0) {
            result = false;
        }

        return result;
    }

    /**
     * Method to handle the user input for the labyrinth structure
     */
    public void readLabyrinth() {
        //Scanner for reading user inputs
        Scanner scanner = new Scanner(System.in);

        //Read labyrinth structure line by line for every level in the labyrinth
        UserInputHandler.clearScreen();
        Labyrinth.printLabyrinthStructureRules();
        String labyrinthLine;
        for (int z = 0; z < labyrinth.getZ(); z++) {
            for (int y = 0; y < labyrinth.getY(); y++) {
                do {
                    System.out.printf("Please insert %d. line of the %d. level in the Labyrinth%n", y + 1, z + 1);
                    labyrinthLine = "";
                    try {
                        labyrinthLine = scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error while reading labyrinth structure");
                        Labyrinth.printLabyrinthStructureRules();
                    }
                } while (!parseLabyrinthStructure(labyrinthLine, z, y) || labyrinthLine.isEmpty());
            }
        }


        //After everything is read, clear screen for solver output
        UserInputHandler.clearScreen();
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
                    break;
                } else if (!(Integer.parseInt(s.trim()) >= minDimension) || !(Integer.parseInt(s.trim()) <= maxDimension)) {
                    //Check if all numeric strings are within the dimension rules
                    result = false;
                    break;
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
     *
     * @param labyrinthLine A structure line for the labyrinth from user input
     * @return True if the user input corresponds to the input rules
     */
    private boolean parseLabyrinthStructure(String labyrinthLine, int z, int y) {
        boolean result = true;

        //Check if length is the given dimension from earlier
        if (labyrinthLine.length() != labyrinth.getX()) {
            result = false;
            System.out.printf("The given dimensions from earlier only allow %d character(s) per line%n", labyrinth.getX());
            Labyrinth.printLabyrinthStructureRules();
        }

        //Check if all characters are ".", "#", "S"/"s" or "E"/"e"
        for (char c : labyrinthLine.toCharArray()) {
            if (c != '.' && c != '#' && c != 's' && c != 'S' && c != 'e' && c != 'E') {
                result = false;
                break;
            } else if (c == 's' || c == 'S') {
                //Check if start point was already set
                if (startSet) {
                    result = false;
                    System.out.println("Start point was already set. There can only be one start point");
                } else {
                    startSet = true;
                }
            } else if (c == 'e' || c == 'E') {
                //Check if end point was already set
                if (endSet) {
                    result = false;
                    System.out.println("End point was already set. There can only be one end point");
                } else {
                    endSet = true;
                }
            }
        }

        //Line corresponds to the rules --> parse
        if (result) {
            for (int x = 0; x < labyrinthLine.length(); x++) {
                switch (labyrinthLine.charAt(x)) {
                    case '.' -> labyrinth.setPoint(z, y, x, '.');
                    case '#' -> labyrinth.setPoint(z, y, x, '#');
                    case 's', 'S' -> labyrinth.setPoint(z, y, x, 'S');
                    case 'e', 'E' -> labyrinth.setPoint(z, y, x, 'E');
                    default -> result = false;
                }
            }
        }

        return result;
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

    // ***********************************
    // Getters and Setters
    // ***********************************

    /**
     * Getter for labyrinth
     *
     * @return Labyrinth
     */
    public Labyrinth getLabyrinth() {
        return labyrinth;
    }
}
