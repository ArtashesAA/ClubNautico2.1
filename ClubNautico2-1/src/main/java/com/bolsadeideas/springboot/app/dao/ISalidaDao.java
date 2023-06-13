package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.model.Patron;
import com.bolsadeideas.springboot.app.model.Salida;

public interface ISalidaDao {

	List<Salida> obtenerTodasLasSalidas();

	Salida obtenerSalidaPorId(String id);

	void crearSalida(Salida salida);

	void actualizarSalida(Salida salida);

	void eliminarSalida(Salida salida);

	Patron obtenerPatronDeSalida(String id);

	void asignarPatronASalida(String id, Patron patron);
}
