package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Salida;
import com.bolsadeideas.springboot.app.model.Socio;

public interface IBarcoDao {

	List<Barco> obtenerTodosLosBarcos();

	Barco obtenerBarcoPorMatricula(String matricula);

	void crearBarco(Barco barco);

	void actualizarBarco(Barco barco);

	void eliminarBarco(Barco barco);

	List<Salida> obtenerSalidasDelBarco(String matricula);

	void agregarSalidaAlBarco(String matricula, Salida salida);

	Socio obtenerSocioDelBarco(String matricula);

	void asignarSocioAlBarco(String matricula, Socio socio);
}
