package br.com.md.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.util.FacesUtilBean;

@ManagedBean
@ViewScoped
public class ConsultaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> bairros;
	private List<String> nomes;
	private List<String> primeiroNome;
	private List<Beneficiado> beneficiados;
	private String parametro;

	public List<String> getBairros() {
		return bairros;
	}

	public void setBairros(List<String> bairros) {
		this.bairros = bairros;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public List<Beneficiado> getBeneficiados() {
		return beneficiados;
	}

	public void setBeneficiados(List<Beneficiado> beneficiados) {
		this.beneficiados = beneficiados;
	}

	public List<String> getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(List<String> primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void listarBairros() {
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			bairros = dao.listarBairrosCadastrados();

		} catch (RuntimeException e) {
			e.printStackTrace();
			FacesUtilBean.msgErro("Ocorreu um erro ao listar os bairros cadastrados! ERRO: [ " + e.getMessage() + " ]");
		}
	}

	public void listar() {
		try {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			primeiroNome = new ArrayList<>();
			if (this.parametro.equals("selecione")) {
				FacesUtilBean.msgAlert("Selecione um bairro para listar os nomes!");
			} else {
				nomes = dao.listarPrimeiroNomePorBairro(parametro);
				for (int i = 0; i < nomes.size(); i++) {
					String n = imprimirPrimeiroNome(nomes.get(i));
					primeiroNome.add(n);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		for (String string : primeiroNome) {
			System.out.println(string);
		}
	}

	private String imprimirPrimeiroNome(String n) {
		String nome = n;
		String primeiroNome = "";
		
		for (int j=0;j<nome.length();j++){
			if ((j==0) && (nome.substring(j, j+1).equalsIgnoreCase(" "))){
				System.out.println("Erro: Nome digitado iniciado com tecla ESPAÇO.");
				break;
			}
			else if (!nome.substring(j, j+1).equalsIgnoreCase(" ")){
				primeiroNome += nome.substring(j, j+1);
			}
			else
				break;
		}
		return primeiroNome;
	}

}
