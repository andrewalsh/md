package br.com.md.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.md.dao.BeneficiadoDAO;

@ManagedBean
@ViewScoped

public class EtiquetaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String bairro;
	private String rua;
	private List<String> ruas;
	private List<String> bairros;
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public List<String> getRuas() {
		return ruas;
	}
	public void setRuas(List<String> ruas) {
		this.ruas = ruas;
	}
	public List<String> getBairros() {
		return bairros;
	}
	public void setBairros(List<String> bairros) {
		this.bairros = bairros;
	}
	
	public void listar(){
		listarBairros();
		listarRuas();
	}
	
	public void imprimir() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/md/etiquetasServlet?bairro=" + bairro+"&rua="+rua);
	}
	
	public void relatorioAcaoSocial() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/md/acaoSocialServlet");
	}
	
	private void listarBairros(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			bairros = dao.listarBairrosCadastrados();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	private void listarRuas(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			ruas = dao.listarRuasCadastrados();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
