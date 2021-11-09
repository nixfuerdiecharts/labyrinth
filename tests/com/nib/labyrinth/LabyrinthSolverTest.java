package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for LabyrinthSolver
 * @see LabyrinthSolver
 */
class LabyrinthSolverTest {
    //Instance of LabyrinthSolver
    LabyrinthSolver labyrinthSolver;

    @BeforeEach
    void init() {
        labyrinthSolver = new LabyrinthSolver();
    }

}