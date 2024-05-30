
import Rodnik.Services.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTests {
    @Mock
    private static CatOwnerDAO catOwnerDAO;
    @Mock
    private static CatDAO catDAO;

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
}

