package br.com.md.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


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
}

