package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Consultas;
import cl.dci.eshop.model.Historial;
import cl.dci.eshop.model.Pedido;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.model.ProductoCarrito;
import cl.dci.eshop.model.servicio;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.ConsultaRepository;
import cl.dci.eshop.repository.HistorialRepository;
import cl.dci.eshop.repository.PedidoRepository;
import cl.dci.eshop.repository.ProductoCarritoRepository;
import cl.dci.eshop.repository.ProductoRepository;
import cl.dci.eshop.repository.ServicioRepository;
import cl.dci.eshop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private ProductoCarritoRepository productoCarritoRepository;
    @Autowired 
    private UserRepository userRepository;
    @Autowired 
    private PedidoRepository pedidoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private HistorialRepository HistorialRepository;

    @PostMapping("/crear/{id}")
    public String agregarProducto(@PathVariable int id){
        System.out.println(id);
        Producto producto = productoRepository.findById(id).orElse(null);
        int pre = producto.getPrecio();
        servicio servicio = servicioRepository.findById(id).orElse(null);
        Carrito carrito = getCurrentUser().getCarrito();
        carrito.addProducto(producto);

        ProductoCarrito pc = new ProductoCarrito(producto,carrito,servicio);
        pc.setSubtotal(pre);
        pc.setCant(1);
        productoCarritoRepository.save(pc);
        carritoRepository.save(carrito);
        return "redirect:/carrito";
    }
    
    @PostMapping("/cantidad")
    public String agregarCantidad(@RequestParam("idpro") int idpro, @RequestParam("cant") int cant){
        System.out.println(idpro);
        Producto producto = productoRepository.findById(idpro).orElse(null);
      
			List<ProductoCarrito>  pc =productoCarritoRepository.findByProducto(producto);
			System.out.print(pc);
			for (int i = 0; i < pc.size(); i++) {
				if(pc.get(i).getProducto().getId()== idpro) {
					int pre = pc.get(i).getProducto().getPrecio();
					pc.get(i).cantTotal(cant, pre);
					
						
					
					
						productoCarritoRepository.save(pc.get(i));
						
				       
					
				}
				
			}
		Carrito carrito = getCurrentUser().getCarrito();
       
		
				        carrito.cantPrecio(producto);
       
      carritoRepository.save(carrito);
						 
        

      
        return "redirect:/carrito";
    }
    
    
    @PostMapping("/crearPed/{id}")
    public String agregarPedido(@PathVariable int id){
        System.out.println(id);
        User user = userRepository.findById(id).orElse(null);
        Carrito carrito = getCurrentUser().getCarrito();
      
        Date actual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		formatoFecha.format(actual);
		
        Pedido pedido = new Pedido();
        pedido.setUser(user);
        pedido.setFecha(formatoFecha.format(actual));
        pedido.setEstado("En espera");
        pedido.setCarrito(carrito);

      
        pedidoRepository.save(pedido);
        
        return "redirect:/pedido";
    }
    
    @PostMapping("/archivar/{id}")
    public String archivar(@PathVariable int id){
        System.out.println(id);
       Consultas consulta = consultaRepository.findById(id).orElse(null);
        Historial his = new Historial();
        his.setDescripcion(consulta.getDescripcion());
        his.setMascota(consulta.getMascota());
        
        HistorialRepository.save(his);
        
        
        
        return "redirect:/historial";
    }
    
    
    @PreAuthorize("hasAuthority('producto:update')")
    @PostMapping("/confirma/{id}")
    public String ConfirmaPed(Model modelo, @PathVariable("id") int idcar) {
       

        Pedido pedido = pedidoRepository.findById(idcar).orElse(null);
        
        pedido.setEstado("Activo");
        pedidoRepository.save(pedido);
        
        return "redirect:/admin/pedidos";
    }
    


    @PreAuthorize("hasAuthority('carrito:manage')")
    @PostMapping(path = "{id}")
    public String eliminarProducto(@PathVariable int id){

        System.out.println(id);
        Carrito carrito = getCurrentUser().getCarrito();

        ProductoCarrito pc = productoCarritoRepository.findById(id).orElse(null);
        Producto producto = pc.getProducto();

        carrito.deleteProducto(producto);

        productoCarritoRepository.delete(pc);
        carritoRepository.save(carrito);

        return "redirect:/carrito";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;

        if (principal instanceof User) {
            user = ((User) principal);
        }
        return user;
    }

}
