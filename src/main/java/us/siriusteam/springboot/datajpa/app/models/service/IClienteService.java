package us.siriusteam.springboot.datajpa.app.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();

    /**
     * Retorna los elementos paginados
     * @param pageable pagina
     * @return una pagina con los elementos obtenidos
     */
    Page<Cliente> findAll(Pageable pageable);

    void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);


}
