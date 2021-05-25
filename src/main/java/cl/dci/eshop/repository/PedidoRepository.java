package cl.dci.eshop.repository;

import cl.dci.eshop.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findPedidoByUser(int user);
}
