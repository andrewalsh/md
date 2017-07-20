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
	
	public void editar(Beneficiado beneficiado){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(beneficiado);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			session.close();
		}
	}
	
	public Beneficiado buscarPorID(long idBeneficiado){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Beneficiado beneficiado = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.buascarPorID");
			query.setLong("idBeneficiado", idBeneficiado);
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
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> listarTelefonesCelularesPorBairro(String bairro){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.listarTelefonesCelularesPorBairro");
			query.setString("bairro", bairro);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> listarTelefonesCelular1(String bairro){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		
		try {
			Query query = session.getNamedQuery("Beneficiado.listarTelefone1");
			query.setString("bairro", bairro);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> listarTelefonesCelular2(String bairro){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		
		try {
			Query query = session.getNamedQuery("Beneficiado.listarTelefone2");
			query.setString("bairro", bairro);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarBairrosCadastrados(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<String> bairros = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.listarBairros");
			bairros = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return bairros;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> verificaSeExiste(String nome){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.verificaSeExiste");
			query.setString("nome", nome);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficiado> listar(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Beneficiado> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.listar");
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarPrimeiroNomePorBairro(String bairro){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<String> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.listarPrimeiroNomePorBairro");
			query.setString("bairro", bairro);
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarPrimeiroNome(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<String> beneficiados = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.listarPrimeiroNome");
			beneficiados = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiados;
	}
	
	public Beneficiado verificarChave(String nome, String endereco, String numero){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Beneficiado beneficiado = null;
		try {
			Query query = session.getNamedQuery("Beneficiado.verificarChave");
			query.setString("nome", nome);
			query.setString("endereco", endereco);
			query.setString("numero", numero);
			beneficiado = (Beneficiado) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return beneficiado;
	}

}
