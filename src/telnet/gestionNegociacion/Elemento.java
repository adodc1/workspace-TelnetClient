package telnet.gestionNegociacion;

import java.util.ArrayList;
import java.util.Iterator;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

/**
 * El objeto Elemento contiene todo lo necesario para establecer una conexion
 * Telnet.
 * 
 * @author dmuelas1
 *
 */
public class Elemento implements Comandos, Opciones, Terminales, Parametros {
	private ArrayList<Byte> listaLectura = new ArrayList<Byte>();
	private ArrayList<Byte> listaEscritura = new ArrayList<Byte>();
	private boolean comandoCompletado = false;

	/**
	 * El objeto Elemento contiene todo lo necesario para establecer una conexion
	 * Telnet.
	 */
	public Elemento() {
		//
	}

	/**
	 * Establece la opcion de negociacion.
	 * 
	 * @param opcion : byte : opcion de negociacion
	 */
	public void setOpcionNegociacion(final byte opcion) {
		this.listaLectura.add((Byte) opcion);
	}

	/**
	 * Genera un objeto Iterador para la lectura secuencial del buffer.
	 * 
	 * @return Iterator - Byte : Instancia al objeto Iterador.
	 */
	public Iterator<Byte> getLecturaIterator() {
		return this.listaLectura.iterator();
	}

	/**
	 * Comprueba si existe un valor dentro del buffer.
	 * 
	 * @param valor : byte : Valor a buscar
	 * @return Retorna cierto si el valor existe dentro de buffer. Retorna falso en
	 *         caso contrario.
	 */
	public boolean contains(final byte valor) {
		return this.listaLectura.contains(valor);
	}

	/**
	 * Retorna el ultimo byte leido.
	 * 
	 * @return byte : Ultimo byte leido.
	 */
	public byte getLecturaLast() {
		return this.listaLectura.get(this.listaLectura.size() - 1).byteValue();
	}

	/**
	 * Retorna el primer byte leido desde el buffer.
	 * 
	 * @return byte : Primer byte leido.
	 */
	public byte getLecturaFirst() {
		return this.listaLectura.get(0).byteValue();
	}

	/**
	 * Indica si en algun momento me han preguntado ECHO
	 * 
	 * @return Cierto si se encontro este comando.
	 */
	public boolean mePreguntaronECHO() {
		return false;
	}

	/**
	 * Indica si en algun momento me han preguntado GA
	 * 
	 * @return Cierto si se encontro este comando.
	 */
	public boolean mePreguntaronGA() {
		return false;
	}

	/**
	 * Verifica si la negociacion de la conexion se ha terminado.
	 * 
	 * @return Retorna cierto si la negociacion ha terminado. Retorna falso en caso
	 *         contrario.
	 */
	public boolean isComandoNegociacionCompletado() {
		return this.comandoCompletado;
	}

	/**
	 * Escribe un valor en el buffer.
	 * 
	 * @param valor : Byte : valor para escribir.
	 */
	public void setEscritura(byte valor) {
		this.listaEscritura.add(valor);
	}

	/**
	 * Escribe un grupo de valores en el buffer.
	 * 
	 * @param valor : Byte : Grupo de valores para escribir.
	 */
	public void setEscritura(byte[] valor) {
		for (Byte b : valor) {
			this.listaEscritura.add(b);
		}
	}

	/**
	 * Retorna un grupo de datos resultado de la lectura del buffer.
	 * 
	 * @return byte : Grupo de datos.
	 */
	public byte[] getLectura() {
		byte[] lista = new byte[listaLectura.size()];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = listaLectura.get(i).byteValue();
		}
		return lista;
	}

	/**
	 * Devuelve un brupo de datos desde el buffer de escritura.
	 * 
	 * @return byte : Grupo de datos.
	 */
	public byte[] getEscritura() {
		byte[] lista = new byte[listaEscritura.size()];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = listaEscritura.get(i).byteValue();
		}
		return lista;
	}

	@Override
	public String toString() {
		return new String(this.getLectura());
	}

	/**
	 * Retorna la cantidad de datos escritos.
	 * 
	 * @return int : Cantidad de datos.
	 */
	public int getEscrituraCantidad() {
		return this.listaEscritura.size();
	}

	/**
	 * Retorna la cantidad de datos leidos.
	 * 
	 * @return int : Cantidad de datos.
	 */
	public int getLecturaCantidad() {
		return this.listaLectura.size();
	}

}
