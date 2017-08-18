package br.com.md.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="Beneficiado.listar",query="from Beneficiado b"),
	@NamedQuery(name="Beneficiado.listarPrimeiroNome",query="select b.id.nome from Beneficiado b"),
	@NamedQuery(name="Beneficiado.listarPrimeiroNomePorBairro",query="select b.id.nome from Beneficiado b where b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.buascarPorID",query="from Beneficiado b where b.idBeneficiado=:idBeneficiado"),
	@NamedQuery(name="Beneficiado.buscarPorNome",query="from Beneficiado b where b.id.nome like:nome"),
	@NamedQuery(name="Beneficiado.buscarPorBairro",query="from Beneficiado b where b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarBairros",query="SELECT DISTINCT b.id.logradouro.bairro from Beneficiado b"),
	@NamedQuery(name="Beneficiado.listarRuas",query="SELECT DISTINCT b.id.logradouro.endereco from Beneficiado b"),
	@NamedQuery(name="Beneficiado.listarTelefonesCelularesPorBairro",query="from Beneficiado b where b.telefoneCelular1 is not null "
			+ "or b.telefoneCelular2 is not null and b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarTelefone1",query="from Beneficiado b where b.telefoneCelular1 is not null "
			+ "and b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.listarTelefone2",query="from Beneficiado b where b.telefoneCelular2 is not null "
			+ "and b.id.logradouro.bairro=:bairro"),
	@NamedQuery(name="Beneficiado.verificaSeExiste",query="from Beneficiado b where b.id.nome=:nome"),
	@NamedQuery(name="Beneficiado.verificarChave",query="from Beneficiado b where b.id.nome=:nome and b.id.logradouro.endereco=:endereco and b.id.logradouro.numero=:numero")
})
public class Beneficiado implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	private long idBeneficiado;
	private BeneficiadoID id;
	private String cpf;
	private String telefoneResidencia;
	private String telefoneCelular1;
	private String telefoneCelular2;
	private String sexo;
	private Date nascimento;
	private String telefone;
	private Usuario cadastradoPor;
	private List<Beneficio> beneficios;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getIdBeneficiado() {
		return idBeneficiado;
	}
	public void setIdBeneficiado(long idBeneficiado) {
		this.idBeneficiado = idBeneficiado;
	}
	
	@Transient
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	@Embedded
	@Column(unique=true)
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
	
	@ManyToOne
	@JoinColumn(name="cadstradoPor",referencedColumnName="idUsuario",nullable=false)
	public Usuario getCadastradoPor() {
		return cadastradoPor;
	}
	public void setCadastradoPor(Usuario cadastradoPor) {
		this.cadastradoPor = cadastradoPor;
	}
	
	@ManyToMany
	public List<Beneficio> getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
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
