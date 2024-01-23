// package MsHospedaje.MsUsuario.mapper;

// import MsHospedaje.MsUsuario.dto.UsuarioDTO;
// import MsHospedaje.MsUsuario.model.modelUsuario;

// public class UsuarioMapper {

//     public UsuarioDTO toDTO(modelUsuario usuario) {
//         UsuarioDTO dto = new UsuarioDTO();
//         dto.setUsuario(usuario.getIdUsuario());
//         dto.setPassword(usuario.getPassword());
//         dto.setRol(usuario.getRol());
//         return dto;
//     }

//     public UsuarioDTO toEntity(UsuarioDTO dto) {
//         modelUsuario usuario = new modelUsuario();
//         usuario.setUsername(dto.getUsername());
//         usuario.setPassword(dto.getPassword());
//         usuario.setRol(dto.getRol());
//         return usuario;
//     }
// }