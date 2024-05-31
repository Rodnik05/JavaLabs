package Rodnik.Services;

import Rodnik.DAOs.CatDAO;
import Rodnik.DAOs.CatOwnerDAO;
import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.CatOwnerDTO;
import Rodnik.DTOs.ColorsDTO;
import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;
import Rodnik.Entities.Colors;

import java.util.ArrayList;
import java.util.List;

public class CatOwnerService {
    private CatOwnerDAO catOwnerDAO;
    private CatDAO catDAO;

    public void addOwner(CatOwnerDTO catOwnerDTO) {
        catOwnerDAO.saveCatOwner(new CatOwner(catOwnerDTO.getName(), catOwnerDTO.getBirthDate()));
    }

    public void removeCat(long ownerId, long catId) {
        CatOwner catOwner = catOwnerDAO.getCatOwnerById(ownerId);
        Cat cat = catDAO.getCatById(catId);
        catOwner.getCats().remove(cat);
        cat.setOwner(null);
        catOwnerDAO.updateCatOwner(catOwner);
        catDAO.updateCat(cat);
    }

    public void addCat(long ownerId, long catId) {
        CatOwner catOwner = catOwnerDAO.getCatOwnerById(ownerId);
        Cat cat = catDAO.getCatById(catId);
        catOwnerDAO.addCat(catOwner, cat);
    }

    public void removeOwner(long ownerId) {
        CatOwner catOwner = catOwnerDAO.getCatOwnerById(ownerId);
        List<Cat> cats = new ArrayList<>(catOwner.getCats());

        for (Cat cat : cats) {
            catOwnerDAO.removeCat(catOwner, cat);
        }

        catOwnerDAO.updateCatOwner(catOwner);
        catOwnerDAO.deleteCatOwner(catOwner);
    }

    public void updateOwner(CatOwnerDTO catOwnerDTO) {
        CatOwner catOwner = catOwnerDAO.getCatOwnerById(catOwnerDTO.getId());
        catOwner.setName(catOwnerDTO.getName());
        catOwner.setBirthDate(catOwnerDTO.getBirthDate());
        catOwnerDAO.updateCatOwner(catOwner);
    }

    public CatOwnerDTO getOwnerById(long ownerId) {
        CatOwner catOwner = catOwnerDAO.getCatOwnerById(ownerId);
        List<CatDTO> catDTOs = catOwner.getCats().stream()
                .map(cat -> new CatDTO(
                        cat.getName(),
                        transferColorToDTO(cat.getColor()),
                        cat.getBreed(),
                        cat.getBirthDate(),
                        cat.getOwner().getId()
                ))
                .toList();
        return new CatOwnerDTO(catOwner.getId(), catOwner.getName(), catOwner.getBirthDate(), catDTOs);
    }

    public ColorsDTO transferColorToDTO(Colors color) {
        return ColorsDTO.findByValue(color.name());
    }

    public Colors transferDTOToColor(ColorsDTO colorDTO) {
        return Colors.findByValue(colorDTO.name());
    }

    public List<CatOwnerDTO> getAllOwners() {
        return catOwnerDAO.getAllCatOwners().stream()
                .map(catOwner -> new CatOwnerDTO(catOwner.getId(), catOwner.getName(), catOwner.getBirthDate(), catOwner.getCats().stream()
                        .map(cat -> new CatDTO(
                                cat.getName(),
                                transferColorToDTO(cat.getColor()),
                                cat.getBreed(),
                                cat.getBirthDate(),
                                cat.getOwner().getId()))
                        .toList()))
                .toList();
    }
}
