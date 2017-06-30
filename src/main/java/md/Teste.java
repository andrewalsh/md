package md;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.md.dao.BeneficiadoDAO;
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
				String auxtelefone = row[7];
				String telefone = "";
				
				telefone = auxtelefone.replace(" ", "").replaceAll("[-]", "");
				
				if(telefone.startsWith("9") && telefone.length() == 19) {
					for (int i = 0; i < telefone.length(); i++) {
						b.setTelefoneCelular1(telefone.substring(0, 9));
						b.setTelefoneCelular2(telefone.substring(10, 19));
					}
				}else if(telefone.startsWith("9") && telefone.length() == 18){
					for (int i = 0; i < telefone.length(); i++) {
						b.setTelefoneCelular1(telefone.substring(0, 9));
						b.setTelefoneResidencia(telefone.substring(10, 18));
					}
				}else if(!telefone.startsWith("9") && telefone.length() == 18){
					for (int i = 0; i < telefone.length(); i++) {
						b.setTelefoneResidencia(telefone.substring(0, 8));
						b.setTelefoneCelular1(telefone.substring(9, 18));
					}
				}else if(!telefone.startsWith("9") && !telefone.startsWith("0") && telefone.length() == 8){
					b.setTelefoneResidencia(telefone);
				}else if(telefone.startsWith("9") && telefone.length() == 9){
					b.setTelefoneCelular1(telefone);
				}else{
					b.setTelefoneCelular1(null);
					b.setTelefoneCelular2(null);
					b.setTelefoneResidencia(null);
				}
				
				if(row[9].equals("0")){
					b.setNascimento(null);
				}else{
					b.setNascimento(ManipulateDate.getDate(Integer.parseInt(row[11]), Integer.parseInt(row[10]),Integer.parseInt(row[9])));
				}
				
				if(!row[12].equals("M") || !row[12].equals("F")){
					b.setSexo(null);
				}else{
					 b.setSexo(row[12]);
				}
				
				if(row[1].startsWith("x") || row[1].length() > 11){
					b.setCpf(null);
				}else{
					b.setCpf(row[1].replaceAll("[-]", ""));
				}
				

				b.setId(new BeneficiadoID(row[0], new Logradouro(row[3], row[4], row[5], null, row[8], row[6])));
				b.setCpf(row[1].replaceAll("[-]", ""));
				
				lista.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean terminou = false;
		int inicio = 0;
		int fim = 500;
		while(terminou == false){
			if(lista.size() > 500){
				int listaTamanho = lista.size();
				
				List<Beneficiado> sublista = new ArrayList<>();
				sublista = lista.subList(inicio, fim);
				for (Beneficiado beneficiado : sublista) {
					System.out.println(beneficiado.toString());
				}
				System.out.println(sublista.size());
				terminou = true;
			}
		}
		
		
		
		

		/*for (Beneficiado beneficiado : lista) {
			BeneficiadoDAO dao = new BeneficiadoDAO();
			//Beneficiado aux = new Beneficiado();
			//System.out.println(beneficiado.toString());
			try {
				//aux = dao.buscarPorID(beneficiado.getId().getNome(), beneficiado.getId().getLogradouro().getEndereco());
				//if(aux.equals(null)){
					dao.salvar(beneficiado);
				//}
			} catch (RuntimeException e) {
				e.printStackTrace();
				
			}
		}*/
		//System.out.println("terminou");
		
	}

}
