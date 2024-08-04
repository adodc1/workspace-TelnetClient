package telnet.gestionNegociacion;

import telnet.constantes.Parametros;

/**
 * Dato leido que extiende de Elemento.
 * 
 * @author dmuelas1
 *
 */
public class DatoRecibido extends Elemento implements Parametros {
	private boolean comandoCompletado = true;

	/**
	 * Dato leido que extiende de Elemento.
	 */
	public DatoRecibido() {
		super();
	}

	@Override
	public void setOpcionNegociacion(final byte opcion) {
		super.setOpcionNegociacion(opcion);
	}

	@Override
	public boolean isComandoNegociacionCompletado() {
		return this.comandoCompletado;
	}

}
