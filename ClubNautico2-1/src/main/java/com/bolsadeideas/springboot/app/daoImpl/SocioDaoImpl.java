package com.bolsadeideas.springboot.app.daoImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.dao.ISocioDao;
import com.bolsadeideas.springboot.app.model.Barco;
import com.bolsadeideas.springboot.app.model.Socio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class SocioDaoImpl implements ISocioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Socio> obtenerTodosLosSocios() {
		return em.createQuery("from Socio").getResultList();
	}

	@Transactional
	public Socio obtenerSocioPorDni(String dni) {
		return em.find(Socio.class, dni);
	}

	@Transactional
	public void crearSocio(Socio socio) {
		em.persist(socio);
	}

	@Transactional
	public void actualizarSocio(Socio socio) {
		em.merge(socio);
	}

	@Transactional
	public void eliminarSocio(Socio socio) {
		em.remove(socio);
	}

	@Transactional
	public List<Barco> obtenerBarcosDeSocio(String dni) {
		Socio socio = obtenerSocioPorDni(dni);
		if (socio != null) {
			return socio.getBarcos();
		}
		return Collections.emptyList();
	}

	@Transactional
	public void agregarBarcoASocio(String dni, Barco barco) {
		Socio socio = obtenerSocioPorDni(dni);
		if (socio != null) {
			socio.getBarcos().add(barco);
			barco.setSocio(socio);
			em.merge(socio);
			em.merge(barco);
		}
	}
}
