package com.bolsadeideas.springboot.app.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bolsadeideas.springboot.app.dao.IPatronDao;
import com.bolsadeideas.springboot.app.model.Patron;
import com.bolsadeideas.springboot.app.model.Salida;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/patron")
public class PatronControlador {

	@Autowired
	private IPatronDao patronDao;

	public PatronControlador(IPatronDao patronDao) {
		this.patronDao = patronDao;
	}

	//------------------------------------Crud Patron.-----------------------------------------------------
	
	@GetMapping
	@Operation(summary = "Obtener patrones")
	public List<Patron> obtenerTodosLosPatrones() {
		return patronDao.obtenerTodosLosPatrones();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener patron por id")
	public Patron obtenerPatronPorId(@PathVariable String id) {
		return patronDao.obtenerPatronPorId(id);
	}

	@PostMapping
	@Operation(summary = "Crear patron")
	public void crearPatron(@RequestBody Patron patron) {
		patronDao.crearPatron(patron);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar patron")
	public void actualizarPatron(@PathVariable String id, @RequestBody Patron patron) {
		Patron patronExistente = patronDao.obtenerPatronPorId(id);
		if (patronExistente != null) {
			patronExistente.setNombre(patron.getNombre());
			patronExistente.setApellidos(patron.getApellidos());
			patronDao.actualizarPatron(patronExistente);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar patron")
	public void eliminarPatron(@PathVariable String id) {
		Patron patron = patronDao.obtenerPatronPorId(id);
		if (patron != null) {
			patronDao.eliminarPatron(patron);
		}
	}

	//--------------------------Crud salidas de patron----------------------------------------------
	
	@GetMapping("/{id}/salidas")
	@Operation(summary = "Obtener salidas de patron")
	public List<Salida> obtenerSalidasDePatron(@PathVariable String id) {
		return patronDao.obtenerSalidasDePatron(id);
	}

	@PostMapping("/{id}/salidas")
	@Operation(summary = "Asignar salida a patron")
	public void asignarSalidaAPatron(@PathVariable String id, @RequestBody Salida salida) {
		patronDao.asignarSalidaAPatron(id, salida);
	}
}
