package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    Labyrinth labyrinth;

    @BeforeEach
    void init() {
        labyrinth = new Labyrinth();
    }

    @Test
    void printEmptyLabyrinthTest() {
        labyrinth.printLabyrinth();
    }
}