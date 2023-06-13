package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.model.Patron;
import com.bolsadeideas.springboot.app.model.Salida;

public interface IPatronDao {

	List<Patron> obtenerTodosLosPatrones();

	Patron obtenerPatronPorId(String id);

	void crearPatron(Patron patron);

	void actualizarPatron(Patron patron);

	void eliminarPatron(Patron patron);

	List<Salida> obtenerSalidasDePatron(String id);

	void asignarSalidaAPatron(String id, Salida salida);

}
