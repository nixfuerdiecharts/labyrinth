package com.nib.labyrinth;

import java.util.*;

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
     * HashSet to save which coordinates were visited already in labyrinth
     */
    private Set<Triple> visitedCoordinates;

    /**
     * Constructor
     */
    public LabyrinthSolver() {
        this.labyrinths = new ArrayList<>();
        this.visitedCoordinates = new HashSet<>();
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
            this.visitedCoordinates.clear();

            //Check if there is a Start point in the labyrinth
            if (!findStartAndEnd(labyrinths.get(i))) {
                System.out.println("There is no entry to the labyrinth");
            } else if (!this.hasEnd) { //Check if there is an end point in the labyrinth
                System.out.println("There is no escape from the labyrinth");
            } else {
                //Labyrinth has start and end, so try to escape
                this.stepsDone = breadthFirstSearch(labyrinths.get(i), z, y, x);
                if (this.stepsDone == 0) {
                    System.out.println("There is no escape from the labyrinth");
                } else {
                    System.out.printf("Escaped in %d minute(s)", this.stepsDone);
                }
            }
        }
    }

    /**
     * Implementation of breadth search algorithm
     *
     * @param labyrinth labyrinth to solve
     * @param z         Z coordinate of start point
     * @param y         Y coordinate of start point
     * @param x         X coordinate of start point
     * @return Amount of steps needed to solve the labyrinth
     */
    public int breadthFirstSearch(Labyrinth labyrinth, int z, int y, int x) {
        //Add current coordinate to visited coordinates
        this.visitedCoordinates.add(new Triple(z, y, x));

        //Queue for next nodes in labyrinth to visit
        Queue<Triple> visitingQueue = new ArrayDeque<>();
        //Add the start to the queue
        visitingQueue.add(new Triple(z, y, x));
        visitingQueue.add(new Triple(-1, -1, -1));

        //Initialize stepsCounter
        int steps = 0;
        //Helper for keeping track if end was found
        boolean exitFound = false;

        while (!visitingQueue.isEmpty()) {
            Triple currentNode = visitingQueue.poll();
            if (currentNode.getZ() == -1 && currentNode.getY() == -1 && currentNode.getX() == -1) {
                steps++;
                if (Objects.requireNonNull(visitingQueue.peek()).getZ() == -1 && Objects.requireNonNull(visitingQueue.peek()).getY() == -1 && Objects.requireNonNull(visitingQueue.peek()).getX() == -1) {
                    break;
                } else {
                    continue;
                }
            }

            if (labyrinth.getPoint(currentNode.getZ(), currentNode.getY(), currentNode.getX()) == 'E') {
                exitFound = true;
                break;
            }

            //Find all reachable neighbours of current node
            List<Triple> currentNodeNeighbours = findNeighbours(labyrinth, currentNode.getZ(), currentNode.getY(), currentNode.getX());

            //Check whether there are any reachable neighbours of current node
            if (!currentNodeNeighbours.isEmpty()) {
                //Iterate over all neighbours
                for (Triple node : currentNodeNeighbours) {
                    if (!this.visitedCoordinates.contains(node)) {
                        //Add neighbour to visited coordinates and to the visiting queue
                        this.visitedCoordinates.add(node);
                        visitingQueue.add(node);
                    }
                }
                visitingQueue.add(new Triple(-1, -1, -1));
            }
        }

        if (exitFound) {
            return steps;
        } else {
            return 0;
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
    private boolean solveLabyrinth(Labyrinth labyrinth, int stepsDone, int z, int y, int x) {
        //Add current coordinate to visited coordinates
        this.visitedCoordinates.add(new Triple(z, y, x));

        //Check if current position is the end
        if (labyrinth.getPoint(z, y, x) == 'E') {
            this.stepsDone = stepsDone;
            return true;
        }

        //Check if going up is an option
        if (isStepPossible(labyrinth, z + 1, y, x)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z + 1, y, x)) {
                return true;
            }
        }

        //Check if going down is an option
        if (isStepPossible(labyrinth, z - 1, y, x)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z - 1, y, x)) {
                return true;
            }

        }

        //Check if going north is an option
        if (isStepPossible(labyrinth, z, y - 1, x)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y - 1, x)) {
                return true;
            }
        }

        //Check if going south is an option
        if (isStepPossible(labyrinth, z, y + 1, x)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y + 1, x)) {
                return true;
            }
        }

        //Check if going west is an option
        if (isStepPossible(labyrinth, z, y, x - 1)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y, x - 1)) {
                return true;
            }
        }

        //Check if going east is an option
        if (isStepPossible(labyrinth, z, y, x + 1)) {
            if (solveLabyrinth(labyrinth, stepsDone + 1, z, y, x + 1)) {
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
     * Helper method to find all reachable neighbours of a node in the labyrinth
     */
    private List<Triple> findNeighbours(Labyrinth labyrinth, int z, int y, int x) {
        List<Triple> reachableNeighboursList = new ArrayList<>();
        //Check if upper neighbour is reachable
        if (isStepPossible(labyrinth, z + 1, y, x)) {
            reachableNeighboursList.add(new Triple(z + 1, y, x));
        }

        //Check if lower neighbour is reachable
        if (isStepPossible(labyrinth, z - 1, y, x)) {
            reachableNeighboursList.add(new Triple(z - 1, y, x));
        }

        //Check if north neighbour is reachable
        if (isStepPossible(labyrinth, z, y - 1, x)) {
            reachableNeighboursList.add(new Triple(z, y - 1, x));
        }

        //Check if south neighbour is reachable
        if (isStepPossible(labyrinth, z, y + 1, x)) {
            reachableNeighboursList.add(new Triple(z, y + 1, x));
        }

        //Check if west neighbour is reachable
        if (isStepPossible(labyrinth, z, y, x - 1)) {
            reachableNeighboursList.add(new Triple(z, y, x - 1));
        }

        //Check if east neighbour is reachable
        if (isStepPossible(labyrinth, z, y, x + 1)) {
            reachableNeighboursList.add(new Triple(z, y, x + 1));
        }

        return reachableNeighboursList;
    }

    /**
     * Helper method to check whether a step in the labyrinth is possible or not
     *
     * @param labyrinth Given labyrinth
     * @param z         Z coordinate
     * @param y         Y coordinate
     * @param x         X coordinate
     * @return True if step is possible
     */
    private boolean isStepPossible(Labyrinth labyrinth, int z, int y, int x) {
        boolean result = true;

        if (labyrinth.getPoint(z, y, x) == null) { //Check if coordinate is out of labyrinth bounds
            result = false;
        } else if (labyrinth.getPoint(z, y, x) == '#') { //Check if coordinate is a Stone, hence not visitable
            result = false;
        } else if (this.visitedCoordinates.contains(new Triple(z, y, x))) { //Check if coordinate was already visited
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
                        this.x = x;
                        this.y = y;
                        this.z = z;
                    } else if (labyrinth.getPoint(z, y, x) == 'E') {
                        this.hasEnd = true;
                    }
                }
            }
        }

        return result;
    }
}
