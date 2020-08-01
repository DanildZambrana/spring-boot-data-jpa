package us.siriusteam.springboot.datajpa.app.models.dao.deprecated;

import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {
    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    public void delete(Long id);
}
