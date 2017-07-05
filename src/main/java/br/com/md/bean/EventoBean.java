package br.com.md.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.EventoDAO;
import br.com.md.entities.Evento;
import br.com.md.util.FacesUtilBean;


@ManagedBean
@ViewScoped
public class EventoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Evento evento;
	private List<Evento> eventos;
	
	
	public Evento getEvento() {
		if(this.evento ==null)
			this.evento = new Evento();
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	public void listarEventos(){
		EventoDAO dao = new EventoDAO();
		try {
			this.eventos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar listar os Eventos cadastrados! ERRO: [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		EventoDAO dao = new EventoDAO();
		try {
			dao.salvar(evento);
			FacesUtilBean.msgInfo("Evento cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar cadastrar o evento! ERRO: [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	public void atualizar(){
		EventoDAO dao = new EventoDAO();
		try {
			dao.editar(evento);
			FacesUtilBean.msgInfo("Dados do evento atualizados com sucesso!");
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao tentar atualizar os dados do evento! ERRO: [ "+e.getMessage()+" ]");
			e.printStackTrace();
		}
	}
	
	public void adicionar(Evento evento){
		this.evento = evento;
	}
}
