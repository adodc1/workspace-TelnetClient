package telnet.utiles;

import java.io.Serializable;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

import telnet.gestionNegociacion.Elemento;
import telnet.gestionNegociacion.ComandoWILL;
import telnet.gestionNegociacion.GestionNegociacionListado;

/**
 * Metodos para la gestion de la negociacion
 * 
 * @author dmuelas1
 *
 */
public final class GestionOpciones implements Comandos, Opciones, Terminales, Parametros, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Inserta un comando WILL completo en la comunicacion.
	 * 
	 * @param lista Instancia al listado de comandos para la negociacion
	 */
	public final static void insertarWILL_ECHO(final GestionNegociacionListado lista) {
		Elemento comando = new ComandoWILL();
		comando.setEscritura(IAC);
		comando.setEscritura(WILL);
		comando.setEscritura(ECHO);
		lista.add(comando);
	}

	/**
	 * Inserta un comando WILL_GA completo en la comunicacion.
	 * 
	 * @param lista Instancia al listado de comandos para la negociacion
	 */
	public final static void insertarWILL_GA(final GestionNegociacionListado lista) {
		Elemento comando = new ComandoWILL();
		comando.setEscritura(IAC);
		comando.setEscritura(WILL);
		comando.setEscritura(SUPPRESS_GO_AHEAD);
		lista.add(comando);
	}

}
