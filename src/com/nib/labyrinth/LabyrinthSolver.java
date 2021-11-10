package com.nib.labyrinth;

import java.util.ArrayList;
import java.util.List;

/**
 * Labyrinth Solver class
 */
public class LabyrinthSolver {
    /**
     * List of Labyrinths to solve
     *
     * @see Labyrinth
     */
    private List<Labyrinth> labyrinths;
    /**
     * Start coordinate of z for current labrinth to solve
     */
    private int z;
    /**
     * Start coordinate of y for current labrinth to solve
     */
    private int y;
    /**
     * Start coordinate of x for current labrinth to solve
     */
    private int x;
    /**
     * Checker whether labyrinth has and end or not
     */
    private boolean hasEnd;
    /**
     * Counter for steps in current labyrinth
     */
    private int stepsDone;

    /**
     * Constructor
     */
    public LabyrinthSolver() {
        this.labyrinths = new ArrayList<>();
    }

    /**
     * Setter for labyrints list
     *
     * @param labyrinths Labyrinth list
     */
    public void setLabyrinths(List<Labyrinth> labyrinths) {
        this.labyrinths = labyrinths;
    }

    /**
     * Method to solve all labyrinths in the labyrinths list
     */
    public void solveAllLabyrinths() {
        for (int i = 0; i < labyrinths.size(); i++) {
            System.out.printf("Solving the %d. labyrinth...%n", i + 1);
            this.hasEnd = false;

            //Check if there is a Start point in the labyrinth
            if (!findStartAndEnd(labyrinths.get(i))) {
                System.out.println("There is no entry to the labyrinth");
            } else if (!this.hasEnd) { //Check if there is an end point in the labyrinth
                System.out.println("There is no escape from the labyrinth");
            } else {
                //Labyrinth has start and end, so try to escape
                this.stepsDone = 0;
                if (!solveLabyrinth(labyrinths.get(i), stepsDone, z, y, x, z, y, x)) {
                    System.out.println("There is no escape from the labyrinth");
                } else {
                    System.out.printf("Escaped in %d minute(s)", this.stepsDone);
                }
            }
        }
    }

    /**
     * Recursive backtracking method to solve a given labyrinth
     *
     * @param labyrinth given labyrinth
     * @param stepsDone current steps done
     * @param z         Current Z coordinate
     * @param y         Current Y coordinate
     * @param x         Current X coordinate
     * @return steps needed to solve the labyrinth
     */
    private boolean solveLabyrinth(Labyrinth labyrinth, int stepsDone, int z, int y, int x, int lastZ, int lastY, int lastX) {
        //Check if current position is the end
        if (labyrinth.getPoint(z, y, x) == 'E') {
            this.stepsDone = stepsDone;
            return true;
        }

        if (isStepPossible(labyrinth, z + 1, y, x, lastZ, lastY, lastX)) {
            //Check if going up is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z + 1, y, x, z, y, x)) {
                return true;
            }
        }

        if (isStepPossible(labyrinth, z - 1, y, x, lastZ, lastY, lastX)) {
            //Check if going down is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z - 1, y, x, z, y, x)) {
                return true;
            }

        }

        if (isStepPossible(labyrinth, z, y - 1, x, lastZ, lastY, lastX)) {
            //Check if going north is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y - 1, x, z, y, x)) {
                return true;
            }
        }

        if (isStepPossible(labyrinth, z, y + 1, x, lastZ, lastY, lastX)) {
            //Check if going south is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y + 1, x, z, y, x)) {
                return true;
            }
        }

        if (isStepPossible(labyrinth, z, y, x - 1, lastZ, lastY, lastX)) {
            //Check if going west is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y, x - 1, z, y, x)) {
                return true;
            }
        }

        if (isStepPossible(labyrinth, z, y, x + 1, lastZ, lastY, lastX)) {
            //Check if going east is an option
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y, x + 1, z, y, x)) {
                return true;
            }
        }

        //Nothing else to move to --> trapped on current way
        return false;
    }

    // ***********************************
    // Helper methods
    // ***********************************

    /**
     * Helper method to check whether a step in the labyrinth is possible or not
     *
     * @param labyrinth Given labyrinth
     * @param z         Z coordinate
     * @param y         Y coordinate
     * @param x         X coordinate
     * @return True if step is possible
     */
    private boolean isStepPossible(Labyrinth labyrinth, int z, int y, int x, int lastZ, int lastY, int lastX) {
        boolean result = true;

        if (labyrinth.getPoint(z, y, x) == null) {
            result = false;
        } else if (labyrinth.getPoint(z, y, x) == '#') {
            result = false;
        } else if (z == lastZ && y == lastY && x == lastX) {
            result = false;
        }

        return result;
    }

    /**
     * Helper method to find the entry point in the labyrinth
     *
     * @param labyrinth giben labyrinth
     * @return True if there is a start point
     */
    private boolean findStartAndEnd(Labyrinth labyrinth) {
        boolean result = false;
        for (int z = 0; z < labyrinth.getZ(); z++) {
            for (int y = 0; y < labyrinth.getY(); y++) {
                for (int x = 0; x < labyrinth.getX(); x++) {
                    if (labyrinth.getPoint(z, y, x) == 'S') {
                        result = true;
                    } else if (labyrinth.getPoint(z, y, x) == 'E') {
                        this.hasEnd = true;
                    }
                }
            }
        }

        return result;
    }
}
