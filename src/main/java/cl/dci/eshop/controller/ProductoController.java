package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Consultas;
import cl.dci.eshop.model.Mascota;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.model.ProductoCarrito;
import cl.dci.eshop.model.servicio;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.ConsultaRepository;
import cl.dci.eshop.repository.MascotaRepository;
import cl.dci.eshop.repository.ProductoCarritoRepository;
import cl.dci.eshop.repository.ProductoRepository;
import cl.dci.eshop.repository.ServicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoJpaRepository;
    @Autowired
    private ProductoCarritoRepository productoCarritoRepository;
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ServicioRepository serviciojpaRepository;
    @Autowired
    private MascotaRepository mascotajpaRepository;
    @Autowired
    private ConsultaRepository consultajpaRepository;



    @PreAuthorize("hasAuthority('producto:write')")
    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute("producto") Producto producto, @RequestParam("file") MultipartFile imagen,
    		@RequestParam("file2") MultipartFile imagen2,@RequestParam("file3") MultipartFile imagen3){
    
    	if(!imagen.isEmpty()) {
    		Path DirectorioImagenes = Paths.get("src//main//resources//static/images");
    		String RutaAbsoluta = DirectorioImagenes.toFile().getAbsolutePath();
    		
    		try {
				byte[] BytesImg = imagen.getBytes();
				
				Path rutaCompleta = Paths.get(RutaAbsoluta + "//" + imagen.getOriginalFilename());
				
				Files.write(rutaCompleta, BytesImg);
				
				producto.setFoto1(imagen.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	if(!imagen2.isEmpty()) {
    		Path DirectorioImagenes = Paths.get("src//main//resources//static/images");
    		String RutaAbsoluta = DirectorioImagenes.toFile().getAbsolutePath();
    		
    		try {
				byte[] BytesImg = imagen2.getBytes();
				
				Path rutaCompleta = Paths.get(RutaAbsoluta + "//" + imagen2.getOriginalFilename());
				
				Files.write(rutaCompleta, BytesImg);
				
				producto.setFoto2(imagen2.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	if(!imagen3.isEmpty()) {
    		Path DirectorioImagenes = Paths.get("src//main//resources//static/images");
    		String RutaAbsoluta = DirectorioImagenes.toFile().getAbsolutePath();
    		
    		try {
				byte[] BytesImg = imagen3.getBytes();
				
				Path rutaCompleta = Paths.get(RutaAbsoluta + "//" + imagen3.getOriginalFilename());
				
				Files.write(rutaCompleta, BytesImg);
				
				producto.setFoto3(imagen3.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
        productoJpaRepository.save(producto);
        return "redirect:/admin/productos";
    }
    
    
    @PreAuthorize("hasAuthority('producto:write')")
    @PostMapping("/crearSer")
    public String crearServicio(@ModelAttribute("servicio") servicio servicio){

        serviciojpaRepository.save(servicio);
        return "redirect:/admin/servicios";
    }
    

   
    @PostMapping("/crearMas")
    public String crearMascota(@ModelAttribute("mascota") Mascota mascota, @RequestParam("fotoMas") MultipartFile masco){
    	
    	if(!masco.isEmpty()) {
    		Path DirectorioImagenes = Paths.get("src//main//resources//static/images");
    		String RutaAbsoluta = DirectorioImagenes.toFile().getAbsolutePath();
    		
    		try {
				byte[] BytesImg = masco.getBytes();
				
				Path rutaCompleta = Paths.get(RutaAbsoluta + "//" + masco.getOriginalFilename());
				
				Files.write(rutaCompleta, BytesImg);
				
				mascota.setFoto1(masco.getOriginalFilename());
				mascota.setUser(getCurrentUser());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}

        mascotajpaRepository.save(mascota);
        return "redirect:/admin/mascotas";
    }

    
   
    @PostMapping("/crearCon")
    public String crearConsulta(@RequestParam("mascota") int idmas, @RequestParam("descripcion") String des){
    	Consultas con = new Consultas();
    	Mascota mas = mascotajpaRepository.findById(idmas).orElse(null);
    	 Date actual = new Date();
 		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
 		formatoFecha.format(actual);
    	con.setDescripcion(des);
    	con.setMascota(mas);
    	con.setFecha(formatoFecha.format(actual));
    	con.setUser(getCurrentUser());
    	
    	

        consultajpaRepository.save(con);
        return "redirect:/consultaMas";
    }
    

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;

        if (principal instanceof User) {
            user = ((User) principal);
        }
        return user;
    }


    @PreAuthorize("hasAuthority('producto:delete')")
    @PostMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable int id){
        List<ProductoCarrito> pcs = productoCarritoRepository.findByProducto(productoJpaRepository.findById(id).orElse(null));
        if (!pcs.isEmpty()) {
            for (ProductoCarrito pc : pcs) {
                Carrito c = pc.getCarrito();
                Producto p = pc.getProducto();
                c.deleteProducto(p);
                carritoRepository.save(c);
                productoCarritoRepository.delete(pc);
            }
        }
        productoJpaRepository.deleteById(id);
        return "redirect:/admin/productos";
    }
    
    @PreAuthorize("hasAuthority('producto:delete')")
    @PostMapping("/deleteSer/{id}")
    public String eliminarServicio(@PathVariable int id){
        List<ProductoCarrito> pcs = productoCarritoRepository.findByServicio(serviciojpaRepository.findById(id).orElse(null));
        if (!pcs.isEmpty()) {
            for (ProductoCarrito pc : pcs) {
                Carrito c = pc.getCarrito();
                servicio s = pc.getServicio();
                c.deleteServicio(s);
                carritoRepository.save(c);
                productoCarritoRepository.delete(pc);
            }
        }
        serviciojpaRepository.deleteById(id);
        return "redirect:/admin/servicios";
    }
  
    
    @PreAuthorize("hasAuthority('producto:update')")
    @GetMapping("/update/{id}")
    public String getEditarProducto(@PathVariable int id, Model modelo){

        Producto producto = productoJpaRepository.findById(id).orElse(null);
        modelo.addAttribute("producto", producto);

        return "/admin/producto-update";
    }
    
    @PreAuthorize("hasAuthority('producto:update')")
    @GetMapping("/updateSer/{id}")
    public String getEditarServicio(@PathVariable int id, Model modelo){

        servicio servicio = serviciojpaRepository.findById(id).orElse(null);
        modelo.addAttribute("servicio", servicio);

        return "/admin/servicio-update";
    }

    @PreAuthorize("hasAuthority('producto:update')")
    @PostMapping("/updateSer")
    public String editarServicio(@ModelAttribute("servicio") servicio servicio){
        serviciojpaRepository.save(servicio);
        return "redirect:/admin/servicios";
    }

    @PreAuthorize("hasAuthority('producto:update')")
    @PostMapping("/update")
    public String editarProducto(@ModelAttribute("producto") Producto producto){
        productoJpaRepository.save(producto);
        return "redirect:/admin/productos";
    }



}
