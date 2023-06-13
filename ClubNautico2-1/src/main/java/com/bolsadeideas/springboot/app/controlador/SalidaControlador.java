package com.bolsadeideas.springboot.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.dao.ISalidaDao;
import com.bolsadeideas.springboot.app.model.Salida;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/salida")
public class SalidaControlador {

	@Autowired
	private ISalidaDao salidaDao;

	public SalidaControlador(ISalidaDao salidaDao) {
		this.salidaDao = salidaDao;
	}
	
	//------------------------------------Crud Salida.-----------------------------------------------------

	@GetMapping
	@Operation(summary = "Obtener salidas")
	public List<Salida> obtenerTodasLasSalidas() {
		return salidaDao.obtenerTodasLasSalidas();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener salidas por id")
	public Salida obtenerSalidaPorId(@PathVariable String id) {
		return salidaDao.obtenerSalidaPorId(id);
	}

	@PostMapping
	@Operation(summary = "Crear salida")
	public void crearSalida(@RequestBody Salida salida) {
		salidaDao.crearSalida(salida);
	}
}
