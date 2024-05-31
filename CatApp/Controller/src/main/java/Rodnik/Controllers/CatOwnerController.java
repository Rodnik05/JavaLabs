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
    public long addOwner(String name, LocalDate birth) {
        return catOwnerService.addOwner(new CatOwnerDTO(name, birth));
    }
    public CatOwnerDTO getOwnerById(long id) {
        return catOwnerService.getOwnerById(id);
    }
    public List<CatOwnerDTO> getAllOwners() {
        return catOwnerService.getAllOwners();
    }
    public void removeOwner(long id) {
        catOwnerService.removeOwner(id);
    }
}
