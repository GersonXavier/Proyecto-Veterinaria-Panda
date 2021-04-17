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
    private int idMoscota;
	@Column
	private String nombre;
	@Column
	private String tipo;
	@Column
	private int idDueño;
	@Column
	private int edad;
	@Column
	private double peso;
	
	public int getidMoscota() {
		return idMoscota;
	}
	public void setidMoscota(int idMoscota) {
		this.idMoscota = idMoscota;
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
	
	public int getidDueño() {
		return idDueño;
	}
	public void setidDueño(int idDueño) {
		this.idDueño = idDueño;
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
