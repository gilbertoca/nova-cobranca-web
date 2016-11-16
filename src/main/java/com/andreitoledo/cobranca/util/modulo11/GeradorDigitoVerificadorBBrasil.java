package com.andreitoledo.cobranca.util.modulo11;

import java.io.Serializable;

public class GeradorDigitoVerificadorBBrasil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String gerarDigito(Integer carteira, String nossoNumero){ 
		// cria a string do digito para calculo da carteira + nosoNumero
		String digitosParaCalculo = String.valueOf(carteira);
		// concatena os digitos da carteira + nosso numero
		digitosParaCalculo += nossoNumero; 
		
		// efetua a soma para obter o numero que vai dividir por 11
		int soma = obterSomaParaModulo11(digitosParaCalculo);
		
		return obterDigitoVerificador(soma); // metodo para obter o dv dessa soma
		
	}

	private String obterDigitoVerificador(int soma) {
		int modulo = 11;
		// efetua a divisão da soma % modulo
		int restoDivisao = soma % modulo; 
		
		// obtem o digito subtraindo o modulo pelo resto da divisão
		int preDigito = modulo - restoDivisao;
		
		String digitoVerificador;
		switch (preDigito) {
		case 10:
			digitoVerificador = "P";			
			break;
		case 11:
			digitoVerificador = "0";			
			break;
		default:
			digitoVerificador = String.valueOf(preDigito);
			break;
		}

		return digitoVerificador;
	}

	private int obterSomaParaModulo11(String digitosParaCalculo) {
		int fator = 2; // inicia no fator 2 e segue... 2,3,4,5,6,7
		int somatorio = 0; // resultado da soma
		
		// (-1) navega na string(linha) de traz para frente
		for(int i = digitosParaCalculo.length() - 1; i >= 0; i--){
			
			// pega os digitos de traz para frente na string(linha)
			Integer digito = Integer.parseInt(String.valueOf(digitosParaCalculo.charAt(i)));
			
			somatorio += fator * digito; // multiplica o valor pelo fator 2...
			fator++; // multiplica os outros valores pelo fator respectivo seguindo a linha... 3, 4, 5, 6, 7
			
			// quando chegar no fator 8, volta a preenher a sequencia a partir do fator 2,3,4,5,6,7 novamente até o final da linha
			if(fator == 8){
				fator = 2;
				
			}
			
		}

		return somatorio;
	}

	public String completarComZeros(String numero) {
		String novoNumero = "";
		for(int i = 0; i < (11 - numero.length()); i++){
			novoNumero += "0";
		}

		return novoNumero + numero;
	}

}
