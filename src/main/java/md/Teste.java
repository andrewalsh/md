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
import br.com.md.entities.Usuario;
import br.com.md.util.ManipulateDate;

public class Teste {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:/doc/doc2.csv"));
		String linha = "";
		List<Beneficiado> lista = new ArrayList<>();
		try {
			while ((linha = br.readLine()) != null) {
				Beneficiado b = new Beneficiado();
				String[] row = linha.split(";");
				String auxtelefone = row[5];
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
					b.setNascimento(ManipulateDate.getDate(Integer.parseInt(row[9]), Integer.parseInt(row[8]),Integer.parseInt(row[7])));
				}
				
				b.setSexo(null);
				
				if(row[1].startsWith("x") || row[1].length() > 11){
					b.setCpf(null);
				}else{
					b.setCpf(row[1].replaceAll("[-]", ""));
				}
				
				//b.setId(new BeneficiadoID(nome, new Logradouro(endereco, numero, bairro, cidade, uf, cep)));
				b.setId(new BeneficiadoID(row[0], new Logradouro(row[1], row[2], row[3], null, "RJ", row[4])));
				b.setCpf(null);
				
				lista.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		for (int i = 0; i < lista.size(); i++){
			BeneficiadoDAO dao = new BeneficiadoDAO();
			Beneficiado beneficiado = new Beneficiado();
			Beneficiado aux = new Beneficiado();
			Usuario usuario = new Usuario();
			
			usuario.setIdUsuario(1);
			beneficiado = lista.get(i);
			beneficiado.setCadastradoPor(usuario);
			try {
				aux = dao.verificarChave(beneficiado.getId().getNome(), beneficiado.getId().getLogradouro().getEndereco(), beneficiado.getId().getLogradouro().getNumero());
				if(aux == null){
					dao.salvar(beneficiado);
					System.out.println(beneficiado.toString());
					System.out.println(i);
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				
			}
		}
		System.out.println("terminou");
		
	}

}
