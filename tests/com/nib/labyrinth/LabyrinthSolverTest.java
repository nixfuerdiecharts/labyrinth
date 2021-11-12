package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test Class for LabyrinthSolver
 *
 * @see LabyrinthSolver
 */
class LabyrinthSolverTest {
    //Instance of LabyrinthSolver
    LabyrinthSolver labyrinthSolver;
    //Labyrinth to solve
    Labyrinth labyrinth;

    String labyrinth1Structure =
            """
                    S..#
                    #.##
                    ####
                       \s
                    ###E
                    #.##
                    ####
                       \s
                    ###.
                    #...
                    #.##""";

    String labyrinth2Structure =
            """
                    S..
                    ...
                    ...
                       \s
                    ...
                    ...
                    ...
                       \s
                    E..
                    ...
                    ...
                    """;

    String labyrinth3Structure =
            """
                    S....
                    .###.
                    .##..
                    ###.#
                         \s
                    #####
                    #####
                    ##.##
                    ##...
                         \s
                    #####
                    #####
                    #.###
                    ####E
                    """;

    @BeforeEach
    void init() {
        labyrinthSolver = new LabyrinthSolver();
        labyrinth = new Labyrinth();
    }

    @Test
    void solveLabyrinthByTextBlockTest() {
        labyrinth.setLabyrinthByTextBlock(labyrinth2Structure);
        List<Labyrinth> myLabyrinthList = new ArrayList<>();
        myLabyrinthList.add(labyrinth);

        labyrinthSolver.setLabyrinths(myLabyrinthList);
        labyrinthSolver.solveAllLabyrinths();
    }

    @Test
    void solveLabyrinthBFSTest() {
        labyrinth.setLabyrinthByTextBlock(labyrinth3Structure);

        int steps = labyrinthSolver.breadthFirstSearch(labyrinth, 0, 0, 0);

        System.out.printf("Solved labyrinth in %d steps", steps);
    }

}