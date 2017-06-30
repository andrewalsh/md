package md;
import java.util.ArrayList;
import java.util.List;

public class T4 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int tamanhoMaximoDaLista = 2;
		int lista = 11;
		int repeticao = 0;
		int primeiraPosicao = 0;
		int ultimaPosicao = 0;
		List<Integer> telefones = new ArrayList<>();

		for (int i = 0; i < lista; i++) {
			Integer telefone = new Integer(i);
			telefones.add(telefone);

		}

		for (Integer integer : telefones) {
			System.out.println(integer.toString());
		}

		if (lista % tamanhoMaximoDaLista == 0) {

			repeticao = (lista / tamanhoMaximoDaLista);
			for (int i = 0; i < repeticao; i++) {

				List<Integer> sublista = new ArrayList<>();

				if (primeiraPosicao == 0 && ultimaPosicao == 0) {
					ultimaPosicao = tamanhoMaximoDaLista;
					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(j);
					}
				} else {
					primeiraPosicao = ultimaPosicao;
					ultimaPosicao = ultimaPosicao + tamanhoMaximoDaLista;

					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(j);
					}
				}

				System.out.println(sublista.toString());

			}

		} else {
			repeticao = (lista / tamanhoMaximoDaLista) + 1;

			for (int i = 0; i < repeticao; i++) {

				List<Integer> sublista = new ArrayList<>();

				if (primeiraPosicao == 0 && ultimaPosicao == 0) {
					ultimaPosicao = tamanhoMaximoDaLista;
					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(j);
					}
				} else {
					primeiraPosicao = ultimaPosicao;
					ultimaPosicao = ultimaPosicao + tamanhoMaximoDaLista;

					for (int j = primeiraPosicao; j < ultimaPosicao; j++) {
						sublista.add(j);
					}
				}

				System.out.println(sublista.toString());

			}
		}
	}

}
