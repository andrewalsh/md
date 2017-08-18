package br.com.md.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


@Entity
@NamedQueries({
	@NamedQuery(name="Beneficio.listar",query="from Beneficio b"),
	@NamedQuery(name="Beneficio.buscarPorCodigo",query="from Beneficio b where b.idBeneficio=:id")
})
public class Beneficio implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;
	private long idBeneficio;
	private String nomeBeneficio;
	
	
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
		return "Beneficio [idBeneficio=" + idBeneficio + ", nomeBeneficio=" + nomeBeneficio + "]";
	}
	
}
