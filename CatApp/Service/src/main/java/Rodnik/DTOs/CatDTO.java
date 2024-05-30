package Rodnik.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class CatDTO {
    private long id;
    @NonNull
    private String name;
    @NonNull
    private ColorsDTO color;
    @NonNull
    private String breed;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private long ownerId;
}
