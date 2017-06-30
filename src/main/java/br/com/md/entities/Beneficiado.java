package br.com.md.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="Beneficiado.listar",query="from Beneficiado b"),
	@NamedQuery(name="Beneficiado.buascarPorID",query="from Beneficiado b where b.id.nome=:nome and b.id.logradouro.endereco=:endereco"),
	@NamedQuery(name="Beneficiado.buscarPorNome",query="from Beneficiado b where b.id.nome like:nome"),
	@NamedQuery(name="Beneficiado.buscarPorBairro",query="from Beneficiado b where b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarBairros",query="SELECT DISTINCT b.id.logradouro.bairro from Beneficiado b"),
	@NamedQuery(name="Beneficiado.listarTelefonesCelularesPorBairro",query="from Beneficiado b where b.telefoneCelular1 is not null "
			+ "or b.telefoneCelular2 is not null and b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarTelefone1",query="from Beneficiado b where b.telefoneCelular1 is not null "
			+ "and b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarTelefone2",query="from Beneficiado b where b.telefoneCelular2 is not null "
			+ "and b.id.logradouro.bairro=:bairro")
})
public class Beneficiado implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	private BeneficiadoID id;
	private String cpf;
	private String telefoneResidencia;
	private String telefoneCelular1;
	private String telefoneCelular2;
	private String sexo;
	private Date nascimento;
	private String telefone;
	
	@Transient
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	@EmbeddedId
	public BeneficiadoID getId() {
		return id;
	}
	public void setId(BeneficiadoID id) {
		this.id = id;
	}
	
	
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
	
	public String getTelefoneCelular1() {
		return telefoneCelular1;
	}
	public void setTelefoneCelular1(String telefoneCelular1) {
		this.telefoneCelular1 = telefoneCelular1;
	}
	public String getTelefoneCelular2() {
		return telefoneCelular2;
	}
	public void setTelefoneCelular2(String telefoneCelular2) {
		this.telefoneCelular2 = telefoneCelular2;
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
				+ ", telefoneCelular1=" + telefoneCelular1 + ", telefoneCelular2=" + telefoneCelular2 + ", sexo=" + sexo
				+ ", nascimento=" + nascimento + ", telefone=" + telefone + "]";
	}
}
