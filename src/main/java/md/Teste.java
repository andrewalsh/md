package md;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.md.entities.Beneficiado;
import br.com.md.entities.BeneficiadoID;
import br.com.md.entities.Logradouro;
import br.com.md.util.ManipulateDate;

public class Teste {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:/doc.csv"));
		String linha = "";
		List<Beneficiado> lista = new ArrayList<>();
		try {
			while ((linha = br.readLine()) != null) {
				Beneficiado b = new Beneficiado();
				String[] row = linha.split(";");
				// String telefone = row[7];

				// telefone.replaceAll("[/-]", "");

				// if(telefone.substring(0, 0).equals("9")){
				// b.setTelefoneCelular(telefone.substring(0, 10));
				// }

				b.setId(new BeneficiadoID(row[0], new Logradouro(row[3], row[4], row[5], null, row[8], row[6])));
				b.setCpf(row[1]);
				b.setTelefoneCelular(row[7]);
				if(row[9].equals("0")){
					b.setNascimento(null);
				}else{
					b.setNascimento(ManipulateDate.getDate(Integer.parseInt(row[9]), Integer.parseInt(row[10]),Integer.parseInt(row[11])));
				}
			    b.setSexo(row[12]);
				lista.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Beneficiado beneficiado : lista) {
			System.out.println(beneficiado.toString());
		}
		System.out.println("lista contem :"+lista.size());

		/*
		 * for (int i = 0; i <= lista.size(); i++) { for (int j = 0; j <
		 * lista.get(i).getTelefoneCelular().length(); j++) {
		 * if(lista.get(j).getTelefoneCelular().startsWith("9") &&
		 * lista.get(j).getTelefoneCelular().length() > 2){ System.out.println(
		 * "É celular "+lista.get(j).getTelefoneCelular().substring(0, 10));
		 * }else{ System.out.println("não é"); } } }
		 */
	}

}
