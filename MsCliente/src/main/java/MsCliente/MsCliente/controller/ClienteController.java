package MsCliente.MsCliente.controller;


import java.util.List;
import java.util.stream.Collectors;

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

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteService.getAllClientes();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(cliente -> convertToDTO(cliente))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clienteDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            ClienteDTO clienteDTO = convertToDTO(cliente);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente nuevoCliente = convertToEntity(clienteDTO);
        Cliente clienteCreado = clienteService.createCliente(clienteDTO);
        ClienteDTO creadoDTO = convertToDTO(clienteCreado);
        return new ResponseEntity<>(creadoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteActualizado = clienteService.updateCliente(id, clienteDTO);
        if (clienteActualizado != null) {
            ClienteDTO actualizadoDTO = convertToDTO(clienteActualizado);
            return new ResponseEntity<>(actualizadoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
