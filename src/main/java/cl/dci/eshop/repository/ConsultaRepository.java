package cl.dci.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.dci.eshop.model.Consultas;

public interface ConsultaRepository extends JpaRepository<Consultas, Integer> {

}
