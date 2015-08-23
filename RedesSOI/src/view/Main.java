package view;

import javax.swing.JOptionPane;
import controller.RedesController;
import controller.ProcessosController;

public class Main {

	public static void main(String[] args) {
		ProcessosController processo;
		byte op = 0;
		while(op != 3){
			op = Byte.parseByte(JOptionPane.showInputDialog("MENU:\n1 - IP - EXIBE ADAPTADOR E PROTOCOLO"
					+ "\n2 - PING - TESTA CONEXAO\n3 - SAIR"));
			switch(op){
				case 1:
					processo = new ProcessosController();
					String osName = processo.sisOp();
					RedesController rede = new RedesController();
					rede.ip(osName);
					System.out.println(rede.mostra());
					break;
				case 2:
					processo = new ProcessosController();
					String nomeOS = processo.sisOp();
					RedesController network = new RedesController();
					System.out.println(network.ping(nomeOS));
					break;
				case 3:
					System.exit(0);
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida!");
					break;
			}
		}
	}
}
