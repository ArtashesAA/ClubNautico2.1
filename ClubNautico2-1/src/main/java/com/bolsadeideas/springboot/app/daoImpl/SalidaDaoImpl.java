package com.bolsadeideas.springboot.app.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.dao.ISalidaDao;
import com.bolsadeideas.springboot.app.model.Patron;
import com.bolsadeideas.springboot.app.model.Salida;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class SalidaDaoImpl implements ISalidaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Salida> obtenerTodasLasSalidas() {
		return em.createQuery("from Salida").getResultList();
	}

	@Transactional
	public Salida obtenerSalidaPorId(String id) {
		return em.find(Salida.class, id);
	}

	@Transactional
	public void crearSalida(Salida salida) {
		em.persist(salida);
	}

	@Transactional
	public void actualizarSalida(Salida salida) {
		em.merge(salida);
	}

	@Transactional
	public void eliminarSalida(Salida salida) {
		em.remove(salida);
	}

	@Transactional
	public Patron obtenerPatronDeSalida(String id) {
		Salida salida = obtenerSalidaPorId(id);
		if (salida != null) {
			return salida.getPatron();
		}
		return null;
	}

	@Transactional
	public void asignarPatronASalida(String id, Patron patron) {
		Salida salida = obtenerSalidaPorId(id);
		if (salida != null) {
			salida.setPatron(patron);
			em.merge(salida);
		}
	}
}
