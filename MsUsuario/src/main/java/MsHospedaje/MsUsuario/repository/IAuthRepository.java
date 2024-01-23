package MsHospedaje.MsUsuario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import MsHospedaje.MsUsuario.model.modelUsuario;




@Repository
public interface IAuthRepository extends CrudRepository<modelUsuario, Integer> {
   
}