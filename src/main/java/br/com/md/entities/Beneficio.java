package br.com.md.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class Beneficio implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;
	private long idBeneficio;
	private String nomeBeneficio;
	private String responsável;
	private String evento;
	private Date dataBeneficio;
	private Beneficiado beneficiado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getIdBeneficio() {
		return idBeneficio;
	}
	public void setIdBeneficio(long idBeneficio) {
		this.idBeneficio = idBeneficio;
	}
	
	public String getNomeBeneficio() {
		return nomeBeneficio;
	}
	public void setNomeBeneficio(String nomeBeneficio) {
		this.nomeBeneficio = nomeBeneficio;
	}
	
	public String getResponsável() {
		return responsável;
	}
	public void setResponsável(String responsável) {
		this.responsável = responsável;
	}
	
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataBeneficio() {
		return dataBeneficio;
	}
	public void setDataBeneficio(Date dataBeneficio) {
		this.dataBeneficio = dataBeneficio;
	}
	
	@ManyToOne
	@JoinColumn(name="idBeneficiado",referencedColumnName="idBeneficiado",nullable=false)
	public Beneficiado getBeneficiado() {
		return beneficiado;
	}
	public void setBeneficiado(Beneficiado beneficiado) {
		this.beneficiado = beneficiado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBeneficio ^ (idBeneficio >>> 32));
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
		Beneficio other = (Beneficio) obj;
		if (idBeneficio != other.idBeneficio)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Beneficio [idBeneficio=" + idBeneficio + ", nomeBeneficio=" + nomeBeneficio + ", responsável="
				+ responsável + ", evento=" + evento + ", beneficiado=" + beneficiado + "]";
	}
}
