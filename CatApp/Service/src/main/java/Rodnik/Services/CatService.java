package Rodnik.Services;

import Rodnik.DAOs.CatDAO;
import Rodnik.DAOs.CatOwnerDAO;
import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.ColorsDTO;
import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;
import Rodnik.Entities.Colors;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CatService {
    private CatDAO catDAO;
    private CatOwnerDAO catOwnerDAO;

    public void addCat(CatDTO catDTO) {
        catDAO.saveCat(new Cat(catDTO.getName(), transferDTOToColor(catDTO.getColor()), catDTO.getBreed(), catDTO.getBirthDate()));
    }

    public ColorsDTO transferColorToDTO(Colors color) {
        return ColorsDTO.findByValue(color.name());
    }

    public Colors transferDTOToColor(ColorsDTO colorDTO) {
        return Colors.findByValue(colorDTO.name());
    }

    public CatDTO getCatById(long catId) {
        Cat cat = catDAO.getCatById(catId);
        return new CatDTO(
                cat.getName(),
                transferColorToDTO(cat.getColor()),
                cat.getBreed(),
                cat.getBirthDate(),
                cat.getOwner().getId());
    }

    public void updateCat(CatDTO catDTO) {
        Cat cat = catDAO.getCatById(catDTO.getId());
        cat.setName(catDTO.getName());
        cat.setColor(transferDTOToColor(catDTO.getColor()));
        cat.setBreed(catDTO.getBreed());
        cat.setBirthDate(catDTO.getBirthDate());
        catDAO.updateCat(cat);
    }

    public void removeCat(long catId) {
        catDAO.deleteCat(catDAO.getCatById(catId));
    }

    public void addOwner(long catId, long ownerId) {
        Cat cat = catDAO.getCatById(catId);
        catOwnerDAO.ownCat(catOwnerDAO.getCatOwnerById(ownerId), cat);
    }

    public List<CatDTO> getAllCats() {
        List<CatOwner> catOwners = catOwnerDAO.getAllCatOwners();

        return catOwners.stream()
                .flatMap(
                        catOwner -> catOwner
                                .getCats()
                                .stream()
                                .map(cat -> new CatDTO(
                                        cat.getName(),
                                        transferColorToDTO(cat.getColor()),
                                        cat.getBreed(),
                                        cat.getBirthDate(),
                                        cat.getOwner().getId())
                                )
                )
                .toList();
    }

    public void addFriend(long catId, long friendId) {
        Cat cat = catDAO.getCatById(catId);
        Cat friend = catDAO.getCatById(friendId);
        catDAO.addFriend(cat, friend);
    }

    public void removeFriend(long catId, long friendId) {
        Cat cat = catDAO.getCatById(catId);
        Cat friend = catDAO.getCatById(friendId);
        catDAO.removeFriend(cat, friend);
    }

    public List<CatDTO> getFriends(long catId) {
        Cat cat = catDAO.getCatById(catId);
        return cat
                .getFriends()
                .stream()
                .map(friendCat -> new CatDTO(
                    friendCat.getName(),
                    transferColorToDTO(friendCat.getColor()),
                    friendCat.getBreed(),
                    friendCat.getBirthDate(),
                    friendCat.getOwner().getId()))
                .toList();
    }
}

