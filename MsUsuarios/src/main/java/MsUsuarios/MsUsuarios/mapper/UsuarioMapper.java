package MsUsuarios.MsUsuarios.mapper;

import MsUsuarios.MsUsuarios.dto.UsuarioDTO;
import MsUsuarios.MsUsuarios.model.modelUsuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(modelUsuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setUsuario(usuario.getUsuario());
        dto.setClave(usuario.getClave());
        return dto;
    }

    public modelUsuario toEntity(UsuarioDTO dto) {
        modelUsuario usuario = new modelUsuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setUsuario(dto.getUsuario());
        usuario.setClave(dto.getClave());
        return usuario;
    }
}