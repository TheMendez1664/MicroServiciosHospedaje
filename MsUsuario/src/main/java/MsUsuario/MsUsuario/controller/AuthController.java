package MsHospedaje.MsUsuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MsHospedaje.MsUsuario.model.modelUsuario;
import MsHospedaje.MsUsuario.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
    AuthService authService;

    @GetMapping
    public ResponseEntity<List<modelUsuario>> getAllUsers() {
        List<modelUsuario> users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

        
    }
    @GetMapping("/{id}")
    public ResponseEntity<modelUsuario> getUserById(@PathVariable int id) {
        modelUsuario usuario = authService.getUserById(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<modelUsuario> createUser(@RequestBody modelUsuario nuevoUsuario) {
        modelUsuario usuarioCreado = authService.createUser(nuevoUsuario);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        authService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}