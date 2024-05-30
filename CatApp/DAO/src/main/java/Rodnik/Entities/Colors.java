package Rodnik.Entities;

import java.util.Arrays;

public enum Colors {
    White,
    Black,
    Grey,
    Orange,
    Tabby,
    None;

    public static Colors findByValue(String name) {
        return Arrays.stream(Colors.values())
                .filter(b -> b.name().equals(name))
                .findFirst()
                .orElse(None);
    }
}
