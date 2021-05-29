package cl.dci.eshop.model;



import javax.persistence.*;


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
	
	
	
	public Mascota() {
		
	}



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



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getDueño() {
		return Dueño;
	}



	public void setDueño(int dueño) {
		Dueño = dueño;
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	public double getPeso() {
		return peso;
	}



	public void setPeso(double peso) {
		this.peso = peso;
	}



	public String getFoto1() {
		return Foto1;
	}



	public void setFoto1(String foto1) {
		Foto1 = foto1;
	}
	
	
}
