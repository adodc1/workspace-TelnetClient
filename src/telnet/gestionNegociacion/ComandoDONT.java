package telnet.gestionNegociacion;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

public class ComandoDONT extends Elemento implements Comandos, Opciones, Terminales, Parametros {
	private boolean comandoCompletado = false;
	private boolean mePreguntaronECHO = false;
	private boolean mePreguntaronGA = false;

	public ComandoDONT() {
		super();
		super.setOpcionNegociacion(IAC);
		super.setOpcionNegociacion(DONT);

	}

	@Override
	public void setOpcionNegociacion(final byte opcion) {
		super.setOpcionNegociacion(opcion);

		if (this.contains(ECHO)) {
//			this.setEscritura(IAC);
//			this.setEscritura(WONT);
//			this.setEscritura(ECHO);
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
		return comandoCompletado;
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
