package controller;

public class RedesController {
	
	private ProcessosController processo = new ProcessosController();
	
	public String[] ip(String osName){
		String[] texto = null;
		if(osName.contains("Windows")){
			String frase = "";
			String ipconfig = processo.lerProcesso("ipconfig");
			ipconfig = ipconfig.trim();
			texto = ipconfig.split("\n");
			
			for(String sentenca : texto)
				if(sentenca.contains("adapter") || sentenca.contains("IPv4"))
					frase = frase.concat(sentenca + ";");
			
			texto = frase.split(";");
			frase = "";
			
			for(int i = 0; i < (texto.length - 1); i++)
				if(texto[i].contains("adapter") && texto[i + 1].contains("IPv4"))
					frase = frase.concat(texto[i] + ";" + texto[i + 1] + ";");
			
			texto = frase.split(";");
		}
		
		return texto;
	}
}
