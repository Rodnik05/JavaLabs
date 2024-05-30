package Rodnik.Controllers;

import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.CatOwnerDTO;
import Rodnik.Services.CatOwnerService;
import Rodnik.Services.CatService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class CatOwnerController {
    private CatOwnerService catOwnerService;
    void addOwner(String name, LocalDate birth) {
        catOwnerService.addOwner(new CatOwnerDTO(name, birth));
    }
    CatOwnerDTO getOwnerById(int id) {
        return catOwnerService.getOwner(id);
    }
    List<CatOwnerDTO> getAllOwners() {
        return catOwnerService.getAllOwners();
    }
    void removeOwner(int id) {
        catOwnerService.removeOwner(id);
    }
}
