package br.com.md.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Logradouro {

	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	
	
	public Logradouro() {
		
	}


	public Logradouro(String endereco, String numero, String bairro, String cidade, String uf, String cep) {
		super();
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
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

	@Column(length=2)
	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(length=8)
	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	@Override
	public String toString() {
		return "Endereço: "+endereco+" ,número: "+numero+" ,Bairro: "+bairro+" ,Cidade: "+cidade+" ,CEP: "+cep+" UF: "+uf;
	}
	
	
}
