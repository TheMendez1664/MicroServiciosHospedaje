package MsCliente.MsCliente.services;

import MsCliente.MsCliente.dto.ClienteDTO;
import MsCliente.MsCliente.exception.ClienteServiceException;
import MsCliente.MsCliente.model.Cliente;
import MsCliente.MsCliente.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cliente> getAllClientes() {
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al obtener todos los clientes", e);
            throw e;
        }
    }

    @Override
    public Cliente getClienteById(Long id) {
        try {
            return clienteRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error al obtener el cliente con ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public Cliente createCliente(ClienteDTO nuevoClienteDTO) {
        try {
            Cliente nuevoCliente = new Cliente();
            BeanUtils.copyProperties(nuevoClienteDTO, nuevoCliente);
            return clienteRepository.save(nuevoCliente);
        } catch (Exception e) {
            logger.error("Error al crear un nuevo cliente", e);
            throw e;
        }
    }

    @Override
    public Cliente updateCliente(Long id, ClienteDTO clienteActualizadoDTO) {
        try {
            Cliente clienteExistente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ClienteServiceException("Cliente no encontrado con ID: " + id));

            BeanUtils.copyProperties(clienteActualizadoDTO, clienteExistente);

            return clienteRepository.save(clienteExistente);
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente con ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public void deleteCliente(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al eliminar el cliente con ID: {}", id, e);
            throw e;
        }
    }
}
