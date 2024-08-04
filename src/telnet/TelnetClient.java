package telnet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import telnet.gestionNegociacion.*;
import telnet.utiles.GestionOpciones;
import telnet.utiles.Utiles;
import telnet.constantes.*;

/**
 * @author dmuelas1
 *
 */
public class TelnetClient extends Telnet {

	private Socket miSocket;
	private DataOutputStream flujoSalida;
	private DataInputStream flujoEntrada;
	private ThreadLectura threadLectura;
	private ThreadEscritura threadEscritura;
	private GestionNegociacionListado bufferFifo;
	private Gestion gestion;

	/**
	 * Gestion de la conexion del cliente.
	 */
	public TelnetClient() {
		super();
	}

	/**
	 * Retorna el estado de la conexion.
	 * 
	 * @return boolean : retorna cierto si la conexion esta activa y el socket aun
	 *         esta abierto. Retorna falso en caso contrario.
	 */
	public synchronized boolean isConnected() {
		if (this.gestion != null) {
			return this.gestion.isConectado();
		} else {
			return false;
		}
	}

	/**
	 * Desconecta el socket y el flujo de entrada y salida.
	 * 
	 * @throws IOException Si ocurre un fallo de E/S.
	 */
	public synchronized void disconnect() throws IOException {
		try {
			if (this.miSocket != null) {
				if (this.miSocket.isConnected() && !this.miSocket.isClosed()) {
					this.flujoSalida.close();
					this.flujoEntrada.close();
					this.miSocket.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece una conexion con la maquina remota.
	 * 
	 * @param remoteHost : IP de la maquina remota. Especificar un valor nulo es
	 *                   equivalente a especificar una dirección de la interfaz
	 *                   loopback.
	 * @param remotePort : Identificador del puerto de la maquina remota que
	 *                   proporciona el servicio Telnet.
	 * @throws IOException : En caso de que se haya producido algun error.
	 */
	public synchronized void connect(String remoteHost, int remotePort) throws IOException {
		this.setRemoteHost(remoteHost);
		this.setRemotePort(remotePort);
		this.connect();
	}

	/**
	 * Establece una conexion con la maquina remota.
	 * 
	 * @param remoteHost : IP de la maquina remota. Especificar un valor nulo es
	 *                   equivalente a especificar una dirección de la interfaz
	 *                   loopback.
	 * @throws IOException : En caso de que se haya producido algun error.
	 */
	public synchronized void connect(String remoteHost) throws IOException {
		this.setRemoteHost(remoteHost);
		this.connect();
	}

	/**
	 * Establece una conexion con la maquina remota.
	 * 
	 * @throws IOException : En caso de que se haya producido algun error.
	 */
	public synchronized void connect() throws IOException {

		this.bufferFifo = new GestionNegociacionListado(BUFFER_SIZE);

		try {
			if (this.miSocket == null) {

				if (!Utiles.isInetAddressOK(this.getRemoteHost())) {
					throw new IOException(" IP del host remoto indeterminada.");
				}

				if (this.getRemotePort() <= 0) {
					throw new IOException(" Puerto fuera de rango.");
				}

				this.miSocket = new Socket();
				this.miSocket.connect(new InetSocketAddress(this.getRemoteHost(), this.getRemotePort()),
						getSoTimeout());

				this.flujoSalida = new DataOutputStream(miSocket.getOutputStream());
				this.flujoEntrada = new DataInputStream(miSocket.getInputStream());

				this.gestion = new Gestion(bufferFifo, flujoEntrada, flujoSalida);

				this.threadLectura = new ThreadLectura(this.gestion);
				this.threadLectura.start();

				this.threadEscritura = new ThreadEscritura(this.gestion);
				this.gestion.setUsuario(this.getUser());
				this.gestion.setPassword(this.getPassword());
				this.threadEscritura.start();

				this.threadLectura.join();
				this.threadEscritura.join();

				this.miSocket.setSoTimeout(getSoTimeout());

			}
		} catch (InterruptedException e) {
			//
		}
	}

	/**
	 * Escribe el comando que queremos ejecutar en la maquina remota.
	 * 
	 * @param comando : los datos a escribir.
	 * @throws IOException : si se produce un error de E/S.
	 */
	public synchronized void write(String comando) throws IOException {
		super.setComando(comando);
		Utiles.escrituraDatos(flujoSalida, this.getComando());
	}

	/**
	 * Lectura secuencial del buffer de entrada.
	 * <p>
	 * Realiza una lectura del flujo procedente de la maquina remota hasta encontrar
	 * el simbolo del sistema
	 * </p>
	 * 
	 * @return String : texto recibido desde la maquina remota.
	 * @throws IOException :
	 *                     <p>
	 *                     Si el primer byte no se puede leer por algún motivo que
	 *                     no sea el final del archivo.
	 *                     </p>
	 *                     <p>
	 *                     La secuencia se ha cerrado por lo que ya no admite la
	 *                     lectura.
	 *                     </p>
	 *                     <p>
	 *                     Porque se produce un error de E/S.
	 *                     </p>
	 */
	public synchronized String read() throws IOException {
		return Utiles.lecturasDatos(flujoEntrada, new String[] { PROMPT_LINUX, PROMPT_WINDOWS });
	}

	protected class ThreadLectura extends Thread implements Runnable {
		private final Gestion hilo;

		public ThreadLectura(Gestion hilo) {
			super("ThreadLectura");
			this.hilo = hilo;
		}

		public void run() {

			hilo.hanPreguntadoECHO();
			hilo.hanPreguntadoGA();

			do {
				hilo.Pregunta();
			} while (!this.hilo.isConectado());
		}

	}

	protected class ThreadEscritura extends Thread implements Runnable {
		private final Gestion hilo;

		public ThreadEscritura(Gestion hilo) {
			super("ThreadEscritura");
			this.hilo = hilo;
		}

		public void run() {
			do {
				hilo.Respuesta();
			} while (!this.hilo.isConectado());
		}

	}

	protected class Gestion {
		private final GestionNegociacionListado bufferFifo;
		private final DataInputStream flujoEntrada;
		private final DataOutputStream flujoSalida;

		private String usuario = "";
		private String password = "";

		private boolean isConectado = false;
		private boolean isLogin = false;
		private boolean isPassword = false;
		private boolean escribiendo = false;

		public Gestion(GestionNegociacionListado buffer, DataInputStream flujoEntrada, DataOutputStream flujoSalida) {
			super();
			this.bufferFifo = buffer;
			this.flujoEntrada = flujoEntrada;
			this.flujoSalida = flujoSalida;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isConectado() {
			return this.isConectado;
		}

		public synchronized void Pregunta() {
			final byte[] buffer = new byte[BUFFER_SIZE];
			int indice = 0;
			Elemento comando = null;
			int nivel = 0;

			try {

				if (escribiendo) {
					wait();
				}

				if (this.isConectado) {
					return;
				}

				do {
					byte b = flujoEntrada.readByte();

					if (nivel == 0 && b == Comandos.IAC) {
						nivel = 1;

					} else if (nivel == 1 && b == Comandos.DO) {
						nivel++;
						comando = new ComandoDO();

					} else if (nivel == 1 && b == Comandos.DONT) {
						nivel++;
						comando = new ComandoDONT();

					} else if (nivel == 1 && b == Comandos.WILL) {
						nivel++;
						comando = new ComandoWILL();

					} else if (nivel == 1 && b == Comandos.WONT) {
						nivel++;
						comando = new ComandoWONT();

					} else if (nivel == 1 && b == Comandos.SB) {
						nivel++;
						comando = new ComandoSB();

					} else if (nivel == 1 && b == Comandos.SE) {
						nivel = 0;

					} else if (nivel == 2) {
						if (comando != null) {
							comando.setOpcionNegociacion(b);
							if (comando.isComandoNegociacionCompletado()) {
								nivel = 0;
								System.arraycopy(comando.getLectura(), 0, buffer, indice, comando.getLecturaCantidad());
								indice += comando.getEscrituraCantidad();
								this.bufferFifo.add(comando);
							}
						}

					} else {
						if (comando instanceof DatoRecibido) {
							comando.setOpcionNegociacion(b);
						} else {
							comando = new DatoRecibido();
							this.bufferFifo.add(comando);
							comando.setOpcionNegociacion(b);
						}
					}

				} while (flujoEntrada.available() > 0);

				if (indice > 0) {
					Utiles.lecturasOpciones(buffer, indice);
				}

			} catch (EOFException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				//
			}

			escribiendo = true;
			notify();

		}

		public synchronized void Respuesta() {
			final byte[] buffer = new byte[BUFFER_SIZE];
			int indice = 0;
			try {

				if (!escribiendo) {
					wait();
				}

				if (this.isConectado) {
					return;
				}

				do {
					Elemento comando = this.bufferFifo.take();

					if (comando instanceof DatoRecibido) {

						System.out.println(comando.toString());

						if (!this.isLogin && Utiles.filtroPatron(comando.toString(), new String[] { LOGIN })) {
							flujoSalida.writeBytes(this.usuario);
							flujoSalida.flush();
							this.isLogin = true;

						} else if (!this.isPassword
								&& Utiles.filtroPatron(comando.toString(), new String[] { PASSWORD })) {
							flujoSalida.writeBytes(this.password);
							flujoSalida.flush();
							this.isPassword = true;

						} else {
							this.isConectado |= Utiles.filtroPatron(comando.toString(),
									new String[] { PROMPT_LINUX, PROMPT_WINDOWS });
						}

					} else {
						if (comando.getEscrituraCantidad() > 0) {
							System.arraycopy(comando.getEscritura(), 0, buffer, indice, comando.getEscrituraCantidad());
							indice += comando.getEscrituraCantidad();
						}
					}
				} while (!this.bufferFifo.isEmpty());

				if (indice > 0) {
					Utiles.escrituraOpciones(buffer, indice);
					flujoSalida.write(buffer, 0, indice);
					flujoSalida.flush();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			escribiendo = !this.bufferFifo.isEmpty();
			notify();
		}

		public synchronized void hanPreguntadoECHO() {
			try {
				if (escribiendo) {
					wait();
				}

				if (!this.bufferFifo.getHanPreguntadoECHO()) {
					GestionOpciones.insertarWILL_ECHO(this.bufferFifo);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			escribiendo = true;
			notify();
		}

		public synchronized void hanPreguntadoGA() {

			try {
				if (escribiendo) {
					wait();
				}

				if (!this.bufferFifo.getHanPreguntadoGA()) {
					GestionOpciones.insertarWILL_GA(this.bufferFifo);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			escribiendo = true;
			notify();
		}

	}

}
