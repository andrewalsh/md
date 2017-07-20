package br.com.md.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.md.entities.Usuario;


public class FacesUtilBean {
	public static void msgInfo(String msg){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
    public static void msgErro(String msg){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
    public static void msgAlert(String msg){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, null, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
    public static String getParams(String nome){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String,String> parametro = externalContext.getRequestParameterMap();
        String valor = parametro.get(nome);
        return valor;
    }
    
    public Usuario usuarioLogado(){
    	Usuario usuario = null;
    	FacesContext facesContext =  FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		usuario = (Usuario) session.getAttribute("autenticacaoBean");
		return usuario;
    }
}

