package telnet.gestionNegociacion;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Coleccion de datos que compone el buffer.
 * 
 * @author dmuelas1
 *
 */
public class GestionNegociacionListado extends ArrayBlockingQueue<Elemento> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private boolean hanPreguntadoECHO = false;

	/**
	 * 
	 */
	private boolean hanPreguntadoGA = false;

	/**
	 * Coleccion de datos que compone el buffer.
	 * 
	 * @param capacidad int : Cantidad de bytes
	 */
	public GestionNegociacionListado(int capacidad) {
		super(capacidad);
	}

	/**
	 * Coleccion de datos que compone el buffer.
	 * 
	 * @param capacidad  int : Cantidad de bytes
	 * @param bloqueante boolean : se puede bloquear durante las operaciones.
	 * @param coleccion  Collection - Elementos : Coleccion de elementos.
	 */
	public GestionNegociacionListado(int capacidad, boolean bloqueante, Collection<? extends Elemento> coleccion) {
		super(capacidad, bloqueante, coleccion);
	}

	/**
	 * Coleccion de datos que compone el buffer.
	 * 
	 * @param capacidad  int : Cantidad de bytes
	 * @param bloqueante boolean : se puede bloquear durante las operaciones.
	 */
	public GestionNegociacionListado(int capacidad, boolean bloqueante) {
		super(capacidad, bloqueante);
	}

	/**
	 * Verifica si se ha preguntado ECHO
	 * 
	 * @return booleano : Retorna cierto si se ha recibido ECHO. Retorna falso en
	 *         caso contrario.
	 */
	public boolean getHanPreguntadoECHO() {
		return this.hanPreguntadoECHO;
	}

	/**
	 * Verifica si se ha preguntado GA
	 * 
	 * @return booleano : Retorna cierto si se ha recibido GA. Retorna falso en caso
	 *         contrario.
	 */
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

	/**
	 * Verifica si no se ha recibido un comando.
	 * 
	 * @return booleano : Retorna cierto si no se ha recibido. Retorna falso en caso
	 *         contrario.
	 */
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

	/**
	 * Verifica si no se ha recibido un dato.
	 * 
	 * @return booleano : Retorna cierto si no se ha recibido. Retorna falso en caso
	 *         contrario.
	 */
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
