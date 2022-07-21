package folder;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractRepositoryFacade<T> {

    @PersistenceContext
    EntityManager em;

    Class<T> clazz;

    public AbstractRepositoryFacade() {
        this.clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public void edit(T entity) {
        em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        return em.find(clazz, id);
    }

    public List<T> findAllOrderById() {
        return em.createQuery("select e from " + clazz.getSimpleName() + " e order by e.id", clazz).getResultList();
    }
}
