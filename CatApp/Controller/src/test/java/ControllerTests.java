import Rodnik.Controllers.CatController;
import Rodnik.DTOs.CatDTO;
import Rodnik.DTOs.ColorsDTO;
import Rodnik.Services.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTests {
    @Mock
    private static CatService catService;

    @InjectMocks
    private CatController catController;

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

        catController.getCatById(1L);
        catController.getCatById(2L);

        verify(catService).getCatById(1L);
        verify(catService).getCatById(2L);
    }
}

