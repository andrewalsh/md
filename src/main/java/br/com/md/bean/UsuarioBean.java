package br.com.md.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.md.dao.UsuarioDAO;
import br.com.md.entities.Usuario;
import br.com.md.util.FacesUtilBean;


@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario novoUsuario;
	private String senha1;
	private String senha2;
	private List<Usuario> usuarios;
	
	
	public Usuario getNovoUsuario() {
		if(this.novoUsuario == null){
			this.novoUsuario = new Usuario();
		}
		return novoUsuario;
	}
	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
	
	public Usuario getUsuario() {
		if(this.usuario == null){
			this.usuario = new Usuario();
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getSenha1() {
		return senha1;
	}
	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String autenticar(){
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usr = dao.autenticar(this.usuario);
			if(usr == null){
				FacesUtilBean.msgErro("CPF e/ou senha inválidos");
				return null;
			}else if(usr.isAtivo() == false){
				FacesUtilBean.msgAlert("Usuário não está autorizado!");
				return null;
			}else{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				session.setAttribute("autenticacaoBean", usr);
				return "/public/index.xhtml=?faces-redirect=true";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			FacesUtilBean.msgErro("Erro ao autenticar Usuário! ERRO: [ " + e.getMessage() + " ]");
			return null;
		}
	}
	
	public void encerraSessao() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("autenticacaoBean", null);
			externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.xhtml");
			session.invalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listar(){
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.listar();
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao carregar a lista de Usuários cadastrados! ERRO [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	public void exibirUsuarioLogado(){
		FacesUtilBean bean = new FacesUtilBean();
		this.usuario = bean.usuarioLogado();
	}
	
	public void carregarCadastro(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("autenticacaoBean");
		this.usuario = usuario;
		
		try {
			if (this.usuario.getIdUsuario() != 0) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuario = new Usuario();
				usuario = usuarioDAO.buscarPorCodigo(this.usuario.getIdUsuario());
			}
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Erro ao tentar carregar os dados do Usuário! ERRO: [ " + e.getLocalizedMessage() + " ]");
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			UsuarioDAO dao = new UsuarioDAO();
			String cpf = novoUsuario.getLogin().replaceAll("[.-]", "");
			novoUsuario.setSenha(cpf);
			dao.salvar(novoUsuario);
			FacesUtilBean.msgInfo("Usuário cadastrado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar salvar o usuário! ERRO: [ "+e.getMessage()+" ]");
		}
	}
	
	public void editar(){
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.atualzar(novoUsuario);
			FacesUtilBean.msgInfo("Usuário atualizado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar atualizar o usuário! ERRO: [ "+e.getMessage()+" ]");
		}
	}
	
	public void validarSenha1Senha2(){
		if(!this.senha1.equals(senha2)){
			FacesUtilBean.msgAlert("As senhas digitadas não conferem!");
		}else{
			editar();
		}
	}
	
	public void adicionar(Usuario usuario){
		this.novoUsuario = usuario;
	}
	
	public void novo(){
		this.novoUsuario = new Usuario();
	}
}
