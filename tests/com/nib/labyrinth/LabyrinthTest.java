package com.nib.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void setPointTest() {
        labyrinth.setX(4);
        labyrinth.setY(3);
        labyrinth.setZ(1);
        labyrinth.setPoint(0, 0, 0, 's');
        labyrinth.setPoint(0, 0, 1, '.');
        labyrinth.setPoint(0, 0, 2, '.');
        labyrinth.setPoint(0, 0, 3, '.');

        labyrinth.setPoint(0, 1, 0, '#');
        labyrinth.setPoint(0, 1, 1, '.');
        labyrinth.setPoint(0, 1, 2, '#');
        labyrinth.setPoint(0, 1, 3, '#');

        labyrinth.setPoint(0, 2, 0, '#');
        labyrinth.setPoint(0, 2, 1, '#');
        labyrinth.setPoint(0, 2, 2, '#');
        labyrinth.setPoint(0, 2, 3, '#');

        labyrinth.printLabyrinth();

    }

    @Test
    void setLabyrinthByTextBlockTest() {
        labyrinth.setLabyrinthByTextBlock(labyrinth1Structure);

        System.out.printf("X = %d ; Y = %d ; Z = %d", labyrinth.getX(), labyrinth.getY(), labyrinth.getZ());

        assertEquals(3, labyrinth.getZ());
        assertEquals(3, labyrinth.getY());
        assertEquals(4, labyrinth.getX());

        labyrinth.printLabyrinth();
    }
}