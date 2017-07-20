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
			this.beneficiado.setId(new BeneficiadoID());
			this.beneficiado.getId().setLogradouro(new Logradouro());
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
	
	
	public void listar(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			listaBeneficiado = dao.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			FacesUtilBean.msgErro("Ocorreu um erro ao gerar a lista dos Beneficiados cadastrados! ERRO: [ "+e.getMessage()+" ]");
		}
	}
	
	public void salvar(){
		try {
			FacesUtilBean bean = new FacesUtilBean();
			this.beneficiado.setCadastradoPor(bean.usuarioLogado());
			Beneficiado aux = verificarChave();
			BeneficiadoDAO dao = new BeneficiadoDAO();
			populate();
			beneficiado.setId(new BeneficiadoID(nome, new Logradouro(endereco, numero, bairro, cidade, uf, cep)));
			if(aux == null){
				dao.salvar(beneficiado);
				FacesUtilBean.msgInfo("Beneficiado(a) cadastrado(a) com sucesso!");
			}else{
				FacesUtilBean.msgAlert("O beneficiado já possui cadastro no sistema! Se os dados não estiverem atualizados, atualize-os e clique no botão atualizar.");
				this.beneficiado = aux;
			}
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar cadastrar o(a) beneficiado(a): [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	public void atualizar(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			populate();
			dao.editar(beneficiado);
			FacesUtilBean.msgInfo("Dados cadastrais do(a) beneficiado(a) atualizados com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar atualizar o cadastro do(a) beneficiado(a): [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	
	public void verificarID(){
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			this.beneficiado = null;
			//this.beneficiado = dao.buscarPorID(nome, endereco);
			if(!this.beneficiado.equals(null)){
				FacesUtilBean.msgAlert("O(a) beneficiado(a) já possui cadastro no sistema! Verifique se os dados estão atualizados.");
			}
		} catch (RuntimeException e) {
			FacesUtilBean.msgAlert("O(a) beneficiado(a) NÃO possui cadastro no sistema! Pode prosseguir com o cadastro");
		}
	}
	
	public void verificaSeExiste(){
		BeneficiadoDAO dao = new BeneficiadoDAO();
		//List<Beneficiado> beneficiados = new ArrayList<>();
		
		try {
			listaBeneficiado = dao.verificaSeExiste(beneficiado.getId().getNome());
			
		} catch (RuntimeException e) {
			// TODO: handle exception
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
		System.out.println(this.beneficiado.toString());
	}
	
	private void populate(){
		this.nome = this.beneficiado.getId().getNome();
		this.endereco = this.beneficiado.getId().getLogradouro().getEndereco();
		this.numero = this.beneficiado.getId().getLogradouro().getNumero();
		this.bairro = this.beneficiado.getId().getLogradouro().getBairro();
		this.cidade = this.beneficiado.getId().getLogradouro().getCidade();
		this.uf = this.beneficiado.getId().getLogradouro().getUf();
		this.cep = this.beneficiado.getId().getLogradouro().getCep();
		this.beneficiado.getCpf().replaceAll("[.-]", "");
		
		if(this.beneficiado.getTelefoneResidencia().length() < 9){
			this.beneficiado.setTelefoneResidencia(null);
		}else{
			this.beneficiado.getTelefoneResidencia().replaceAll("[-]", "");
		}
		
		if(this.beneficiado.getTelefoneCelular1().length() < 10){
			this.beneficiado.setTelefoneCelular1(null);
		}else{
			this.beneficiado.getTelefoneCelular1().replaceAll("[-]", "");
		}
		
		if(this.beneficiado.getTelefoneCelular2().length() < 10){
			this.beneficiado.setTelefoneCelular2(null);
		}else{
			this.beneficiado.getTelefoneCelular2().replaceAll("[-]", "");
		}
	}
	
	private Beneficiado verificarChave(){
		BeneficiadoDAO dao = new BeneficiadoDAO();
		Beneficiado aux = new Beneficiado();
		try {
			aux = dao.verificarChave(this.beneficiado.getId().getNome(), this.beneficiado.getId().getLogradouro().getEndereco(), this.beneficiado.getId().getLogradouro().getNumero());
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar evitar a duplicidade de cadastro! ERRO: [ "+e.getMessage()+" ]");
		}return aux;
	}
}
