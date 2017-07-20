package md;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.types.Date;

import br.com.md.dao.BeneficiadoDAO;
import br.com.md.entities.Beneficiado;
import br.com.md.entities.BeneficiadoID;
import br.com.md.entities.Logradouro;
import br.com.md.entities.Usuario;
import br.com.md.util.ManipulateDate;

public class Teste2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:/doc/Cadastros.csv"));
		String linha = "";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		List<Beneficiado> lista = new ArrayList<>();
		try {
			while ((linha = br.readLine()) != null) {
				Beneficiado b = new Beneficiado();
				String[] row = linha.split(";");
				
				//b.setNascimento(formato.parse(row[0]));
				//b.setTelefoneCelular1(row[1]);
				String auxtelefone = row[6];
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
				
				b.setNascimento(formato.parse(row[7]));
				
				b.setSexo(null);
				
				b.setCpf(null);
				
				//b.setId(new BeneficiadoID(nome, new Logradouro(endereco, numero, bairro, cidade, uf, cep)));
				b.setId(new BeneficiadoID(row[0].toUpperCase(), new Logradouro(row[1].toUpperCase(), row[2].toUpperCase(), row[3].toUpperCase(), row[4], "RJ", row[5])));
				
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
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				
			}
		}
		System.out.println(lista.size());
		//System.out.println("terminou");
		
	}
}
