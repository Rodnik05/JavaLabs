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
    void addCat(String name, long ownerId, String breed, String color, LocalDate birth) {
         catService.addCat(new CatDTO(name, ColorsDTO.findByValue(color), breed, birth, ownerId));
    }
    void removeCat(long id) {
        catService.removeCat(id);
    }
    CatDTO getCatById(long id) {
        return catService.getCat(id);
    }
    List<CatDTO> getAllCats() {
        return catService.getAllCats();
    }
    List<CatDTO> getAllFriends(long id) {
        return catService.getFriends(id);
    }
    void addFriend(long catId, long friendCatId) {
        catService.addFriend(catId, friendCatId);
    }
    void unfriend(long catId, long friendCatId) {
        catService.removeFriend(catId, friendCatId);
    }
}