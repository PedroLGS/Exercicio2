package view;

import controller.KillController;

public class Principal {

	public static void main(String[] args) {
		KillController pCont = new KillController();
		pCont.os();
		
		String process = "tasklist /fo table";
	    pCont.readProcess(process);

		String opc = "";
		pCont.killProcess(opc);
		
	}

}