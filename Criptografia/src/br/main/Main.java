package br.main;

import java.security.InvalidKeyException;

import br.main.arquivos.Arquivos;
import br.main.criptografia.EncriptaDecriptaOTP;
import br.main.criptografia.EncriptaDecriptaRC4;

public class Main {
	
	private final static String pathArquivoClearText = "/mnt/backup/05312097926/pessoal/pessoal/keeper_senhas";
	private final static String pathArquivoCriptografado = "/home/emerson/Área de Trabalho/cripto.txt";
	private final static String pathArquivoClearTextAgain = "/home/emerson/Área de Trabalho/de-cripto.txt";
	
	public static void main(String[] args) throws InvalidKeyException {
		EncriptaDecriptaRC4 rc4 = new EncriptaDecriptaRC4(getKey());

		criptografar(rc4);
		//descriptografar(rc4);
	}

	private static void criptografar(EncriptaDecriptaRC4 rc4) {
		String textoCriptografado = rc4.criptografa(getClearText());
		Arquivos.saveFile(pathArquivoCriptografado, textoCriptografado);
		System.out.println("Salvo em: '" + pathArquivoCriptografado + "'");
	}
	
	private static void descriptografar(EncriptaDecriptaRC4 rc4) {
		String clearAgain = rc4.decriptografa(Arquivos.readFile(pathArquivoCriptografado));
		Arquivos.saveFile(pathArquivoClearTextAgain, clearAgain);
		System.out.println("Salvo em: '" + pathArquivoClearTextAgain + "'");
	}
	
	
	private static String getKey() {
		return "UDzXRsfvsd -- sdf";
	}
	
	private static String getClearText() {
		String texto = Arquivos.readFile(pathArquivoClearText);
		//return "hello";
		return texto;
	}
	
	private static String getCriptoText() {
		return "\fõyÔ¨N$ fó/GÌO4]J";
	}
	
	
	private static void teste1() {
		EncriptaDecriptaOTP otp = new EncriptaDecriptaOTP();
		String menssagem = "hello";
		String chave = otp.genKey(menssagem.length());
		chave = "UDzXR";
		String msgCriptografada = otp.criptografa(menssagem, chave);
		String msgDecriptografada = otp.decriptografa(msgCriptografada, chave);

		System.out.println("Menssagem: " + menssagem);
		System.out.println("Chave: " + chave);
		System.out.println("Mensagem Criptografada: " + msgCriptografada);
		System.out.println("Mensagem Decriptografada: " + msgDecriptografada);
	}
	
	private static void teste2() {
		try {
			EncriptaDecriptaRC4 rc4 = new EncriptaDecriptaRC4("testkey");
			char[] textoCriptografado = rc4.criptografa("Teste de Mensagem de Texto Puro".toCharArray());
			System.out.println("Texto Criptografado:\n" + new String(textoCriptografado));
			System.out.println("Texto Decriptografado:\n" + new String(rc4.decriptografa(textoCriptografado)));
		} catch (InvalidKeyException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}