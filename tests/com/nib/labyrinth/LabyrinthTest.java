package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabyrinthTest {
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

    @BeforeEach
    void init() {
        labyrinth = new Labyrinth();
    }

    @Test
    void printEmptyLabyrinthTest() {
        labyrinth.printLabyrinth();
    }
}