package Rodnik.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private Owner owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cat_friendship",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Cat> friends;

    public Cat() {
        this.friends = new ArrayList<>();
    }

    public Cat(String name,Color color, String breed, LocalDate birthDate) {
        this.name = name;
        this.color = color;
        this.breed = breed;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cat cat = (Cat) obj;
        return id.equals(cat.id);
    }
}
