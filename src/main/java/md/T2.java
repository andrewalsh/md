package md;

import java.util.ArrayList;
import java.util.List;

import br.com.facilitamovel.bean.Retorno;
import br.com.facilitamovel.bean.SmsMultiplo;
import br.com.facilitamovel.service.CheckCredit;
import br.com.facilitamovel.service.SendMessage;


public class T2 {
	
	
	public static void main(String[] args) throws Exception {
		String usuario = "andrewalsh";
		String senha = "Theo2014";
		
		//T2.simples(usuario, senha);
		//T2.checkCredits(usuario, senha);
	}
	
	public static String multiplos(String u, String s, List<String> lista1, List<String> lista2, String msg){
		SmsMultiplo sms = new SmsMultiplo();
		List<String> numeros1 = new ArrayList<String>();
		List<String> numeros2 = new ArrayList<String>();
		
		sms.setUser(u);
		sms.setPassword(s);
		
		numeros1 = lista1;
		numeros2 = lista2;
		
		sms.setMessage(msg);
		sms.setDestinatarios(numeros1);
		sms.setDestinatarios(numeros2);
		try {
			Retorno retorno = SendMessage.multipleSend(sms);
			
			System.out.println("Codigo:" + retorno.getCodigo());
			System.out.println("Descricao:" + retorno.getMensagem());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sucesso";
	}
	
	
	public static void checkCredits(String usuario, String senha) throws Exception {
		try {
			System.out.println(CheckCredit.checkRealCredit(usuario, senha));
			
		} catch (Exception e) {
			//Possivelmente LOGIN INVALIDO
			e.printStackTrace();
		}
	}
}
