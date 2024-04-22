package telnet.gestionNegociacion;

import java.util.ArrayList;
import java.util.Iterator;

import telnet.constantes.Comandos;
import telnet.constantes.Opciones;
import telnet.constantes.Parametros;
import telnet.constantes.Terminales;

public class Elemento implements Comandos, Opciones, Terminales, Parametros {
	private ArrayList<Byte> listaLectura = new ArrayList<Byte>();
	private ArrayList<Byte> listaEscritura = new ArrayList<Byte>();
	private boolean comandoCompletado = false;

	public Elemento() {
		//
	}

	public void setOpcionNegociacion(final byte opcion) {
		this.listaLectura.add((Byte) opcion);
	}

	public Iterator<Byte> getLecturaIterator() {
		return this.listaLectura.iterator();
	}

	public boolean contains(final byte valor) {
		return this.listaLectura.contains(valor);
	}

	public byte getLecturaLast() {
		return this.listaLectura.get(this.listaLectura.size() - 1).byteValue();
	}

	public byte getLecturaFirst() {
		return this.listaLectura.get(0).byteValue();
	}
	
	public boolean mePreguntaronECHO() {
		return false;
	}

	public boolean mePreguntaronGA() {
		return false;
	}

	public boolean isComandoNegociacionCompletado() {
		return this.comandoCompletado;
	}

	public void setEscritura(byte valor) {
		this.listaEscritura.add(valor);
	}

	public void setEscritura(byte[] valor) {
		for (Byte b : valor) {
			this.listaEscritura.add(b);			
		}
	}
	
	public byte[] getLectura() {
		byte[] lista = new byte[listaLectura.size()];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = listaLectura.get(i).byteValue();
		}
		return lista;
	}

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

	public int getEscrituraCantidad() {
		return this.listaEscritura.size();
	}

	public int getLecturaCantidad() {
		return this.listaLectura.size();
	}

}
