package cl.dci.eshop.repository;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findPedidoByUser(User user);
	public List<Pedido> findPedidoByCarrito(Carrito carrito);
	
}
