package controller;

import javax.swing.JOptionPane;

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
					frase.append(sentenca + ";");
			
			texto = frase.toString().split(";");
			frase.setLength(0);
			
			for(int i = 0; i < (texto.length - 1); i++)
				if(texto[i].contains("adapter") && texto[i + 1].contains("IPv4"))
					frase.append(texto[i] + ";" + texto[i + 1] + ";");
			
			texto = frase.toString().split(";");
		}else{
			if(osName.contains("Linux")){
				String ifconfig = processo.lerProcesso("ifconfig");
				texto = ifconfig.split("\n");
				
				for(String sentenca : texto)
					if(sentenca.contains("Ethernet") || sentenca.contains("inet"))
						frase.append(sentenca + ";");
						
				texto = frase.toString().split(";");
				frase.setLength(0);
				
				for(int i = 0; i < (texto.length - 1); i++)
					if(texto[i].contains("Ethernet") && texto[i + 1].contains("inet"))
						frase.append(texto[i] + ";" + texto[i + 1] + ";");
				
				texto = frase.toString().split(";");
			}else{
				JOptionPane.showMessageDialog(null, "Sistema Operacional não encontrado", "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public String mostra(){
		String r = "";
		for(int i = 0; i < texto.length; i++)
			r = r + texto[i] + "\n";
		return r;
	}
	
	public String ping(String osName){
		StringBuffer buffer = new StringBuffer();
		int tamanho = 10, media = 0;
		if(osName.contains("Windows")){
			int lastIndex = 0;
			String ping = processo.lerProcesso("ping -n 10 www.google.com.br");
			ping = ping.trim();
			texto = ping.split("\n");
			
			for(String sentenca : texto)
				if(sentenca.contains("Reply") && sentenca.contains("time"))
					buffer.append(sentenca + ";");
				
			texto = buffer.toString().split(";");
			buffer.setLength(0);
			
			for(String filtro : texto){
				lastIndex = filtro.lastIndexOf("ms");
				buffer.append(filtro.substring(lastIndex - 2, lastIndex) + ";");
			}
			
			texto = buffer.toString().split(";");
			buffer.setLength(0);
			
			for(String s : texto)
				media += Integer.parseInt(s);
			
			buffer.append("Média: " + media/10 + "ms");
		}
		return buffer.toString();
	}
	
	
	
}
