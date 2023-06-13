package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Socio;

public interface ISocioDao {

	List<Socio> obtenerTodosLosSocios();

	Socio obtenerSocioPorDni(String dni);

	void crearSocio(Socio socio);

	void actualizarSocio(Socio socio);

	void eliminarSocio(Socio socio);

	List<Barco> obtenerBarcosDeSocio(String dni);

	void agregarBarcoASocio(String dni, Barco barco);

}
