package MsCliente.MsCliente.services;



import MsCliente.MsCliente.dto.ClienteDTO;
import MsCliente.MsCliente.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> getAllClientes();

    Cliente getClienteById(Long id);

    Cliente createCliente(ClienteDTO nuevoClienteDTO);

    Cliente updateCliente(Long id, ClienteDTO clienteActualizadoDTO);

    void deleteCliente(Long id);
}
