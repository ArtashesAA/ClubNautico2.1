package com.bolsadeideas.springboot.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patron")
public class Patron {

	@Id
	@Column(name = "id")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "No puede contener caracteres especiales")
	@NotNull(message = "El id no puede ser nulo")
	private String id;

	@Column(name = "nombre", nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Solo debe contener letras")
	@NotNull(message = "El nombre no puede ser nulo")
	private String nombre;

	@Column(name = "apellidos", nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Solo debe contener letras")
	@NotNull(message = "El apellido no puede ser nulo")
	private String apellidos;

	@Column(name = "correo", nullable = false)
	@Email
	@NotNull(message = "El correo no puede ser nulo")
	private String correo;

	@OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
	private List<Salida> salidas;

	public Patron() {
		this.salidas = new ArrayList<>();
	}

	public Patron(String id, String nombre, String apellidos, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.salidas = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}

	@Override
	public String toString() {
		return "Patron [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos()
				+ ", getCorreo()=" + getCorreo() + ", getSalidas()=" + getSalidas() + "]";
	}

}
