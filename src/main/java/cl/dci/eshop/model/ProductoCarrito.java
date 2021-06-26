package cl.dci.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "producto_carrito")
public class ProductoCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
    
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private servicio servicio;
    @Column
    private int cant;
    @Column 
    private int subtotal;

    public ProductoCarrito() {
    }

    public ProductoCarrito(Producto producto, Carrito carrito, servicio servicio) {
        this.producto = producto;
        this.carrito = carrito;
        this.servicio = servicio;
    }
    
    public void cantTotal(int canti,int pre) {
    	cant = canti;
    	subtotal= pre * cant;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public ProductoCarritoKey getId() {
        return id;
    }

    public void setId(ProductoCarritoKey id) {
        this.id = id;
    }*/

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

	public servicio getServicio() {
		return servicio;
	}

	public void setServicio(servicio servicio) {
		this.servicio = servicio;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	
    
    
}
