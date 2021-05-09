package cl.dci.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "mascota")
public class Mascota {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	@Column
	private String nombre;
	@Column
	private String tipo;
	@Column
	private int Dueño;
	@Column
	private int edad;
	@Column
	private double peso;
	@Column
	private String Foto1;
	
	
	
	
	
	
	public String getFoto1() {
		return Foto1;
	}
	public void setFoto1(String foto1) {
		Foto1 = foto1;
	}
	public Mascota() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mascota(int id, String nombre, String tipo, int Dueño, int edad, double peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.Dueño = Dueño;
		this.edad = edad;
		this.peso = peso;
	}
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String gettipo() {
		return tipo;
	}
	public void settipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getDueño() {
		return Dueño;
	}
	public void setDueño(int Dueño) {
		this.Dueño = Dueño;
	}
	
	public int getedad() {
		return edad;
	}
	public void setedad(int edad) {
		this.edad = edad;
	}
	
	public double getpeso() {
		return peso;
	}
	public void setpeso(double peso) {
		this.peso = peso;
	}
}
