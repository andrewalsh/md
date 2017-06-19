package br.com.md.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="beneficiado")
@NamedQueries({
	@NamedQuery(name="Beneficiado.listar",query="from Beneficiado b"),
	@NamedQuery(name="Beneficiado.buascarPorID",query="from Beneficiado b where b.id.nome=:nome and b.id.logradouro.endereco=:endereco"),
	@NamedQuery(name="Beneficiado.buscarPorNome",query="from Beneficiado b where b.id.nome like:nome"),
	@NamedQuery(name="Beneficiado.buscarPorBairro",query="from Beneficiado b where b.id.logradouro.bairro=:bairro")
})
public class Beneficiado implements Serializable{

	private static final long serialVersionUID = 1L;
	private BeneficiadoID id;
	private String cpf;
	private String telefoneResidencia;
	private String telefoneCelular;
	private String sexo;
	private Date nascimento;
	
	
	@EmbeddedId
	public BeneficiadoID getId() {
		return id;
	}
	public void setId(BeneficiadoID id) {
		this.id = id;
	}
	
	@Column(length=11)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefoneResidencia() {
		return telefoneResidencia;
	}
	public void setTelefoneResidencia(String telefoneResidencia) {
		this.telefoneResidencia = telefoneResidencia;
	}
	
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
	@Column(length=1)
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Beneficiado other = (Beneficiado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Beneficiado [id=" + id + ", cpf=" + cpf + ", telefoneResidencia=" + telefoneResidencia
				+ ", telefoneCelular=" + telefoneCelular + ", sexo=" + sexo + ", nascimento=" + nascimento + "]";
	}

}
