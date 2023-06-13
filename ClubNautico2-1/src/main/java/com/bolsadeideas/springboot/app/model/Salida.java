package com.bolsadeideas.springboot.app.model;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "salida")
public class Salida {

	@Id
	@Column(name = "id", nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "No puede contener caracteres especiales")
	@NotNull(message = "El id no puede ser nulo")
	private String id;

	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	@FutureOrPresent
	@NotNull(message = "La fecha no puede ser nula")
	private Date fecha;

	@Column(name = "hora", nullable = false)
	@NotNull(message = "La hora no puede ser nula")
	@Positive
	private Integer hora;

	@Column(name = "destino", nullable = false)
	@NotNull(message = "El destino no puede ser nulo")
	private String destino;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patronid", referencedColumnName = "id")
	private Patron patron;

	public Salida() {

	}

	public Salida(String id, Date fecha, Integer hora, String destino, Patron patron) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.destino = destino;
		this.patron = patron;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "Salida [getId()=" + getId() + ", getPatron()=" + getPatron() + ", getFecha()=" + getFecha()
				+ ", getHora()=" + getHora() + ", getDestino()=" + getDestino() + "]";
	}

}
