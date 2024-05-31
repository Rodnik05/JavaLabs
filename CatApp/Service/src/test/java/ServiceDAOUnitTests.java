import Rodnik.DTOs.CatOwnerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Rodnik.DAOs.CatOwnerDAO;
import Rodnik.DAOs.CatDAO;
import Rodnik.Services.CatOwnerService;
import Rodnik.Services.CatService;
import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAOUnitTests {

    @Mock
    private static CatOwnerDAO catOwnerDAO;
    @Mock
    private static CatDAO catDAO;

    @InjectMocks
    private CatOwnerService catOwnerService;
    @InjectMocks
    private CatService catService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addFriendTest() {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        when(catDAO.getCatById(1L)).thenReturn(cat1);
        when(catDAO.getCatById(2L)).thenReturn(cat2);

        catService.addFriend(1L, 2L);

        verify(catDAO).getCatById(1L);
        verify(catDAO).getCatById(2L);
        verify(catDAO).addFriend(cat1, cat2);
    }

    @Test
    public void removeFriendTest() {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        when(catDAO.getCatById(1L)).thenReturn(cat1);
        when(catDAO.getCatById(2L)).thenReturn(cat2);

        catService.removeFriend(1L, 2L);

        verify(catDAO).getCatById(1L);
        verify(catDAO).getCatById(2L);
        verify(catDAO).removeFriend(cat1, cat2);
    }



    @Test
    public void addCattTest() {
        CatOwnerDTO catOwner = new CatOwnerDTO();
        catOwner.setName("Pasha");
        catOwner.setBirthDate(java.time.LocalDate.of(2004, 8, 6));

        catOwnerService.addOwner(catOwner);

        verify(catOwnerDAO).saveCatOwner(any(CatOwner.class));
    }


    @Test
    public void removeOwnerTest() {
        CatOwner catOwner = new CatOwner();
        catOwner.setCats(new ArrayList<>());
        when(catOwnerDAO.getCatOwnerById(any())).thenReturn(catOwner);

        catOwnerService.removeOwner(1L);

        verify(catOwnerDAO).getCatOwnerById(1L);
        verify(catOwnerDAO).updateCatOwner(catOwner);
        verify(catOwnerDAO).deleteCatOwner(catOwner);
    }


    @Test
    public void removeCatTest() {
        CatOwner catOwner = new CatOwner();
        Cat cat = new Cat();
        cat.setOwner(catOwner);
        List<Cat> cats = new ArrayList<>();
        cats.add(cat);
        catOwner.setCats(cats);

        when(catOwnerDAO.getCatOwnerById(1L)).thenReturn(catOwner);
        when(catDAO.getCatById(1L)).thenReturn(cat);

        catOwnerService.removeCat(1L, 1L);

        verify(catOwnerDAO).getCatOwnerById(1L);
        verify(catDAO).getCatById(1L);
        verify(catOwnerDAO).updateCatOwner(catOwner);
        verify(catDAO).updateCat(cat);
    }

    @Test
    public void ownCatTest() {
        CatOwner catOwner = new CatOwner();
        Cat cat = new Cat();

        when(catOwnerDAO.getCatOwnerById(1L)).thenReturn(catOwner);
        when(catDAO.getCatById(1L)).thenReturn(cat);

        catOwnerService.addCat(1L, 1L);

        verify(catOwnerDAO).addCat(catOwner, cat);
        verify(catOwnerDAO).getCatOwnerById(1L);
        verify(catDAO).getCatById(1L);
    }
}
