package br.com.md.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.md.dao.EventoDAO;
import br.com.md.entities.Evento;

@FacesConverter("eventoConverter")
public class EventoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		EventoDAO dao = new EventoDAO();
		try {
			Long codigo = Long.parseLong(valor);
			Evento evento = dao.buscarPorCodigo(codigo);
			return evento;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		try {
			Evento evento = (Evento)object;
			Long codigo = evento.getIdEvento();
			return codigo.toString();
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}	
	}

}
