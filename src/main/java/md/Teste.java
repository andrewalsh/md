package md;

import java.util.ArrayList;
import java.util.List;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.BeneficiadoID;
import br.com.md.entities.Logradouro;

public class Teste {

	public static void main(String[] args){
		//Beneficiado b = new Beneficiado();
		List<Beneficiado> lista = new ArrayList<>();
		BeneficiadoDAO dao = new BeneficiadoDAO();
		
		//b.setId(new BeneficiadoID("Theo", new Logradouro("Rua Galeno", "95", "Irajá", "Rio de Janeiro", "RJ", "21230020")));
		
		try {
			lista = dao.buscarPorBairro("IRAJA");
			for (Beneficiado beneficiado : lista) {
				System.out.println(beneficiado.toString());
			}
		} catch (RuntimeException e) {
			System.out.println("Erro "+e.getMessage());
		}
	}
	
	
}
