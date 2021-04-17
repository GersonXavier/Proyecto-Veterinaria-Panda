package cl.dci.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import cl.dci.eshop.model.servicio;

@Component
public interface ServicioRepository extends JpaRepository<servicio, Integer>{

}
