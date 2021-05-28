package cl.dci.eshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private int precio;
  
    @Column
    private String marca;
    @Column
    private String proveedor;
    @Column
    private String descripcionSim;
    @Column
    private String descripciomHtml;
    @Column
    private String foto1; 
    @Column
    private String foto2;
    @Column
    private String foto3;
    @Column
    private int stock;
    


    /*@ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private List<Carrito> carritos;*/

 

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getDescripcionSim() {
		return descripcionSim;
	}

	public void setDescripcionSim(String descripcionSim) {
		this.descripcionSim = descripcionSim;
	}

	public String getDescripciomHtml() {
		return descripciomHtml;
	}

	public void setDescripciomHtml(String descripciomHtml) {
		this.descripciomHtml = descripciomHtml;
	}

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}
	
	

	@OneToMany(mappedBy = "producto", cascade = CascadeType.MERGE)
    private List<ProductoCarrito> productoCarritos;

    public Producto(){
        //this.carritos = new ArrayList<>();
        this.productoCarritos = new ArrayList<>();
    }

    public Producto(String nombre, int precio, int stock){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        
        //this.carritos = new ArrayList<>();
        this.productoCarritos = new ArrayList<>();
    }

    public List<ProductoCarrito> getProductoCarritos() {
        return productoCarritos;
    }

    public void setProductoCarritos(List<ProductoCarrito> productoCarritos) {
        this.productoCarritos = productoCarritos;
    }
/*
    public void addCarrito(Carrito carrito){
        this.carritos.add(carrito);
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
    
  

	@Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio + '\'' +
                 
                  ", marca=" + marca + '\'' +
                  ", proveedor=" + proveedor + '\'' +
                  ", descripcionSim=" + descripcionSim + '\'' +
                  ", descricionHtml=" + descripciomHtml + '\'' +
                  ", foto1=" + foto1 + '\'' +
                  ", foto2=" + foto2 + '\'' +
                  ", foto3=" + foto3 + '\'' +
                   ", stock=" + stock +
                //", carritos=" + carritos +
                '}';
    }
}
