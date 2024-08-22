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

/**
 * @author dmuelas1
 *
 */
public final class Utiles implements Comandos, Opciones, Terminales, Parametros {

	/**
	 * Verifica si la IP proporcionada tiene un formato correcto segun el estandar.
	 * 
	 * @param address : String : direccion IP.
	 * @return Retorna cierto si la IP tiene un formato correcto. Retorna falso en
	 *         caso contrario.
	 */
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

	/**
	 * Recupera los datos procedentes del servidor que aun estan en el buffer del
	 * sistema y los pone en una cadena para ser interpretados.
	 * 
	 * @param flujoLectura : DataInputStream : Buffer de lectura del sistema.
	 * @param patron       : String : Simbolo se sistema para identificar si ha
	 *                     llegado al final de la transmision.
	 * @return Retorna una cadena con los datos leidos.
	 * @throws IOException Si no se puede leer el primer byte por algún motivo que
	 *                     no sea el final del archivo, la secuencia se ha cerrado y
	 *                     la secuencia de entrada subyacente no admite la lectura
	 *                     después del cierre, o se produce otro error de E/S.
	 */
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

	/**
	 * Verifica si se ha encontrado el patron.
	 * 
	 * @param cadena : String : Cadena sobre la que se busca el patron.
	 * @param patron : String : Simbolo se sistema para identificar si ha llegado al
	 *               final de la transmision.
	 * @return Retorna cierto si se ha encontrado el patron. Retorna falso en caso
	 *         contrario.
	 */
	public final static synchronized boolean filtroPatron(String cadena, String[] patron) {
		boolean existe = false;
		for (String s : patron) {
			existe |= cadena.contains(s);
		}
		return existe;
	}

	/**
	 * Interpreta los comandos procedentes del servidor.
	 * 
	 * @param buffer   : byte : Bloque de datos.
	 * @param cantidad : int : Candidad de datos.
	 */
	public final static synchronized void lecturasOpciones(final byte[] buffer, int cantidad,
			StringBuffer flujoEventos) {
		flujoEventos.append("From SRV:");
		composicionOpciones(buffer, cantidad, flujoEventos);
	}

	/**
	 * Interpreta los comandos procedentes del cliente.
	 * 
	 * @param buffer   : byte : Bloque de datos.
	 * @param cantidad : int : Candidad de datos.
	 */
	public final static synchronized void escrituraOpciones(final byte[] buffer, int cantidad,
			StringBuffer flujoEventos) {
		flujoEventos.append("From CLI:");
		composicionOpciones(buffer, cantidad, flujoEventos);
	}

	/**
	 * Envia un comando por el canal de salida.
	 * 
	 * @param flujoSalida : DataOutputStream : Buffer de datos del sistema.
	 * @param comando     : String : Comando para enviar.
	 * @throws IOException En casi de existir un fallo de E/S
	 */
	public final static synchronized void escrituraDatos(final DataOutputStream flujoSalida, String comando)
			throws IOException {

		flujoSalida.write(comando.getBytes());
		flujoSalida.flush();
	}

	/**
	 * @param milisegundos : long : Cantidad de milisegundos
	 */
	public final static void esperar(long milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			//
		}
	}

	private static void composicionOpciones(final byte[] buffer, int cantidad, StringBuffer flujoEventos) {

		int nivel = 0;

		for (int i = 0; i < cantidad; i++) {

			byte b = buffer[i];

			if (b == -1) {
				flujoEventos.append(commandString[-b] + " ");
				nivel = 0;
			} else if (b < 0 && nivel == 0) {
				flujoEventos.append(commandString[-b] + " ");
				nivel++;
			} else if (b < 40 && nivel == 1) {
				flujoEventos.append(optionString[b] + " ");
				nivel++;
			} else if (nivel == 2) {
				flujoEventos.append(terminalString[b] + " ");
				nivel++;
			} else {
				flujoEventos.append((char) b + " ");
			}
		}
	}

}
