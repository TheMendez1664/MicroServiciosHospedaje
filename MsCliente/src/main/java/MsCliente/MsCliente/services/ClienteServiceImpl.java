package MsCliente.MsCliente.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MsCliente.MsCliente.dto.ClienteDTO;
import MsCliente.MsCliente.model.Cliente;
import MsCliente.MsCliente.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente createCliente(ClienteDTO nuevoClienteDTO) {
        Cliente nuevoCliente = new Cliente();
        BeanUtils.copyProperties(nuevoClienteDTO, nuevoCliente);
        return clienteRepository.save(nuevoCliente);
    }

    @Override
    public Cliente updateCliente(Long id, ClienteDTO clienteActualizadoDTO) {
        // Implementa la lógica para actualizar un cliente
        return null;
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
