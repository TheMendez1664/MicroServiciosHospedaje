package MsHospedaje.MsUsuario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MsHospedaje.MsUsuario.dto.UsuarioDTO;
import MsHospedaje.MsUsuario.model.UsuarioModel;
import MsHospedaje.MsUsuario.service.UsuarioService;
import MsHospedaje.MsUsuario.exception.ResourceNotFoundException;
import MsHospedaje.MsUsuario.dto.ApiResponse;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> getAllUsuarios() {
        try {
            List<UsuarioModel> usuarios = (List<UsuarioModel>) usuarioService.getAllUsuarios();
            List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                    .map(usuario -> convertToDTO(usuario))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ApiResponse<>(usuarioDTOs, "Usuarios obtenidos con éxito", true), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error al obtener todos los usuarios", ex);
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getUsuarioById(@PathVariable Integer id) {
        try {
            UsuarioModel usuario = usuarioService.getUsuarioById(id);
            if (usuario != null) {
                UsuarioDTO usuarioDTO = convertToDTO(usuario);
                return new ResponseEntity<>(new ApiResponse<>(usuarioDTO, "Usuario obtenido con éxito", true), HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Usuario", "id", id);
            }
        } catch (ResourceNotFoundException ex) {
            logger.error("Error al obtener el usuario", ex);
            throw ex;
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioModel nuevoUsuario = convertToEntity(usuarioDTO);
            UsuarioModel usuarioCreado = usuarioService.createUsuario(usuarioDTO);
            UsuarioDTO creadoDTO = convertToDTO(usuarioCreado);
            return new ResponseEntity<>(new ApiResponse<>(creadoDTO, "Usuario creado con éxito", true), HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Error al crear el usuario", ex);
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioModel usuarioActualizado = usuarioService.updateUsuario(id, usuarioDTO);
            if (usuarioActualizado != null) {
                UsuarioDTO actualizadoDTO = convertToDTO(usuarioActualizado);
                return new ResponseEntity<>(new ApiResponse<>(actualizadoDTO, "Usuario actualizado con éxito", true), HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Usuario", "id", id);
            }
        } catch (ResourceNotFoundException ex) {
            logger.error("Error al actualizar el usuario", ex);
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuario(id);
            return new ResponseEntity<>(new ApiResponse<>(null, "Usuario eliminado con éxito", true), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.error("Error al eliminar el usuario", ex);
            throw ex;
        }
    }

    // Métodos de conversión entre Entity y DTO
    private UsuarioDTO convertToDTO(UsuarioModel usuario) {
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol()
        );
    }

    private UsuarioModel convertToEntity(UsuarioDTO usuarioDTO) {
        UsuarioModel entity = new UsuarioModel();
        entity.setUsername(usuarioDTO.getUsername());
        entity.setPassword(usuarioDTO.getPassword());
        entity.setRol(usuarioDTO.getRol());
        return entity;
    }
}