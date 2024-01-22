package MsHospedaje.MsUsuario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MsHospedaje.MsUsuario.dto.AuthRequest;
import MsHospedaje.MsUsuario.dto.AuthResponse;
import MsHospedaje.MsUsuario.jwt.JwtToken;
import MsHospedaje.MsUsuario.model.AccessModel;
import MsHospedaje.MsUsuario.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private JwtToken jwtTokenCross;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping
    public List<AccessModel> get() {
        return authService.getAcces();
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody AuthRequest request) throws Exception {
        logger.info("Post: Username {} - Password {}", request.getUsername(), request.getPassword());

        if (!authService.validatedCredentials(request.getUsername(), request.getPassword())) {
            return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtTokenCross.generateToken(request);
        AuthResponse response = new AuthResponse(token, request.getUsername(), "1d");

        return ResponseEntity.ok(response);
    }

}
