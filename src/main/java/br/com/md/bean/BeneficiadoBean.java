package br.com.md.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.BeneficiadoID;
import br.com.md.entities.Logradouro;
import br.com.md.util.FacesUtilBean;

@ManagedBean
@ViewScoped
public class BeneficiadoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Beneficiado beneficiado;
	private List<Beneficiado> listaBeneficiado;
	private String nome;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String parametroCampo;
	private String valorCampo;
	
	public Beneficiado getBeneficiado() {
		if(this.beneficiado == null){
			this.beneficiado = new Beneficiado();
		}
		return beneficiado;
	}
	public void setBeneficiado(Beneficiado beneficiado) {
		this.beneficiado = beneficiado;
	}
	public List<Beneficiado> getListaBeneficiado() {
		return listaBeneficiado;
	}
	public void setListaBeneficiado(List<Beneficiado> listaBeneficiado) {
		this.listaBeneficiado = listaBeneficiado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getParametroCampo() {
		return parametroCampo;
	}
	public void setParametroCampo(String parametroCampo) {
		this.parametroCampo = parametroCampo;
	}
	public String getValorCampo() {
		return valorCampo;
	}
	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}
	
	
	public void salvar(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			beneficiado.setId(new BeneficiadoID(nome, new Logradouro(endereco, numero, bairro, cidade, uf, cep)));
			dao.salvar(beneficiado);
			FacesUtilBean.msgInfo("Beneficiado(a) cadastrado(a) com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar cadastrar o(a) beneficiado(a): [ "+e.getMessage()+" ]");
		}
	}
	
	public void verificarID(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			this.beneficiado = null;
			this.beneficiado = dao.buscarPorID(nome, endereco);
			if(!this.beneficiado.equals(null)){
				FacesUtilBean.msgAlert("O(a) beneficiado(a) já possui cadastro no sistema! Verifique se os dados estão atualizados.");
			}
		} catch (RuntimeException e) {
			FacesUtilBean.msgAlert("O(a) beneficiado(a) NÃO possui cadastro no sistema! Pode prosseguir com o cadastro");
		}
	}
	
	public void pesquisar(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			
			switch (this.parametroCampo) {
			case "nome":
				listaBeneficiado = new ArrayList<>();
				listaBeneficiado = dao.buscarPorNome(this.valorCampo);
				break;
				
			case "bairro":
				listaBeneficiado = new ArrayList<>();
				listaBeneficiado = dao.buscarPorBairro(this.valorCampo);
				break;

			}
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao pesquisar com o parâmetro informado! ERRO: [ "+e.getMessage()+" ]");
		}
	}
	
	public void adicionar(Beneficiado beneficiado){
		this.beneficiado = beneficiado;
	}
	

}
