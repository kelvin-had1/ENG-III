package negocio.impl;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Date;
import dominio.EntidadeDominio;
import negocio.IStrategy;

public class GerarLog implements IStrategy {

	public String processar(EntidadeDominio entidade){
		String textoLog = "Um(a) " + entidade.getClass().getSimpleName() + " foi salvo(a) às " + new Date().toString();

		Path fileName = Path.of("/logs/log.txt");
		
		try {

			Files.writeString(fileName, textoLog);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("LOG FALHOU");
			System.out.println(textoLog);
		}
		return "";
	}

}
