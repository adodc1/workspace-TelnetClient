package telnet.utiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

public final class Utiles implements Comandos, Opciones, Terminales, Parametros {

	public final static boolean isInetAddressOK(String address) {
		if (address != null) {
			String regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(address);
			return matcher.matches();
		} else {
			return true;
		}
	}

	public final static synchronized String lecturasDatos(final DataInputStream flujoLectura, String[] patron)
			throws IOException {
		
		byte[] lectura = new byte[BUFFER_SIZE];
		int cantidad = 0;
		String s = "";

		try {

			do {
				cantidad = flujoLectura.read(lectura);
				if (cantidad > 0) {
					s += new String(lectura, 0, cantidad);
				} else if (cantidad == -1) {
					throw new IOException("DataInputStream.read() retorna -1");
				}

			} while (!filtroPatron(s, patron) && cantidad > 0);

		} catch (IOException e) {
			IOException ex = new IOException("telnet.utiles.Utiles.lecturasDatos: " + e.getMessage());
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
		return s;
	}

	public final static synchronized boolean filtroPatron(String cadena, String[] patron) {
		boolean existe = false;
		for (String s : patron) {
			existe |= cadena.contains(s);
		}
		return existe;
	}

	public final static synchronized void lecturasOpciones(final byte[] buffer, int cantidad) {
		System.out.println("From SRV:");
		composicionOpciones(buffer, cantidad);
	}

	public final static synchronized void escrituraOpciones(final byte[] buffer, int cantidad) {
		System.out.println("From CLI:");
		composicionOpciones(buffer, cantidad);
	}

	public final static synchronized void escrituraDatos(final DataOutputStream flujoSalida, String comando)
			throws IOException {

		flujoSalida.write(comando.getBytes());
		flujoSalida.flush();
	}

	public final static void esperar(long milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			//
		}
	}

	private static void composicionOpciones(final byte[] buffer, int cantidad) {
		
		int nivel = 0;
		
		for (int i = 0; i < cantidad; i++) {
			
			byte b = buffer[i];
			
			if (b == -1) {
				System.out.print(commandString[-b] + " ");
				nivel = 0;
			} else if (b < 0 && nivel == 0) {
				System.out.print(commandString[-b] + " ");
				nivel++;
			} else if (b < 40 && nivel == 1) {
				System.out.print(optionString[b] + " ");
				nivel++;
			} else if (nivel == 2) {
				System.out.print(terminalString[b] + " ");
				nivel++;
			} else {
				System.out.print((char) b + " ");
			}
		}
		System.out.println();
	}

}
