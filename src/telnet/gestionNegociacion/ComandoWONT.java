package telnet.gestionNegociacion;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

/**
 * Gestion del comando WONT
 * 
 * @author dmuelas1
 *
 */
public class ComandoWONT extends Elemento implements Comandos, Opciones, Terminales, Parametros {
	private boolean comandoCompletado = false;
	private boolean mePreguntaronECHO = false;
	private boolean mePreguntaronGA = false;

	/**
	 * Gestion del comando WONT
	 */
	public ComandoWONT() {
		super();
		super.setOpcionNegociacion(IAC);
		super.setOpcionNegociacion(WONT);
	}

	@Override
	public void setOpcionNegociacion(final byte opcion) {
		super.setOpcionNegociacion(opcion);

		if (this.contains(ECHO)) {
			this.mePreguntaronECHO = true;
			this.comandoCompletado = true;

		} else if (this.contains(SUPPRESS_GO_AHEAD)) {
			this.mePreguntaronGA = true;
			this.comandoCompletado = true;

		} else if (this.contains(TERMINAL_TYPE)) {
			this.comandoCompletado = true;

		} else {
			this.comandoCompletado = true;

		}

	}

	@Override
	public boolean isComandoNegociacionCompletado() {
		return this.comandoCompletado;
	}

	@Override
	public boolean mePreguntaronECHO() {
		return this.mePreguntaronECHO;
	}

	@Override
	public boolean mePreguntaronGA() {
		return this.mePreguntaronGA;
	}

}
