package MsUsuarios.MsUsuarios.controller;

import MsUsuarios.MsUsuarios.dto.ApiResponse;
import MsUsuarios.MsUsuarios.dto.UsuarioDTO;
import MsUsuarios.MsUsuarios.exception.ResourceNotFoundException;
import MsUsuarios.MsUsuarios.mapper.UsuarioMapper;
import MsUsuarios.MsUsuarios.model.modelUsuario;
import MsUsuarios.MsUsuarios.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping("/usuarios")
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> getAllUsers() {
        try {
            List<modelUsuario> usuarios = authService.getAllUsers();
            List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ApiResponse<>(usuarioDTOs, "Usuarios obtenidos con éxito", true), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error al obtener todos los usuarios", ex);
            throw ex;
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getUserById(@PathVariable int id) {
        try {
            modelUsuario usuario = authService.getUserById(id);
            if (usuario == null) {
                throw new ResourceNotFoundException("Usuario", "id", id);
            }
            return new ResponseEntity<>(new ApiResponse<>(usuarioMapper.toDTO(usuario), "Usuario obtenido con éxito", true), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error al obtener el usuario", ex);
            throw ex;
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<ApiResponse<UsuarioDTO>> createUser(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            modelUsuario nuevoUsuario = authService.createUser(usuarioMapper.toEntity(usuarioDTO));
            return new ResponseEntity<>(new ApiResponse<>(usuarioMapper.toDTO(nuevoUsuario), "Usuario creado con éxito", true), HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Error al crear el usuario", ex);
            throw ex;
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> updateUser(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            modelUsuario usuarioActualizado = authService.updateUser(id, usuarioMapper.toEntity(usuarioDTO));
            if (usuarioActualizado == null) {
                throw new ResourceNotFoundException("Usuario", "id", id);
            }
            return new ResponseEntity<>(new ApiResponse<>(usuarioMapper.toDTO(usuarioActualizado), "Usuario actualizado con éxito", true), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error al actualizar el usuario", ex);
            throw ex;
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable int id) {
        try {
            authService.deleteUser(id);
            return new ResponseEntity<>(new ApiResponse<>(null, "Usuario eliminado con éxito", true), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.error("Error al eliminar el usuario", ex);
            throw ex;
        }
    }

    private UsuarioDTO convertToDTO(modelUsuario usuario) {
        return usuarioMapper.toDTO(usuario);
    }

    private modelUsuario convertToEntity(UsuarioDTO usuarioDTO) {
        return usuarioMapper.toEntity(usuarioDTO);
    }
}