package br.com.md.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Evento implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	private long idEvento;
	private String nomeEvento;
	private Date dataEvento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}
	
	@Column(length=100)
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEvento ^ (idEvento >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (idEvento != other.idEvento)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento + "]";
	}
}
