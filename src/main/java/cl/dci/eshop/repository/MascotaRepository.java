package cl.dci.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import cl.dci.eshop.model.Mascota;

@Component
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

}
