package Rodnik.Controllers;

import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.ColorsDTO;
import Rodnik.Services.CatService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class CatController {
    private CatService catService;
    public void addCat(String name, long ownerId, String breed, String color, LocalDate birth) {
         catService.addCat(new CatDTO(name, ColorsDTO.findByValue(color), breed, birth, ownerId));
    }
    public void removeCat(long id) {
        catService.removeCat(id);
    }
    public CatDTO getCatById(long id) {
        return catService.getCatById(id);
    }
    public List<CatDTO> getAllCats() {
        return catService.getAllCats();
    }
    public List<CatDTO> getAllFriends(long id) {
        return catService.getFriends(id);
    }
    public void addFriend(long catId, long friendCatId) {
        catService.addFriend(catId, friendCatId);
    }
    public void unfriend(long catId, long friendCatId) {
        catService.removeFriend(catId, friendCatId);
    }
}