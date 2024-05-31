import Rodnik.Controllers.CatController;
import Rodnik.Controllers.CatOwnerController;
import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.CatOwnerDTO;
import Rodnik.DTOs.ColorsDTO;
import Rodnik.Services.CatOwnerService;
import Rodnik.Services.CatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTests {
    @Mock
    private static CatService catService;

    @Mock
    private static CatOwnerService catOwnerService;

    @InjectMocks
    private CatController catController;

    @InjectMocks
    private CatOwnerController catOwnerController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void GetFriendTest() {
        CatDTO catDTO1 = new CatDTO(
                "Me",
                ColorsDTO.Black,
                "cat",
                LocalDate.of(2004,05,05),
                0);
        CatDTO catDTO2 = new CatDTO(
                "You",
                ColorsDTO.Black,
                "dog",
                LocalDate.of(1800,05,05),
                0);
        when(catService.getCatById(1L)).thenReturn(catDTO1);
        when(catService.getCatById(2L)).thenReturn(catDTO2);

        CatDTO result1 = catController.getCatById(1L);
        CatDTO result2 = catController.getCatById(2L);

        Assertions.assertEquals(result1, catDTO1);
        Assertions.assertEquals(result2, catDTO2);

        verify(catService).getCatById(1L);
        verify(catService).getCatById(2L);
    }

    @Test
    public void getAllCatsTest() {
        CatDTO catDTO1 = new CatDTO(
                "Me",
                ColorsDTO.Black,
                "cat",
                LocalDate.of(2004,05,05),
                0);
        CatDTO catDTO2 = new CatDTO(
                "You",
                ColorsDTO.Black,
                "dog",
                LocalDate.of(1800,05,05),
                0);

        List<CatDTO> listOfCats = new ArrayList<CatDTO>();
        listOfCats.add(catDTO1);
        listOfCats.add(catDTO2);

        when(catService.getAllCats()).thenReturn(listOfCats);


        Assertions.assertEquals(listOfCats, catController.getAllCats());
        verify(catService).getAllCats();
    }


    @Test
    public void getFriendsTest() {
        CatDTO catDTO1 = new CatDTO(
                "Me",
                ColorsDTO.Black,
                "cat",
                LocalDate.of(2004,05,05),
                0);
        CatDTO catDTO2 = new CatDTO(
                "You",
                ColorsDTO.Black,
                "dog",
                LocalDate.of(1800,05,05),
                0);
        CatDTO catDTO3 = new CatDTO(
                "He",
                ColorsDTO.White,
                "cat",
                LocalDate.of(2004,05,05),
                0);
        CatDTO catDTO4 = new CatDTO(
                "She",
                ColorsDTO.Tabby,
                "cat",
                LocalDate.of(100,02,01),
                1);

        List<CatDTO> listOfCats = new ArrayList<CatDTO>();
        listOfCats.add(catDTO2);
        listOfCats.add(catDTO3);
        listOfCats.add(catDTO4);

        when(catService.getFriends(1L)).thenReturn(listOfCats);


        Assertions.assertEquals(listOfCats, catController.getFriends(1L));
        verify(catService).getFriends(1L);
    }

    @Test
    public void getOwnerById() {
        CatOwnerDTO catOwnerDTO1 = new CatOwnerDTO("Nikita", LocalDate.of(2004, 05, 05));

        when(catOwnerService.getOwnerById(1L)).thenReturn(catOwnerDTO1);

        Assertions.assertEquals(catOwnerController.getOwnerById(1L), catOwnerDTO1);
    }

    @Test
    public void getAllOwnersTest() {
        CatOwnerDTO catOwnerDTO1 = new CatOwnerDTO("Nikita", LocalDate.of(2004, 05, 05));
        CatOwnerDTO catOwnerDTO2 = new CatOwnerDTO("Ne Nikita", LocalDate.of(2003, 05, 05));

        List<CatOwnerDTO> listOfCatOwners = new ArrayList<CatOwnerDTO>();
        listOfCatOwners.add(catOwnerDTO1);
        listOfCatOwners.add(catOwnerDTO1);

        when(catOwnerService.getAllOwners()).thenReturn(listOfCatOwners);


        Assertions.assertEquals(catOwnerController.getAllOwners(), listOfCatOwners);
        verify(catOwnerService).getAllOwners();
    }
}

