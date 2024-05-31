package Rodnik.DAOs;

import Rodnik.Entities.Cat;

import java.util.List;

public interface ICatDAO {
    long saveCat(Cat cat);

    Cat getCatById(Long id);

    void updateCat(Cat cat);

    void deleteCat(Cat cat);

    List<Cat> getAllFriends(Cat cat);

    void addFriend(Cat cat, Cat friend);

    void removeFriend(Cat cat, Cat friend);
}

