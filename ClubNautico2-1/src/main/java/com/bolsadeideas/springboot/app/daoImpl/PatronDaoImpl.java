package com.bolsadeideas.springboot.app.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.dao.IPatronDao;
import com.bolsadeideas.springboot.app.model.Patron;
import com.bolsadeideas.springboot.app.model.Salida;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PatronDaoImpl implements IPatronDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Patron> obtenerTodosLosPatrones() {
		return em.createQuery("from Patron").getResultList();
	}

	@Transactional
	public Patron obtenerPatronPorId(String id) {
		return em.find(Patron.class, id);
	}

	@Transactional
	public void crearPatron(Patron patron) {
		em.persist(patron);
	}

	@Transactional
	public void actualizarPatron(Patron patron) {
		em.merge(patron);
	}

	@Transactional
	public void eliminarPatron(Patron patron) {
		em.remove(patron);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Salida> obtenerSalidasDePatron(String id) {
		return em.createQuery("select s from Salida s where s.patron.id = :id").setParameter("id", id).getResultList();
	}

	@Transactional
	public void asignarSalidaAPatron(String id, Salida salida) {
		Patron patron = obtenerPatronPorId(id);
		patron.getSalidas().add(salida);
		salida.setPatron(patron);
		em.merge(patron);
		em.merge(salida);
	}
}
