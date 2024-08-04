package telnet.constantes;

/**
 * @author dmuelas1
 *
 */
public interface Parametros {

	/**
	 * Tamaño predefinido del buffer
	 */
	public static final int BUFFER_SIZE = 1024;
	
	/**
	 * Deteccion de la identificacion del usuario
	 */
	public static final String LOGIN = "ogin:";
	
	/**
	 * Deteccion de la identificacion del password
	 */
	public static final String PASSWORD = "assword:";
	
	/**
	 * Deteccion de la identificacion del simbolo del sistema windows
	 */
	public static final String PROMPT_WINDOWS = ":\\";
	
	/**
	 * Deteccion de la identificacion del simbolo del sistema linux
	 */
	public static final String PROMPT_LINUX = ":~";
	
	/**
	 * Puerto remoto por defecto 
	 */
	public static final int REMOTE_PORT = 23;
	
	/**
	 * Host remoto
	 */
	public static final String REMOTE_HOST = null;
	
	/**
	 * Timeout por defecto
	 */
	public static final int TIMEOUT = 60000;
	
	/**
	 * Linger por defecto.
	 */
	public static final int LINGER = 60;

}
