package com.nib.labyrinth;

/**
 * Class to capsule labyrinth functionalities
 */
public class Labyrinth {


    /**
     * 3d character array for representing the labyrinth structure
     */
    private Character[][][] labyrinth;
    /**
     * Labyrinth dimensions set by user
     */
    private int z, y, x;

    /**
     * Constructor with initialization of nested LinkedHashMap
     */
    public Labyrinth() {
        this.labyrinth = new Character[UserInputHandler.maxDimension][UserInputHandler.maxDimension][UserInputHandler.maxDimension];
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

    public void setPoint(int z, int y, int x, char c) {
        labyrinth[z][y][x] = c;
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
        if (this.labyrinth[0][0][0] == null) {
            System.out.print("Labyrinth is not set up yet");
            return;
        }

        for (int z = 0; z < this.z; z++) {
            for (int y = 0; y < this.y; y++) {
                for (int x = 0; x < this.x; x++) {
                    System.out.print(labyrinth[z][y][x]);
                }
                System.out.printf("%n");
            }
            System.out.printf("%n%n");
        }
    }
}
