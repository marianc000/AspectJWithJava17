package folder;

import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    @Autowired
    ItemRepository db;

    @Test
    public void testItem() {
        Item i = new Item("Test");
        assertNull(i.getId());
        db.create(i);
        assertNotNull(i.getId());
        assertEquals(i.toString(), db.find(i.getId()).toString());
        assertFalse(db.findAllOrderById().isEmpty());
    }
}
