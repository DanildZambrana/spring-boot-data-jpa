package us.siriusteam.springboot.datajpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;


//public interface IClienteDao extends CrudRepository<Cliente, Long> {
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
