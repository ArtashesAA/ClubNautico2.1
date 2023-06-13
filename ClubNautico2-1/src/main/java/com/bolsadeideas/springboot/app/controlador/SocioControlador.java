package com.bolsadeideas.springboot.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.dao.ISocioDao;
import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Socio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/socio")
public class SocioControlador {

	@Autowired
	private ISocioDao socioDao;

	public SocioControlador(ISocioDao socioDao) {
		this.socioDao = socioDao;
	}

	//------------------------------------Crud Socio.-----------------------------------------------------
	
	@GetMapping
	@Operation(summary = "Obtener socios")
	public List<Socio> obtenerTodosLosSocios() {
		return socioDao.obtenerTodosLosSocios();
	}

	@GetMapping("/{dni}")
	@Operation(summary = "Obtener socio por dni")
	public Socio obtenerSocioPorDni(@PathVariable String dni) {
		return socioDao.obtenerSocioPorDni(dni);
	}

	@PostMapping
	@Operation(summary = "Crear socio")
	public void crearSocio(@RequestBody Socio socio) {
		socioDao.crearSocio(socio);
	}

	@PutMapping("/{dni}")
	@Operation(summary = "Actualizar socio")
	public void actualizarSocio(@PathVariable String dni, @RequestBody Socio socio) {
		Socio socioExistente = socioDao.obtenerSocioPorDni(dni);
		if (socioExistente != null) {
			socioExistente.setNombre(socio.getNombre());
			socioExistente.setApellidos(socio.getApellidos());
			socioDao.actualizarSocio(socioExistente);
		}
	}

	@DeleteMapping("/{dni}")
	@Operation(summary = "Eliminar socio")
	public void eliminarSocio(@PathVariable String dni) {
		Socio socio = socioDao.obtenerSocioPorDni(dni);
		if (socio != null) {
			socioDao.eliminarSocio(socio);
		}
	}

	//--------------------------Crud barcos de socio----------------------------------------------
	
	@GetMapping("/{dni}/barcos")
	@Operation(summary = "Obtener barcos de socio")
	public List<Barco> obtenerBarcosDeSocio(@PathVariable String dni) {
		return socioDao.obtenerBarcosDeSocio(dni);
	}

	@PostMapping("/{dni}/barcos")
	@Operation(summary = "Agregar barco a socio")
	public void agregarBarcoASocio(@PathVariable String dni, @RequestBody Barco barco) {
		socioDao.agregarBarcoASocio(dni, barco);
	}
}