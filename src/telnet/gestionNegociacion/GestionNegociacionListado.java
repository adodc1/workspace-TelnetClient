package telnet.gestionNegociacion;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class GestionNegociacionListado extends ArrayBlockingQueue<Elemento> {
	private static final long serialVersionUID = 1L;

	private boolean hanPreguntadoECHO = false;
	private boolean hanPreguntadoGA = false;

	public GestionNegociacionListado(int capacidad) {
		super(capacidad);
	}

	public GestionNegociacionListado(int capacidad, boolean bloqueante, Collection<? extends Elemento> coleccion) {
		super(capacidad, bloqueante, coleccion);
	}

	public GestionNegociacionListado(int capacidad, boolean bloqueante) {
		super(capacidad, bloqueante);
	}

	public boolean getHanPreguntadoECHO() {
		return this.hanPreguntadoECHO;
	}

	public boolean getHanPreguntadoGA() {
		return this.hanPreguntadoGA;
	}

	@Override
	public boolean add(Elemento e) {
		if (e != null) {
			this.hanPreguntadoECHO |= e.mePreguntaronECHO();
			this.hanPreguntadoGA |= e.mePreguntaronGA();
		}
		return super.add(e);
	}

	public boolean isComandoEmpty() {
		Iterator<Elemento> c = super.iterator();
		while (c.hasNext()) {
			if (c.next() instanceof DatoRecibido) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean isDatoEmpty() {
		Iterator<Elemento> c = super.iterator();
		while (c.hasNext()) {
			if (c.next() instanceof DatoRecibido) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

}
