package telnet.gestionNegociacion;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

/**
 * Gestion del comando DO
 * @author dmuelas1
 *
 */
public class ComandoDO extends Elemento implements Comandos, Opciones, Terminales, Parametros {

	private boolean mePreguntaronECHO = false;
	private boolean mePreguntaronGA = false;
	private boolean comandoCompletado = false;

	/**
	 * Gestion del comando DO
	 */
	public ComandoDO() {
		super();
		super.setOpcionNegociacion(IAC);
		super.setOpcionNegociacion(DO);
	}

	@Override
	public void setOpcionNegociacion(final byte opcion) {
		super.setOpcionNegociacion(opcion);

		if (this.contains(ECHO)) {
			this.setEscritura(IAC);
			this.setEscritura(WONT);
			this.setEscritura(ECHO);
			mePreguntaronECHO = true;
			comandoCompletado = true;

		} else if (this.contains(SUPPRESS_GO_AHEAD)) {
			this.setEscritura(IAC);
			this.setEscritura(WILL);
			this.setEscritura(SUPPRESS_GO_AHEAD);
			mePreguntaronGA = true;
			comandoCompletado = true;

		} else if (this.contains(TERMINAL_TYPE)) {
			this.setEscritura(IAC);
			this.setEscritura(WILL);
			this.setEscritura(TERMINAL_TYPE);
			comandoCompletado = true;

		} else {
			this.setEscritura(IAC);
			this.setEscritura(WONT);
			this.setEscritura(this.getLecturaLast());
			comandoCompletado = true;
		}
	}

	@Override
	public boolean mePreguntaronECHO() {
		return mePreguntaronECHO;
	}

	@Override
	public boolean mePreguntaronGA() {
		return mePreguntaronGA;
	}

	@Override
	public boolean isComandoNegociacionCompletado() {
		return comandoCompletado;
	}

}
