package MsHospedaje.MsUsuario.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import MsHospedaje.MsUsuario.dto.UsuarioDTO;
import MsHospedaje.MsUsuario.exception.ResourceNotFoundException;
import MsHospedaje.MsUsuario.model.UsuarioModel;
import MsHospedaje.MsUsuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Iterable<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public UsuarioModel createUsuario(UsuarioDTO nuevoUsuarioDTO) {
        UsuarioModel nuevoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(nuevoUsuarioDTO, nuevoUsuario);
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public UsuarioModel updateUsuario(Integer id, UsuarioDTO usuarioActualizadoDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            if(usuarioActualizadoDTO.getUsername() != null) {
                usuario.setUsername(usuarioActualizadoDTO.getUsername());
            }
            if(usuarioActualizadoDTO.getPassword() != null) {
                usuario.setPassword(usuarioActualizadoDTO.getPassword());
            }
            if(usuarioActualizadoDTO.getRol() != null) {
                usuario.setRol(usuarioActualizadoDTO.getRol());
            }
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
