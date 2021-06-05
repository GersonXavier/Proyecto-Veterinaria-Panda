package cl.dci.eshop.repository;

import cl.dci.eshop.model.Carrito;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
	public Carrito findCarritoByUser(int user);
}
