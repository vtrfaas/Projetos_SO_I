package view;

import controller.ProcessosController;
import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		ProcessosController processo = new ProcessosController();
		RedesController rede = new RedesController();
		String osName = processo.sisOp();
		rede.ip(osName);
		System.out.println(rede.mostra());

	}

}
