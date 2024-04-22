package telnet.utiles;

import java.io.Serializable;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

import telnet.gestionNegociacion.Elemento;
import telnet.gestionNegociacion.ComandoWILL;
import telnet.gestionNegociacion.GestionNegociacionListado;

public final class GestionOpciones implements Comandos, Opciones, Terminales, Parametros, Serializable {
	private static final long serialVersionUID = 1L;

	public final static void insertarWILL_ECHO(final GestionNegociacionListado lista) {
		Elemento comando = new ComandoWILL();
		comando.setEscritura(IAC);
		comando.setEscritura(WILL);
		comando.setEscritura(ECHO);
		lista.add(comando);
	}

	public final static void insertarWILL_GA(final GestionNegociacionListado lista) {
		Elemento comando = new ComandoWILL();
		comando.setEscritura(IAC);
		comando.setEscritura(WILL);
		comando.setEscritura(SUPPRESS_GO_AHEAD);
		lista.add(comando);
	}

}
