package com.bolsadeideas.springboot.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.dao.IBarcoDao;
import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Salida;
import com.bolsadeideas.springboot.app.model.Socio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/barco")
public class BarcoControlador {

	@Autowired
	private IBarcoDao barcoDao;

//------------------------------------Crud Barco.-----------------------------------------------------

	@PostMapping("/crear")
	@Operation(summary = "Crear un barco")
	public String crearBarco(@ModelAttribute("barco") Barco barco) {
		barcoDao.crearBarco(barco);
		return "redirect:/barcos";
	}

	@PutMapping("/{matricula}/editar")
	@Operation(summary = "Actualizar un barco")
	public String actualizarBarco(@PathVariable("matricula") String matricula, @ModelAttribute("barco") Barco barco) {
		barco.setMatricula(matricula);
		barcoDao.actualizarBarco(barco);
		return "redirect:/barcos";
	}

	@DeleteMapping("/{matricula}/eliminar")
	@Operation(summary = "Eliminar un barco")
	public String eliminarBarco(@PathVariable("matricula") String matricula) {
		Barco barco = barcoDao.obtenerBarcoPorMatricula(matricula);
		barcoDao.eliminarBarco(barco);
		return "redirect:/barcos";
	}

	@GetMapping("")
	@Operation(summary = "Obtener todos los barcos")
	public String obtenerTodosLosBarcos(Model model) {
		List<Barco> barcos = barcoDao.obtenerTodosLosBarcos();
		model.addAttribute("barcos", barcos);
		return "barcos";
	}

	@GetMapping("/{matricula}")
	@Operation(summary = "Obtener un barco")
	public String obtenerBarcoPorMatricula(@PathVariable("matricula") String matricula, Model model) {
		Barco barco = barcoDao.obtenerBarcoPorMatricula(matricula);
		model.addAttribute("barco", barco);
		return "barco";
	}

//--------------------------Crud salidas y socios de barco----------------------------------------------

	@GetMapping("/{matricula}/salidas")
	@Operation(summary = "Obtener salidas de barco")
	public String obtenerSalidasDelBarco(@PathVariable("matricula") String matricula, Model model) {
		List<Salida> salidas = barcoDao.obtenerSalidasDelBarco(matricula);
		model.addAttribute("salidas", salidas);
		return "salidas";
	}

	@GetMapping("/{matricula}/socio")
	@Operation(summary = "Obtener socio de barco")
	public String obtenerSocioDelBarco(@PathVariable("matricula") String matricula, Model model) {
		Socio socio = barcoDao.obtenerSocioDelBarco(matricula);
		model.addAttribute("socio", socio);
		return "socio";
	}

	@PostMapping("/{matricula}/salidas")
	@Operation(summary = "Agregar salida a barco")
	public String agregarSalidaAlBarco(@PathVariable("matricula") String matricula,
			@ModelAttribute("salida") Salida salida, Model model) {
		barcoDao.agregarSalidaAlBarco(matricula, salida);
		return "redirect:/barcos/" + matricula + "/salidas";
	}

	@PostMapping("/{matricula}/socio")
	@Operation(summary = "Asignar socio a barco")
	public String asignarSocioAlBarco(@PathVariable("matricula") String matricula, @ModelAttribute("socio") Socio socio,
			Model model) {
		barcoDao.asignarSocioAlBarco(matricula, socio);
		return "redirect:/barcos/" + matricula + "/socio";
	}
}
