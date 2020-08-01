package us.siriusteam.springboot.datajpa.app.models.dao.deprecated;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public ClienteDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }


    @Override
    public void save(@NotNull Cliente cliente) {
        if(cliente.getId() != null && cliente.getId() > 0){
            entityManager.merge(cliente);
        } else {
            entityManager.persist(cliente);
        }
    }

    @Override
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = findOne(id);

        entityManager.remove(cliente);
    }
}
