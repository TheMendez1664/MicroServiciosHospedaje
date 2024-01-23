package MsHospedaje.MsUsuario.mapper;

import org.springframework.stereotype.Component;

import MsHospedaje.MsUsuario.dto.AuthRequest;
import MsHospedaje.MsUsuario.model.AccessModel;

@Component
public class UserMapper {

    public AuthRequest modelToDto(AccessModel model) {
        AuthRequest dto = new AuthRequest();
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        return dto;
    }

    public AccessModel dtoToModel(AuthRequest dto) {
        AccessModel model = new AccessModel();
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        return model;
    }
}