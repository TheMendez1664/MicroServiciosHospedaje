package MsHospedaje.MsUsuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import MsHospedaje.MsUsuario.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
}
