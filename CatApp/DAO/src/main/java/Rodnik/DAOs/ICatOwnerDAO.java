package Rodnik.DAOs;

import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;

import java.util.List;

public interface ICatOwnerDAO {
    void saveCatOwner(CatOwner catOwner);

    CatOwner getCatOwnerById(Long id);

    void updateCatOwner(CatOwner catOwner);

    void deleteCatOwner(CatOwner catOwner);

    List<CatOwner> getAllCatOwners();

    void addCat(CatOwner catOwner, Cat cat);

    void removeCat(CatOwner catOwner, Cat cat);
}

