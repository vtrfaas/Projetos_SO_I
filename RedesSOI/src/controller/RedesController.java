package controller;

public class RedesController {
	
	private ProcessosController processo = new ProcessosController();
	private String[] texto = null;
	
	public void ip(String osName){
		StringBuffer frase = new StringBuffer();
		if(osName.contains("Windows")){
			String ipconfig = processo.lerProcesso("ipconfig");
			ipconfig = ipconfig.trim();
			texto = ipconfig.split("\n");
			
			for(String sentenca : texto)
				if(sentenca.contains("adapter") || sentenca.contains("IPv4"))
					frase = frase.append(sentenca + ";");
			
			texto = frase.toString().split(";");
			frase.setLength(0);
			
			for(int i = 0; i < (texto.length - 1); i++)
				if(texto[i].contains("adapter") && texto[i + 1].contains("IPv4"))
					frase = frase.append(texto[i] + ";" + texto[i + 1] + ";");
			
			texto = frase.toString().split(";");
		}else{
			if(osName.contains("Linux")){
				String ifconfig = processo.lerProcesso("ifconfig");
				texto = ifconfig.split("\n");
				
				for(String sentenca : texto)
					if(sentenca.contains("Ethernet") || sentenca.contains("inet"))
						frase = frase.append(sentenca + ";");
						
				texto = frase.toString().split(";");
				
				for(int i = 0; i < (texto.length - 1); i++)
					if(texto[i].contains("Ethernet") && texto[i + 1].contains("inet"))
						frase = frase.append(texto[i] + ";" + texto[i + 1] + ";");
				
				texto = frase.toString().split(";");
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
