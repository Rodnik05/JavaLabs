package Rodnik.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "owners")
public class CatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Cat> cats;

    public CatOwner() {
    }

    public CatOwner(String name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }
}

