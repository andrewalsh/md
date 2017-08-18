package br.com.md.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.md.entities.Beneficio;
import br.com.md.util.HibernateUtil;

public class BeneficioDAO {
	
	public void salvar(Beneficio beneficio){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(beneficio);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}
		}finally{
			session.close();
		}
	}
	
	public void editar(Beneficio beneficio){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(beneficio);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Beneficio> listar(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficio> beneficios = null;
		try {
			Query query = session.getNamedQuery("Beneficio.listar");
			beneficios = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficios;
	}
	
	public Beneficio buscarPorCodigo(long id){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Beneficio beneficio = null;
		try {
			Query query = session.getNamedQuery("Beneficio.buscarPorCodigo");
			query.setLong("id", id);
			beneficio = (Beneficio) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficio;
	}

}
