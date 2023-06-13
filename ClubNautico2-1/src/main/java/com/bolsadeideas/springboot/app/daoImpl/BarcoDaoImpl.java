package com.bolsadeideas.springboot.app.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.dao.IBarcoDao;
import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Salida;
import com.bolsadeideas.springboot.app.model.Socio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class BarcoDaoImpl implements IBarcoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Barco> obtenerTodosLosBarcos() {
		return em.createQuery("from Barco").getResultList();
	}

	@Override
	@Transactional
	public Barco obtenerBarcoPorMatricula(String matricula) {
		return em.find(Barco.class, matricula);
	}

	@Override
	@Transactional
	public void crearBarco(Barco barco) {
		em.persist(barco);
	}

	@Override
	@Transactional
	public void actualizarBarco(Barco barco) {
		em.merge(barco);
	}

	@Override
	@Transactional
	public void eliminarBarco(Barco barco) {
		em.remove(barco);
	}

	@Override
	@Transactional
	public List<Salida> obtenerSalidasDelBarco(String matricula) {
		Barco barco = obtenerBarcoPorMatricula(matricula);
		return barco.getSalidas();
	}

	@Override
	@Transactional
	public void agregarSalidaAlBarco(String matricula, Salida salida) {
		Barco barco = obtenerBarcoPorMatricula(matricula);
		barco.getSalidas().add(salida);
		em.merge(barco);
	}

	@Override
	@Transactional
	public Socio obtenerSocioDelBarco(String matricula) {
		Barco barco = obtenerBarcoPorMatricula(matricula);
		return barco.getSocio();
	}

	@Override
	@Transactional
	public void asignarSocioAlBarco(String matricula, Socio socio) {
		Barco barco = obtenerBarcoPorMatricula(matricula);
		barco.setSocio(socio);
		em.merge(barco);
	}
}
