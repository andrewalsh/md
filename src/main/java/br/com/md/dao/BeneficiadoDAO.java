package br.com.md.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.md.entities.Beneficiado;
import br.com.md.util.HibernateUtil;

public class BeneficiadoDAO {
	
	public void salvar(Beneficiado beneficiado){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(beneficiado);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			session.close();
		}
	}
	
	public Beneficiado buscarPorID(String nome, String endereco){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Beneficiado beneficiado = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.buascarPorID");
			query.setString("nome", nome);
			query.setString("endereco", endereco);
			beneficiado = (Beneficiado) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> buscarPorNome(String nome){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.buscarPorNome");
			query.setString("nome", "%"+nome+"%");
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> buscarPorBairro(String bairro){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.buscarPorBairro");
			query.setString("bairro", bairro);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}

}
