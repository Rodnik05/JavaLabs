import Rodnik.DAOs.CatDAO;
import Rodnik.DAOs.CatOwnerDAO;
import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;
import Rodnik.hibernate.SessionFactorySingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DAOUnitTests {
    @Test
    public void CatDAOTest() {
        Cat a = new Cat();
        a.setName("aboba");
        CatDAO b = new CatDAO();
        long catId = b.saveCat(a);
        Assertions.assertEquals(b.getCatById(catId).getName(), a.getName());
    }

    @Test
    public void CatOwnerDAOTest() {
        CatOwner a = new CatOwner();
        a.setName("aboba");
        CatOwnerDAO b = new CatOwnerDAO();
        long catOwnerId = b.saveCatOwner(a);
        Assertions.assertEquals(b.getCatOwnerById(catOwnerId).getName(), a.getName());
    }
}
