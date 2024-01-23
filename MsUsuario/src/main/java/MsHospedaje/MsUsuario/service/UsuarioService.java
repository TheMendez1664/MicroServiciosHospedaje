package MsHospedaje.MsUsuario.service;

import MsHospedaje.MsUsuario.dto.UsuarioDTO;
import MsHospedaje.MsUsuario.model.UsuarioModel;

public interface UsuarioService {
    Iterable<UsuarioModel> getAllUsuarios();
    UsuarioModel getUsuarioById(Integer id);
    UsuarioModel createUsuario(UsuarioDTO nuevoUsuarioDTO);
    UsuarioModel updateUsuario(Integer id, UsuarioDTO usuarioActualizadoDTO);
    void deleteUsuario(Integer id);
}