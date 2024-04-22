package telnet.gestionNegociacion;

import telnet.constantes.Parametros;

public class DatoRecibido extends Elemento implements Parametros {
	private boolean comandoCompletado = true;

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
