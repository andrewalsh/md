package md;

import java.util.ArrayList;
import java.util.List;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.dao.EventoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.Evento;

public class T2 {
	
	
	public static void main(String[] args) throws Exception {
		String nome = "David Kennedy Souza Araujo";
		String primeiroNome = "";
		for (int i=0;i<nome.length();i++){
			if ((i==0) && (nome.substring(i, i+1).equalsIgnoreCase(" "))){
				System.out.println("Erro: Nome digitado iniciado com tecla ESPAÇO.");
				break;
			}
			else if (!nome.substring(i, i+1).equalsIgnoreCase(" ")){
				primeiroNome += nome.substring(i, i+1);
			}
			else
				break;
		}
		System.out.println(primeiroNome);
	}
	
	
}
