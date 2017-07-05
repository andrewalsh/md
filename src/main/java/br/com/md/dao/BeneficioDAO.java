package br.com.md.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.md.entities.Beneficiado;
import br.com.md.entities.Beneficio;
import br.com.md.util.HibernateUtil;

public class BeneficioDAO {
	
	public void salvar(Beneficio beneficio, List<Beneficiado> beneficiados){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (int i = 0; i < beneficiados.size(); i++) {
				Beneficiado beneficiado = beneficiados.get(i);
				beneficio.setBeneficiado(beneficiado);
				session.save(beneficio);
			}
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

}
