package br.com.md.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.md.entities.Usuario;
import br.com.md.util.HibernateUtil;

public class UsuarioDAO {
	
	public void salvar(Usuario usuario){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(usuario);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
				throw e;
			}
		}finally{
			session.close();
		}
	}
	
	public void atualzar(Usuario usuario){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(usuario);
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction != null){
				transaction.rollback();
			}
		}finally{
			session.close();
		}
	}
	
	public Usuario buscarPorCodigo(long codigo){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = null;
		try {
			Query query = session.getNamedQuery("Usuario.buscarPorCodigo");
			query.setLong("codigo", codigo);
			usuario = (Usuario) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return usuario;
	}
	
	public Usuario autenticar(Usuario u){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = null;
		try {
			Query query = session.getNamedQuery("Usuario.autenticar");
			query.setString("login",u.getLogin());
			query.setString("senha", u.getSenha());
			usuario = (Usuario) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return usuario;
	}
	
	public String cpf(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		String cpf = null;
		try {
			Query query = session.getNamedQuery("Usuario.cpf");
			cpf =(String) query.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return cpf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Usuario> usuarios = null;
		try {
			Query query = session.getNamedQuery("Usuario.listar");
			usuarios = query.list();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			session.close();
		}return usuarios;
	}

}
