package MsCliente.MsCliente.controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MsCliente.MsCliente.dto.ClienteDTO;
import MsCliente.MsCliente.model.Cliente;
import MsCliente.MsCliente.services.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    public ClienteController(ClienteService clienteService, MessageSource messageSource) {
        this.clienteService = clienteService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        try {
            List<Cliente> clientes = (List<Cliente>) clienteService.getAllClientes();
            List<ClienteDTO> clienteDTOs = clientes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(clienteDTOs, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("error.getting.all.clients", null, Locale.getDefault());
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.getClienteById(id);
            if (cliente != null) {
                ClienteDTO clienteDTO = convertToDTO(cliente);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            } else {
                String errorMessage = messageSource.getMessage("error.getting.client.by.id", new Object[]{id}, Locale.getDefault());
                logger.error(errorMessage);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("error.getting.client.by.id", new Object[]{id}, Locale.getDefault());
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente nuevoCliente = convertToEntity(clienteDTO);
            Cliente clienteCreado = clienteService.createCliente(clienteDTO);
            ClienteDTO creadoDTO = convertToDTO(clienteCreado);
            return new ResponseEntity<>(creadoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("error.creating.new.client", null, Locale.getDefault());
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente clienteActualizado = clienteService.updateCliente(id, clienteDTO);
            if (clienteActualizado != null) {
                ClienteDTO actualizadoDTO = convertToDTO(clienteActualizado);
                return new ResponseEntity<>(actualizadoDTO, HttpStatus.OK);
            } else {
                String errorMessage = messageSource.getMessage("error.updating.client", new Object[]{id}, Locale.getDefault());
                logger.error(errorMessage);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("error.updating.client", new Object[]{id}, Locale.getDefault());
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("error.deleting.client", new Object[]{id}, Locale.getDefault());
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos de conversión entre Entity y DTO
    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente entity = new Cliente();
        entity.setNombre(clienteDTO.getNombre());
        entity.setApellido(clienteDTO.getApellido());
        entity.setCorreo(clienteDTO.getCorreo());
        entity.setTelefono(clienteDTO.getTelefono());
        return entity;
    }
}
