package telnet.gestionNegociacion;

import java.util.Iterator;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

public class ComandoSB extends Elemento implements Comandos, Opciones, Terminales, Parametros {
	private boolean comandoCompletado = false;

	public ComandoSB() {
		super();
		super.setOpcionNegociacion(IAC);
		super.setOpcionNegociacion(SB);
	}

	@Override
	public void setOpcionNegociacion(final byte opcion) {
		super.setOpcionNegociacion(opcion);

		Iterator<Byte> codigo = this.getLecturaIterator();
		int nivel = 0;
		while (codigo.hasNext()) {

			byte b = codigo.next().byteValue();

			if (nivel == 0 && b == TERMINAL_TYPE) {
				nivel++;

			} else if (nivel == 1 && b == TERMINAL_TYPE_SEND) {
				this.setEscritura(IAC);
				this.setEscritura(SB);
				this.setEscritura(TERMINAL_TYPE);
				this.setEscritura(TERMINAL_TYPE_IS);
				this.setEscritura((byte) 'V');
				this.setEscritura((byte) 'T');
				this.setEscritura((byte) '1');
				this.setEscritura((byte) '0');
				this.setEscritura((byte) '0');
				this.setEscritura(IAC);
				this.setEscritura(SE);
				comandoCompletado = true;
			}
		}
	}

	@Override
	public boolean isComandoNegociacionCompletado() {
		return comandoCompletado;
	}

}
