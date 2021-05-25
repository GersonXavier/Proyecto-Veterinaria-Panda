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
   
     @Column
    private int user;
    

    
public Pedido(int id, int user, String fecha) {
		super();
		this.id = id;
		this.user = user;
		this.fecha = fecha;
	}




public void addPedido(User user) {
		
		this.user = user.getId();
		Date actual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		fecha = formatoFecha.format(actual);
		
		
		
		
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

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
	
	
	
}
