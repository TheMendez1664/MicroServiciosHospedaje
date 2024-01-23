package MsHospedaje.MsUsuario.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MsHospedaje.MsUsuario.model.modelUsuario;
import MsHospedaje.MsUsuario.repository.IAuthRepository;


@Service
public class AuthService {

    @Autowired
    private IAuthRepository authRepository;

    public List<modelUsuario> getAllUsers() {
        return (List<modelUsuario>) authRepository.findAll();
    }

    public modelUsuario getUserById(int id) {
        return authRepository.findById(id).orElse(null);
    }

    public modelUsuario createUser(modelUsuario nuevoUsuario) {
        return authRepository.save(nuevoUsuario);
    }

    public modelUsuario updateUser(int id, modelUsuario usuarioActualizado) {
        modelUsuario usuarioExistente = authRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setUsuario(usuarioActualizado.getUsuario());
            usuarioExistente.setClave(usuarioActualizado.getClave());
            return authRepository.save(usuarioExistente);
        } else {
            return null;
        }
    }

    public void deleteUser(int id) {
        // Elimina el usuario con el ID proporcionado, si existe
        authRepository.deleteById(id);
    }

  
}