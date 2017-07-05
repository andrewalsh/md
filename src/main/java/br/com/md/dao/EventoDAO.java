package br.com.md.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.md.entities.Evento;
import br.com.md.util.HibernateUtil;

public class EventoDAO {
	
	public void salvar(Evento evento){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(evento);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	
	
	public void editar(Evento evento){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(evento);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	
	public Evento buscarPorCodigo(long idEvento){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Evento evento = null;
		try {
			Query query = session.getNamedQuery("Evento.buscarPorCodigo");
			query.setLong("idEvento", idEvento);
			evento = (Evento)query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return evento;
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listar(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Evento> eventos = null;
		try {
			Query query = session.getNamedQuery("Evento.listar");
			eventos = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return eventos;
	}

}
