package br.com.md.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.dao.BeneficioDAO;
import br.com.md.dao.EventoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.Beneficio;
import br.com.md.entities.Evento;
import br.com.md.util.FacesUtilBean;

@ManagedBean
@ViewScoped
public class BeneficioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Beneficio beneficio;
	private List<Beneficio> beneficios;
	private List<Beneficiado> beneficiados;
	private List<Beneficiado> beneficiadosSelecionados;
	private List<Evento> eventos;

	public Beneficio getBeneficio() {
		if (this.beneficio == null) {
			this.beneficio = new Beneficio();
			this.beneficio.setBeneficiado(new Beneficiado());
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

	public List<Beneficiado> getBeneficiados() {
		return beneficiados;
	}

	public void setBeneficiados(List<Beneficiado> beneficiados) {
		this.beneficiados = beneficiados;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Beneficiado> getBeneficiadosSelecionados() {
		return beneficiadosSelecionados;
	}

	public void setBeneficiadosSelecionados(List<Beneficiado> beneficiadosSelecionados) {
		this.beneficiadosSelecionados = beneficiadosSelecionados;
	}

	public void carregar() {
		try {
			//EventoDAO edao = new EventoDAO();
			BeneficiadoDAO bdao = new BeneficiadoDAO();

			beneficiados = bdao.listar();
			//eventos = edao.listar();
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro(
					"Ocorreu um erro ao listar os Eventos cadastrados no Sistema! ERRO: [ " + e.getMessage() + " ]");
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			BeneficioDAO dao = new BeneficioDAO();
			dao.salvar(beneficio, beneficiadosSelecionados);
			FacesUtilBean.msgInfo("Beneficios cadastrados com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ");
			e.printStackTrace();
		}
	}

	public void adicionar(Beneficiado beneficiado) {
		if (this.beneficiadosSelecionados == null) {
			this.beneficiadosSelecionados = new ArrayList<>();
		}

		this.beneficiadosSelecionados.add(beneficiado);

		System.out.println(beneficiadosSelecionados.size());
		for (Beneficiado bene : beneficiadosSelecionados) {
			System.out.println(bene.toString());
		}
	}

	// @SuppressWarnings("unused")
	public void remover(Beneficiado beneficiado) {
		for (int i = 0; i < this.beneficiadosSelecionados.size(); i++) {
			if (this.beneficiadosSelecionados.get(i).getIdBeneficiado() == beneficiado.getIdBeneficiado()) {
				this.beneficiadosSelecionados.remove(i);
			}
		}
	}

}
