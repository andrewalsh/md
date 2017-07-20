package br.com.facilitamovel.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.UsuarioDAO;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StartBanco implements Serializable {

	private String nome;
	private int number;

	public String getNome() {
		return nome+" "+number;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumber() {
		return number;
	}

	

	public void escreverNome() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			this.nome = dao.cpf();
			this.number++;
		} catch (RuntimeException e) {
			// TODO: handle exception
		}
	}
}
