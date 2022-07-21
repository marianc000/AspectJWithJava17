package folder;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tracing.annotations.Traced;

@Traced
@Service
public class ItemService {

    @Autowired
    ItemRepository db;

    Item create(Item item) {
        db.create(item);
        return db.find(item.getId());
    }

    List<Item> findAll() {
        return db.findAllOrderById();
    }
}
