package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for LabyrinthSolver
 * @see LabyrinthSolver
 */
class LabyrinthSolverTest {
    //Instance of LabyrinthSolver
    LabyrinthSolver labyrinthSolver;
    //Labyrinth to solve
    Labyrinth labyrinth;
    //Example labyrinth
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

    @BeforeEach
    void init() {
        labyrinthSolver = new LabyrinthSolver();
        labyrinth = new Labyrinth();
    }

    @Test
    void solveLabyrinthByTextBlockTest() {
        labyrinth.setLabyrinthByTextBlock(labyrinth1Structure);
        List<Labyrinth> myLabyrinthList = new ArrayList<>();
        myLabyrinthList.add(labyrinth);

        labyrinthSolver.setLabyrinths(myLabyrinthList);
        labyrinthSolver.solveAllLabyrinths();
    }

}