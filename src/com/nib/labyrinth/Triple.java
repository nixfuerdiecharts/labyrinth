package com.nib.labyrinth;

import java.util.Objects;

/**
 * Class to save three integer values
 */
public record Triple(int z, int y, int x) {

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

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
