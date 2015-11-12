package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ProcessosController 
{
	public String sisOp(){
		String so = System.getProperty("os.name");
		return so;
	}
	
	public void testeDoMerge(){
		//TODO
	}
	
	public void chamaProcesso(String caminho){
		try {
			Process processo = Runtime.getRuntime().exec(caminho);
		} catch (IOException e) { //trata o erro de eleva��o(requer administrador) linha abaixo
			if (e.getMessage().contains("eleva")){ //verifica se aplica��o requer eleva��o do usu�rio(administrador)
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c "); //pede as credenciais do Windows
				buffer.append(caminho);
				try {
					Process processo = Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e.getMessage(), 
							"ERRO", JOptionPane.ERROR_MESSAGE);
				}
			} else{ //trata o erro se o processo chamado for inv�lido
				JOptionPane.showMessageDialog(null, e.getMessage(), 
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void testeDoDenys(){
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		//comentario inutil
		
	}
	
	public void mataProcesso(String id){
		StringBuffer buffer = new StringBuffer();
		buffer.append("taskkill.exe ");
		int pid = 0;
		try{ //tenta matar o processo pelo PID
			pid = Integer.parseInt(id); //se houver erro na convers�o cai no catch
			buffer.append("/PID ");
			buffer.append(id);
		}catch(Exception e){ //mata o processo pelo seu nome
			buffer.append("/IM ");
			buffer.append(id);
		}
		try {
			Process processo = Runtime.getRuntime().exec(buffer.toString()); //executa o taskkill
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String lerProcesso(String tarefa){
		StringBuffer buffer = new StringBuffer();
		try {
			Process processo = Runtime.getRuntime().exec(tarefa);
			InputStream fluxo = processo.getInputStream(); //captura a saida do processo (mensagem)
			InputStreamReader leFluxo = new InputStreamReader(fluxo); //associa a vari�vel fluxo e converte esse fluxo em caracteres
			BufferedReader bufferLeitura = new BufferedReader(leFluxo);//joga o fluxo convertido no buffer 
			
			String linha = bufferLeitura.readLine(); 
			//l� o buffer linha a linha(para qdo a linha estiver vazia)
			while(linha != null){
				buffer.append(linha);
				buffer.append("\n");
				linha = bufferLeitura.readLine();
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return buffer.toString();
	}
	
	private void teste(){
		System.out.println("");
	}
}
