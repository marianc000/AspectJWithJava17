package folder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AbstractRepositoryFacadeTest {

    @Test
    public void testCreate() {
        AbstractRepositoryFacade<Item> i = new AbstractRepositoryFacade<>() {
        };
        assertEquals(i.getClazz(), Item.class);
    }

}
