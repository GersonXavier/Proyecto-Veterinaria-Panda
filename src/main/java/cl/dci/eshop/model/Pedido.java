package cl.dci.eshop.model;

import java.util.Date;

import javax.persistence.*;

import cl.dci.eshop.auth.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id")
    private int id;
   
    @Column
    private String fecha;
   
     @ManyToOne
     @JoinColumn
    private User user;
  
     @Column
     private String estado;
     @ManyToOne
     @JoinColumn
     private Carrito carrito;

    
public Pedido(int id, User user, String fecha) {
		super();
		this.id = id;
		this.user = user;
		this.fecha = fecha;
	}





    
    
    public Pedido() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public 	String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}







	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}







	public Carrito getCarrito() {
		return carrito;
	}







	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}


    

	
	
	
}
