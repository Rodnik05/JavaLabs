package Rodnik.Entity;

import java.util.Arrays;

public enum Color {
    White,
    Black,
    Grey,
    Orange,
    Tabby,
    Unknown;

    public static Color findByValue(String name) {
        return Arrays.stream(Color.values())
                .filter(b -> b.name().equals(name))
                .findFirst()
                .orElse(Unknown);
    }
}
