package MsUsuarios.MsUsuarios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import MsUsuarios.MsUsuarios.model.modelUsuario;





@Repository
public interface IAuthRepository extends CrudRepository<modelUsuario, Integer> {
   
}