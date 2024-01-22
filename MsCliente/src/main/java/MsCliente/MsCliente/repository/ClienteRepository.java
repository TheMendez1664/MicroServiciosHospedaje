package MsCliente.MsCliente.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import MsCliente.MsCliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes agregar métodos personalizados del repositorio si es necesario
}
