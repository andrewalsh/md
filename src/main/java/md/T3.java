package md;

import java.util.ArrayList;
import java.util.List;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;

public class T3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BeneficiadoDAO dao = new BeneficiadoDAO();
		List<Beneficiado> lista1 = new ArrayList<>();
		List<Beneficiado> lista2 = new ArrayList<>();
		List<String> telefones1 = new ArrayList<String>();
		List<String> telefones2 = new ArrayList<String>();
		
		try {
			lista1 = dao.listarTelefonesCelular1("iraja");
			for (int i = 0; i < lista1.size(); i++) {
				telefones1.add(lista1.get(i).getTelefoneCelular1());
			}
			
			lista2 = dao.listarTelefonesCelular2("iraja");
			for (int i = 0; i < lista2.size(); i++) {
				telefones2.add(lista2.get(i).getTelefoneCelular2());
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		for (String string : telefones1) {
			System.out.println(string.toString());
		}
		
		for (String string : telefones2) {
			System.out.println(string.toString());
		}
		
		
		System.out.println(telefones1.size());
		System.out.println(telefones2.size());

		//System.out.println("Depois da comparação "+lista.size());

		/*for (int i = 0; i < lista.size(); i++) {
			String aux1 = new String();
			String aux2 = new String();
			
			aux1 = lista.get(i).getTelefoneCelular1();
			aux2 = lista.get(i).getTelefoneCelular2();
			
			if(!aux1.equals("null")){
				telefones.add("21"+aux1);
			}
			//else if(!aux2.equals(null)){
				//telefones.add("21"+aux2);
			
			//}
			*/
	}

}
