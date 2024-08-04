package telnet;

import telnet.constantes.Parametros;

/**
 * Gestion de la conexion Telnet.
 * 
 * @author dmuelas1
 *
 */
public class Telnet implements Parametros {
	private String usuario;
	private String password;
	private String ipRemota;
	private String comando;
	private int puerto;
	private int soTimeout;
	private int soLinger;

	/**
	 * Gestion de la conexion Telnet
	 */
	public Telnet() {
		this.usuario = "";
		this.password = "";
		this.comando = "\r";
		this.ipRemota = REMOTE_HOST;
		this.puerto = REMOTE_PORT;
		this.soTimeout = TIMEOUT;
		this.soLinger = TIMEOUT;
	}

	/**
	 * Usuario de la sesion en la maquina remota.
	 * 
	 * @return String : Usuario
	 */
	public String getUser() {
		return usuario;
	}

	/**
	 * Usuario de la sesion en la maquina remota.
	 * 
	 * @param usuario : String : Usuario.
	 */
	public void setUser(String usuario) {
		this.usuario = usuario + '\r';
	}

	/**
	 * Password de la sesion iniciada en la maquina remota.
	 * 
	 * @return String : Retorna el password
	 */
	public String getPassword() {
		return password + '\r';
	}

	/**
	 * 
	 * Password de la sesion iniciada en la maquina remota.
	 * 
	 * @param password Modifica el password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Comando para la negociacion.
	 * 
	 * @return Retorna el comando
	 */
	public String getComando() {
		return this.comando;
	}

	/**
	 * Comando para la negociacion.
	 * 
	 * @param comando : String : Comando para insertar.
	 */
	public void setComando(String comando) {
		this.comando = comando + '\r';
	}

	/**
	 * Direccion IP de la maquina remota.
	 * <p>
	 * En caso de que el valor sea nulo se entendera que es el loopback de la
	 * maquina local.
	 * </p>
	 * <p>
	 * En caso de que el valor no cumpla con el formato InetAddress se generara una
	 * excepcion en el momento de la conexion.
	 * </p>
	 * 
	 * @return String : Host remoto.
	 */
	public String getRemoteHost() {
		return ipRemota;
	}

	/**
	 * Direccion IP de la maquina remota.
	 * <p>
	 * En caso de que el valor sea nulo se entendera que es el loopback de la
	 * maquina local.
	 * </p>
	 * <p>
	 * En caso de que el valor no cumpla con el formato InetAddress se generara una
	 * excepcion en el momento de la conexion.
	 * </p>
	 * 
	 * @param rhost : String : Host remoto.
	 */
	public void setRemoteHost(String rhost) {
		this.ipRemota = rhost;
	}

	/**
	 * Puerto Telnet de la maquina remota.
	 * 
	 * @return int : Puerto remoto.
	 */
	public int getRemotePort() {
		return puerto;
	}

	/**
	 * Puerto Telnet de la maquina remota.
	 * 
	 * @param rport : int : Puerto remoto.
	 */
	public void setRemotePort(int rport) {
		this.puerto = rport;
	}

	/**
	 * Tiempo que esperara para la ejecucion de un comando y recibir respuesta.
	 * 
	 * @return int : tiempo en milisegundos.
	 */
	public int getSoTimeout() {
		return soTimeout;
	}

	/**
	 * Tiempo que esperara para la ejecucion de un comando y recibir respuesta.
	 * 
	 * @param milisegundos : int : El valor se establece en milisegundos.
	 */
	public void setSoTimeout(int milisegundos) {
		this.soTimeout = milisegundos;
	}

	/**
	 * Tiempo de espera para terminar una conexion despues de desconectar.
	 * <p>
	 * La funcionalidad Linger establece un tiempo de espera para terminar de enviar
	 * y recibir los datos antes de cerrar definitivamente la conexion.
	 * </p>
	 * <p>
	 * El tiempo se expresa en segundos. En caso de que su valor sea cero indica que
	 * se deshabilita esta funcionalidad.
	 * </p>
	 * 
	 * @return int : Tiempo de Linger expresado en segundos.
	 */
	public int getSoLinger() {
		return soLinger;
	}

	/**
	 * Tiempo de espera para terminar una conexion despues de desconectar.
	 * <p>
	 * La funcionalidad Linger establece un tiempo de espera para terminar de enviar
	 * y recibir los datos antes de cerrar definitivamente la conexion.
	 * </p>
	 * <p>
	 * El tiempo se expresa en segundos. En caso de que su valor sea cero indica que
	 * se deshabilita esta funcionalidad.
	 * </p>
	 * 
	 * @param segundos : int : Tiempo de Linger expresados en segundos
	 */
	public void setSoLinger(int segundos) {
		this.soLinger = segundos;
	}

}
