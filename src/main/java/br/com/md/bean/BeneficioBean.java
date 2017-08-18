package br.com.md.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.BeneficioDAO;
import br.com.md.entities.Beneficio;
import br.com.md.util.FacesUtilBean;

@ManagedBean
@ViewScoped
public class BeneficioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Beneficio beneficio;
	private List<Beneficio> beneficios;

	public Beneficio getBeneficio() {
		if (this.beneficio == null) {
			this.beneficio = new Beneficio();
		}
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}
	
	
	public void listar(){
		try {
			carregar();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(Beneficio beneficio){
		this.beneficio = beneficio;
	}
	
	
	public void salvar(){
		try {
			BeneficioDAO dao = new BeneficioDAO();
			dao.salvar(beneficio);
			FacesUtilBean.msgInfo("Beneficio cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ");
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try {
			BeneficioDAO dao = new BeneficioDAO();
			dao.editar(beneficio);
			FacesUtilBean.msgInfo("Beneficio atualizado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ");
			e.printStackTrace();
		}
	}
	
	private void carregar() {
		try {
			BeneficioDAO dao = new BeneficioDAO();
			beneficios = dao.listar();
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro(
					"Ocorreu um erro ao listar os Eventos cadastrados no Sistema! ERRO: [ " + e.getMessage() + " ]");
			e.printStackTrace();
		}
	}

}
