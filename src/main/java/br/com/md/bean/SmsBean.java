package br.com.md.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.cfg.SetSimpleValueTypeSecondPass;

import br.com.facilitamovel.bean.Retorno;
import br.com.facilitamovel.bean.SmsMultiplo;
import br.com.facilitamovel.service.CheckCredit;
import br.com.facilitamovel.service.SendMessage;
import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.util.FacesUtilBean;

@ManagedBean
@ViewScoped
public class SmsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String user = "andrewalsh";
	private static final String password = "Theo2014";
	private String bairro;
	private List<String> bairros;
	private List<Beneficiado> beneficiados1;
	private List<Beneficiado> beneficiados2;
	private int totalTelefones;
	private String mensagem;
	private int smsDisponivel;
	
	
	public int getSmsDisponivel() {
		return smsDisponivel;
	}
	public void setSmsDisponivel(int smsDisponivel) {
		this.smsDisponivel = smsDisponivel;
	}
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getTotalTelefones() {
		return totalTelefones;
	}

	public List<Beneficiado> getBeneficiados1() {
		return beneficiados1;
	}

	public void setBeneficiados1(List<Beneficiado> beneficiados1) {
		this.beneficiados1 = beneficiados1;
	}

	public List<Beneficiado> getBeneficiados2() {
		return beneficiados2;
	}

	public void setBeneficiados2(List<Beneficiado> beneficiados2) {
		this.beneficiados2 = beneficiados2;
	}

	public List<String> getBairros() {
		return bairros;
	}

	public void setBairros(List<String> bairros) {
		this.bairros = bairros;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void listarBairros() throws Exception {
		BeneficiadoDAO dao = new BeneficiadoDAO();
		try {
			bairros = dao.listarBairrosCadastrados();
			verificarCreditos();
		} catch (RuntimeException e) {
			// TODO: handle exception
		}
	}

	public void pesquisar() {
		if (this.bairro == null) {
			this.bairro = new String();
		}
		try {
			BeneficiadoDAO dao1 = new BeneficiadoDAO();

			beneficiados1 = dao1.listarTelefonesCelular1(this.bairro);
			beneficiados2 = dao1.listarTelefonesCelular2(this.bairro);
			this.totalTelefones = beneficiados1.size() + beneficiados2.size();
		} catch (RuntimeException e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao gerar a listagem de celulares! ERRO: [ " + e.getMessage() + " ]");
		}
	}

	public void criarLista() {
		List<String> telefones1 = new ArrayList<>();
		List<String> telefones2 = new ArrayList<>();

		for (int i = 0; i < beneficiados1.size(); i++) {
			if (beneficiados1.get(i).getTelefoneCelular1().length() > 5)
				telefones1.add("21" + beneficiados1.get(i).getTelefoneCelular1().replace("-", ""));

		}
		
		formatarLista(telefones1);
		
		for (int j = 0; j < beneficiados2.size(); j++) {
			if (beneficiados2.get(j).getTelefoneCelular2().length() > 5)
				telefones2.add("21" + beneficiados2.get(j).getTelefoneCelular2().replace("-", ""));
		}
		//formatarLista(telefones2);
	}

	private void formatarLista(List<String> telefones) {
		final int tamanhoMaximoDaLista = 500;
		int lista = telefones.size();
		int repeticao = 0;
		int primeiraPosicao = 0;
		int ultimaPosicao = 0;

		if (lista % tamanhoMaximoDaLista == 0) {
			repeticao = (lista / tamanhoMaximoDaLista);
			for (int i = 0; i < repeticao; i++) {

				List<String> sublista = new ArrayList<>();

				if (primeiraPosicao == 0 && (tamanhoMaximoDaLista > lista)) {
					ultimaPosicao = lista;
					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(telefones.get(j));
					}
				} else {
					primeiraPosicao = ultimaPosicao;
					ultimaPosicao = ultimaPosicao + tamanhoMaximoDaLista;

					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(telefones.get(j));
					}
				}

				System.out.println(sublista.toString());
				enviarSMS(sublista);
			}

		} else {
			repeticao = (lista / tamanhoMaximoDaLista) + 1;

			for (int i = 0; i < repeticao; i++) {

				List<String> sublista = new ArrayList<>();

				if (primeiraPosicao == 0 && (tamanhoMaximoDaLista > lista)) {
					ultimaPosicao = lista;
					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(telefones.get(j));
					}
				} else {
					primeiraPosicao = ultimaPosicao;
					ultimaPosicao = ultimaPosicao + tamanhoMaximoDaLista;

					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(telefones.get(j));
					}
				}

				System.out.println(sublista.toString());
				enviarSMS(sublista);
			}
		}
	}

	private void enviarSMS(List<String> sublista) {
		SmsMultiplo sms = new SmsMultiplo();
		Retorno retorno = null;
		
		try {
			sms.setUser(user);
			sms.setPassword(password);
			sms.setMessage(this.mensagem);
			sms.setDestinatarios(sublista);
			
			retorno = SendMessage.multipleSend(sms);
			System.out.println("Codigo:" + retorno.getCodigo());
			System.out.println("Descricao:" + retorno.getMensagem());
			
			FacesUtilBean.msgInfo("SMSs enviados com sucesso!");
			verificarCreditos();
		} catch (Exception e) {
			FacesUtilBean.msgErro("Ocorreu um erro ao enviar os SMSs. ERRO: [ " + e.getMessage() + " "+retorno.getCodigo()+"]");
			e.printStackTrace();
		}
	}
	
	private void verificarCreditos() throws Exception {
		try {
			
			smsDisponivel = CheckCredit.checkRealCredit(user, password);
		} catch (Exception e) {
			//Possivelmente LOGIN INVALIDO
			e.printStackTrace();
		}
	}

}
