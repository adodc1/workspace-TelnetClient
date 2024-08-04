package telnet.constantes;

/**
 * Listado de comandos para la negiciacion Telnet.
 * 
 * @author dmuelas1
 *
 */
public interface Comandos {
	/**
	 * Comando IAC
	 */
	public static final byte IAC = (byte) (255 & 0xFF);
	
	/**
	 * Comando DONT
	 */
	public static final byte DONT = (byte) (254 & 0xFF);
	
	/**
	 * Comando DO
	 */
	public static final byte DO = (byte) (253 & 0xFF);
	
	/**
	 * Comando WONT
	 */
	public static final byte WONT = (byte) (252 & 0xFF);
	
	/**
	 * Comando WILL
	 */
	public static final byte WILL = (byte) (251 & 0xFF);
	
	/**
	 * Comando SB
	 */
	public static final byte SB = (byte) (250 & 0xFF);
	

	/**
	 * Comando GA
	 */
	public static final byte GA = (byte) (249 & 0xFF);
	
	/**
	 * Comando EL
	 */
	public static final byte EL = (byte) (248 & 0xFF);
	
	/**
	 * Comano EC
	 */
	public static final byte EC = (byte) (247 & 0xFF);
	
	/**
	 * Comando AYT
	 */
	public static final byte AYT = (byte) (246 & 0xFF);
	
	/**
	 * Comando AO
	 */
	public static final byte AO = (byte) (245 & 0xFF);
	
	/**
	 * Comando IP
	 */
	public static final byte IP = (byte) (244 & 0xFF);
	
	/**
	 * Comando BREAK
	 */
	public static final byte BREAK = (byte) (243 & 0xFF);
	
	/**
	 * Comando DM
	 */
	public static final byte DM = (byte) (242 & 0xFF);
	
	/**
	 * Comando NOP
	 */
	public static final byte NOP = (byte) (241 & 0xFF);
	
	/**
	 * Comando SE
	 */
	public static final byte SE = (byte) (240 & 0xFF);
	
	/**
	 * Comando EOR
	 */
	public static final byte EOR = (byte) (239 & 0xFF);
	
	/**
	 * Coamdno ABORT
	 */
	public static final byte ABORT = (byte) (238 & 0xFF);
	
	/**
	 * Comadno SUSP
	 */
	public static final byte SUSP = (byte) (237 & 0xFF);
	
	/**
	 * Comando EOF
	 */
	public static final byte EOF = (byte) (236 & 0xFF);
	
	/**
	 * Comando SYNCH
	 */
	public static final byte SYNCH = (byte) (242 & 0xFF);
	
	/**
	 * Lista de comandos para la negociacion.
	 */
	public static final String commandString[] = { "", "IAC", "DONT", "DO", "WONT", "WILL", "SB", "GA", "EL", "EC",
			"AYT", "AO", "IP", "BRK", "DMARK", "NOP", "SE", "EOR", "ABORT", "SUSP", "EOF" };

}
