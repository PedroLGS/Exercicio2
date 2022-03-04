package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	
	public KillController() {
		super();
	}
	public String os() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			System.out.println("É windows");
		}
		if (os.contains("Linux")) {
			System.out.println("É linux");
		}
		return os;
	}
    public void callProcess(String process) {
    		try {
    			Runtime.getRuntime().exec(process);
    		} catch (Exception e) {
    			String erro = e.getMessage();
    			if (erro.contains("2")) {
    				System.err.println("Aplicação não encontrada");
    			}
    			if (erro.contains("740")) {
    				StringBuffer buffer = new StringBuffer();
    				buffer.append("tasklist /fo table");
    				buffer.append(" ");
    				buffer.append(process);

    				String cmdCred = buffer.toString();

    				try {
    					Runtime.getRuntime().exec(cmdCred);
    				} catch (Exception e1) {
    					System.err.println("Chamada de aplicação inválida");
    				}
    			}
    		}
    }
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream leFluxo = p.getInputStream();
			InputStreamReader converteString = new InputStreamReader(leFluxo);
			BufferedReader buffer = new BufferedReader(converteString);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			converteString.close();
			leFluxo.close();

		} catch (Exception e) {
			System.err.println("Chamada inválida");
		}
	}
	public void killProcess(String opc) {
		String os = System.getProperty("os.name");
		String cmdNome = "";
		String cmdPid = "";
		if (os.contains("Windows")) {
			cmdNome = "TASKKILL /IM";
			cmdPid = "TASKKILL /PID";
		}
		if (os.contains("Linux")) {
			cmdPid = "kill -9";
			cmdNome = "pkill -f";
		}

		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(opc);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		} catch (NumberFormatException e) {
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(opc);
		}
		callProcess(buffer.toString());
	}
	
	
}
