package telnet.utiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author dmuelas1
 *
 */
public final class TerminalVT100 {
	private static int currentRow = 0;
	private static int currentCol = 0;

	private static boolean isRow = true;
	private static int tempRow = 0;
	private static int tempCol = 0;

	/**
	 * Gestion de los comando recibidos desde la consola VT100
	 * 
	 * @param buffer : String : Bloque de datos recibidos desde la terminal VT100.
	 * @return Retorna una cadena filtrada.
	 */
	public static final String parseVT100(String buffer) {

		currentRow = 0;
		currentCol = 0;

		ByteArrayInputStream in = new ByteArrayInputStream(buffer.getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		terminal(in, out, 0);

		return out.toString();

	}

	private static final int terminal(ByteArrayInputStream in, ByteArrayOutputStream out, int nivel) {
		int c = in.read();
		while (c != -1) {
			if (nivel == 0) {
				if (c == 27) {
					isRow = true;
					tempRow = 0;
					tempCol = 0;
					c = terminal(in, out, nivel + 1);

					if (c == 'H') {
						// Situa el cursor en una posicion absoluta.
						for (; currentRow < tempRow; currentRow++) {
							out.write('\n');
							currentCol = 0;
						}

						for (; currentCol < tempCol; currentCol++) {
							out.write(' ');
						}

					} else {
						// En caso de encontrar un caracter desconocido tras el ESC.
						continue;
					}

				} else {
					// Guardamos los caracteres de forma secuencial en el buffer.
					if ((char) c == '\n') {
						currentRow++;
						currentCol = 0;
					}
					out.write((char) c);
					currentCol++;
				}

			} else if (nivel == 1) {
				if (c == '[') {
					return terminal(in, out, nivel + 1);
				} else {
					return c;
				}

			} else if (nivel >= 2) {
				if (c >= '0' && c <= '9') {
					if (isRow) {
						tempRow = (tempRow * 10) + c - 48;
					} else {
						tempCol = (tempCol * 10) + c - 48;
					}
					return terminal(in, out, nivel + 1);

				} else if (c == ';') {
					isRow = false;
					return terminal(in, out, nivel + 1);

				} else if (c <= '0' || c >= '9') {
					return c;
				}
			}
			c = in.read();
		}
		return 0;
	}

}
