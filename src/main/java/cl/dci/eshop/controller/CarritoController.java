package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Pedido;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.model.ProductoCarrito;
import cl.dci.eshop.model.servicio;
import cl.dci.eshop.repository.CarritoRepository;
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

import java.util.HashSet;
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
    @Autowired PedidoRepository pedidoRepository;

    @PostMapping("/crear/{id}")
    public String agregarProducto(@PathVariable int id){
        System.out.println(id);
        Producto producto = productoRepository.findById(id).orElse(null);
        servicio servicio = servicioRepository.findById(id).orElse(null);
        Carrito carrito = getCurrentUser().getCarrito();
        carrito.addProducto(producto);

        ProductoCarrito pc = new ProductoCarrito(producto,carrito,servicio);
        productoCarritoRepository.save(pc);
        carritoRepository.save(carrito);
        return "redirect:/carrito";
    }
    
    
    @PostMapping("/crearPed/{id}")
    public String agregarPedido(@PathVariable int id){
        System.out.println(id);
        User user = userRepository.findById(id).orElse(null);
      
       
        Pedido pedido = new Pedido();
        
        pedido.addPedido(user);

      
        pedidoRepository.save(pedido);
        
        return "redirect:/pedido";
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
