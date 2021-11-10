package com.nib.labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to capsule labyrinth functionalities
 */
public class Labyrinth {
    /**
     * 3d character array for representing the labyrinth structure
     */
    private final Character[][][] labyrinthStructure;
    /**
     * Labyrinth dimensions set by user
     */
    private int z, y, x;

    /**
     * Constructor with initialization of nested LinkedHashMap
     */
    public Labyrinth() {
        this.labyrinthStructure = new Character[UserInputHandler.maxDimension][UserInputHandler.maxDimension][UserInputHandler.maxDimension];
    }

    /**
     * Main method with console logic for user input of multiple labyrinths and labyrinth solving
     *
     * @param args No Args needed
     */
    public static void main(String[] args) {
        //Labyrinth structure
        Labyrinth labyrinth = new Labyrinth();
        //Labyrinths
        List<Labyrinth> labyrinthList = new ArrayList<>();
        //Labyrinth solver
        LabyrinthSolver labyrinthSolver = new LabyrinthSolver();
        //User input handling


        //Handling of user input for labyrinth structure
        boolean goOn = true;
        while (goOn) {
            //clear screen because there could have been a previous input
            UserInputHandler.clearScreen();
            UserInputHandler userInputHandler = new UserInputHandler(new Labyrinth());
            goOn = userInputHandler.readDimensions();
            if (goOn) {
                userInputHandler.readLabyrinth();
                labyrinthList.add(userInputHandler.getLabyrinth());
            }
        }

        //Solve all Labyrinths
        labyrinthSolver.setLabyrinths(labyrinthList);
        labyrinthSolver.solveAllLabyrinths();
    }

    // ***********************************
    // Setter and Getter
    // ***********************************

    /**
     * Getter for dimension Z --> amount of levels
     *
     * @return dimension Z
     */
    public int getZ() {
        return z;
    }

    /**
     * Setter for dimension Z
     *
     * @param z value of dimension Z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Getter for dimension y --> amount of lines per level
     *
     * @return dimension Y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for dimension Y
     *
     * @param y value of dimension Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for dimension X --> amount of characters per line
     *
     * @return dimension X
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for dimension X
     *
     * @param x value of dimension X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for a 3d point in the labyrint
     *
     * @param z value of dimension z
     * @param y value of dimension y
     * @param x value of dimension x
     * @param c character (Element) placed in x, y, z in the labyrinth
     */
    public void setPoint(int z, int y, int x, char c) {
        labyrinthStructure[z][y][x] = c;
    }

    /**
     * Getter for a point in the labyrinthStructure
     *
     * @param z Level
     * @param y Row
     * @param x Column
     * @return Character that is in the labyrinthstructure on x, y, z
     */
    public Character getPoint(int z, int y, int x) {
        //Prevent index out of Bounds
        if (z < 0 || y < 0 || x < 0 || z > this.z || y > this.y || x > this.x) {
            return null;
        } else {
            return labyrinthStructure[z][y][x];
        }
    }

    /**
     * Helper Function for setting up the whole labyrinth at once via a String text block
     * Assuming that only valid labyrinths land here
     * Needed for Unit Testing
     *
     * @param textBlock Whole labyrinth structure as String text block
     */
    public void setLabyrinthByTextBlock(String textBlock) {
        //Assumption that only valid labyrinths land here
        Scanner scanner = new Scanner(textBlock);
        //Dimensions
        int z = 0, y = 0, x = 0;

        //Read line by line, fill the labyrinth and the read the dimensions out of the structure
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);

            //Check if line is empty --> new level reached
            if (line.trim().isEmpty()) {
                z++;

                //Check if y is not set yet. Since only valid labyrinths reach this method
                //--> after first level the y dimension is detected


                continue;
            }

            //Check if dimension x is not set yet
            if (x == 0) {
                x = line.trim().length();
            }
        }
        //Set the dimensions read by the text block
        this.setX(x);
        this.setY(y);
        this.setZ(z);

        scanner.close();
    }

    // ***********************************
    // Printer methods
    // ***********************************

    /**
     * Printer method to print user input rules for labyrinth dimensions
     */
    static void printLabyrinthDimensionRules() {
        System.out.println("Please insert amount of levels, rows, columns (e.g. 3 4 5) and press enter");
        System.out.printf("All three input must be between %d and %d%n", UserInputHandler.minDimension, UserInputHandler.maxDimension);
    }

    /**
     * Printer method to print user input rules for labyrinth structure
     */
    static void printLabyrinthStructureRules() {
        System.out.println("Please insert the labyrinth Structure");
        System.out.println("Inputs allowed are '.' (Air), '#' (Stone), 'S' (Start), 'E' (End)");
    }

    /**
     * Printer Methode to print the levels of the labrinth on screen
     */
    public void printLabyrinth() {
        //Check if Labyrinth is not set yet
        if (this.labyrinthStructure[0][0][0] == null) {
            System.out.print("Labyrinth is not set up yet");
            return;
        }

        for (int z = 0; z < this.z; z++) {
            for (int y = 0; y < this.y; y++) {
                for (int x = 0; x < this.x; x++) {
                    System.out.print(labyrinthStructure[z][y][x]);
                }
                System.out.printf("%n");
            }
            System.out.printf("%n%n");
        }
    }
}
