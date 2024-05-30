package Rodnik.DTOs;

import java.util.Arrays;

public enum ColorsDTO {
    White,
    Black,
    Grey,
    Orange,
    Tabby,
    None;

    public static ColorsDTO findByValue(String name) {
        return Arrays.stream(ColorsDTO.values())
                .filter(b -> b.name().equals(name))
                .findFirst()
                .orElse(None);
    }
}
