package controller;

public class RedesController {
	
	private ProcessosController processo = new ProcessosController();
	private String[] texto = null;
	
	public void ip(String osName){
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
		}else{
			if(osName.contains("Linux")){
				String frase = "";
				String ifconfig = processo.lerProcesso("ifconfig");
				texto = ifconfig.split("\n");
				
				for(String sentenca : texto)
					if(sentenca.contains("Ethernet") || sentenca.contains("inet"))
						frase = frase.concat(sentenca + ";").trim();
						
				texto = frase.split(";");
				frase = "";
				
				for(int i = 0; i < (texto.length - 1); i++)
					if(texto[i].contains("Ethernet") && texto[i + 1].contains("inet"))
						frase = frase.concat(texto[i] + ";" + texto[i + 1] + ";");
				
				texto = frase.split(";");	
			}
		}
		
		
	}
	
	public String mostra(){
		String r = "";
		for(int i = 0; i < texto.length; i++)
			r = r + texto[i] + "\n";
		return r;
	}
	
	
	
}
