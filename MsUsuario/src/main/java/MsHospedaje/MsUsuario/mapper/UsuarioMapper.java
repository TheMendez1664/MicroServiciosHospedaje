package MsHospedaje.MsUsuario.mapper;

import MsHospedaje.MsUsuario.dto.UsuarioDTO;
import MsHospedaje.MsUsuario.model.UsuarioModel;

public class UsuarioMapper {

    public UsuarioDTO toDTO(UsuarioModel usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(usuario.getUsername());
        dto.setPassword(usuario.getPassword());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public UsuarioModel toEntity(UsuarioDTO dto) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());
        return usuario;
    }
}