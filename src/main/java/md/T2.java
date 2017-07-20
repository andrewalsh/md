package md;

import java.util.ArrayList;
import java.util.List;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.dao.EventoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.Evento;

public class T2 {
	
	
	public static void main(String[] args) throws Exception {
		
		List<String> nomes = new ArrayList<>();
		BeneficiadoDAO dao = new BeneficiadoDAO();
		
		try {
			nomes = dao.listarPrimeiroNomePorBairro("iraja");
			for (String string : nomes) {
				System.out.println(string);
			}
			System.out.println(nomes.size());
			imprimirPrimeiroNome(nomes);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	
	static void imprimirPrimeiroNome(List<String> nomes){
		for (int i = 0; i < nomes.size(); i++) {
			String nome = nomes.get(i);
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
			System.out.println(primeiroNome);
			System.out.println(i);
		}
	}
	
	
}
