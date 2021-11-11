package com.nib.labyrinth;

import java.util.Objects;

/**
 * Class to save three integer values
 */
public class Triple {
    private final int z;
    private final int y;
    private final int x;

    public Triple(int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple triple = (Triple) o;
        return z == triple.z && y == triple.y && x == triple.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(z, y, x);
    }
}
