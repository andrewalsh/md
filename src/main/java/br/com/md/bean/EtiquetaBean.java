package br.com.md.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped

public class EtiquetaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String bairro;
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public void imprimir() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/md/etiquetasServlet?bairro=" + bairro);
	}
	
	public void relatorioAcaoSocial() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/md/acaoSocialServlet");
	}
}
