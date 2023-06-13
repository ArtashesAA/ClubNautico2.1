package com.bolsadeideas.springboot.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "barco")
public class Barco {

	@Id
	@Column(name = "matricula", nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "No puede contener caracteres especiales")
	@NotNull(message = "La matrícula no puede ser nula.")
	private String matricula;

	@Column(name = "nombre", nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "No puede contener caracteres especiales")
	@NotNull(message = "El nombre no puede ser nulo.")
	private String nombre;

	@Column(name = "numAmarre")
	@Positive
	private Integer numAmarre;

	@Column(name = "cuota")
	@Positive
	private Float cuota;

	@Column(name = "capacidad")
	@Positive
	private Integer capacidad;

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "socio_dni", referencedColumnName = "dni")
	private Socio socio;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Barco_Salida", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "matricula") })
	private List<Salida> salidas;

	public Barco() {
		this.salidas = new ArrayList<>();
	}

	public Barco(String matricula, String nombre, Integer numAmarre, Float cuota, Integer capacidad, Socio socio) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.numAmarre = numAmarre;
		this.cuota = cuota;
		this.capacidad = capacidad;
		this.socio = socio;
		this.salidas = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getNumAmarre() {
		return numAmarre;
	}

	public void setNumAmarre(Integer numAmarre) {
		this.numAmarre = numAmarre;
	}

	public Float getCuota() {
		return cuota;
	}

	public void setCuota(Float cuota) {
		this.cuota = cuota;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public List<Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}

	@Override
	public String toString() {
		return "Barco [getNombre()=" + getNombre() + ", getCapacidad()=" + getCapacidad() + ", getMatricula()="
				+ getMatricula() + ", getNumAmarre()=" + getNumAmarre() + ", getCuota()=" + getCuota()
				+ ", getSalidas()=" + getSalidas() + "]";
	}

}
