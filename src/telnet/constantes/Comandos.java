package telnet.constantes;

public interface Comandos {
	// Command
	public static final byte IAC = (byte) (255 & 0xFF);
	public static final byte DONT = (byte) (254 & 0xFF);
	public static final byte DO = (byte) (253 & 0xFF);
	public static final byte WONT = (byte) (252 & 0xFF);
	public static final byte WILL = (byte) (251 & 0xFF);
	public static final byte SB = (byte) (250 & 0xFF);

	
	public static final byte GA = (byte) (249 & 0xFF);
	public static final byte EL = (byte) (248 & 0xFF);
	public static final byte EC = (byte) (247 & 0xFF);
	public static final byte AYT = (byte) (246 & 0xFF);
	public static final byte AO = (byte) (245 & 0xFF);
	public static final byte IP = (byte) (244 & 0xFF);
	public static final byte BREAK = (byte) (243 & 0xFF);
	public static final byte DM = (byte) (242 & 0xFF);
	public static final byte NOP = (byte) (241 & 0xFF);
	public static final byte SE = (byte) (240 & 0xFF);
	public static final byte EOR = (byte) (239 & 0xFF);
	public static final byte ABORT = (byte) (238 & 0xFF);
	public static final byte SUSP = (byte) (237 & 0xFF);
	public static final byte EOF = (byte) (236 & 0xFF);
	public static final byte SYNCH = (byte) (242 & 0xFF);
	public static final String commandString[] = { "", "IAC", "DONT", "DO", "WONT", "WILL", "SB", "GA", "EL", "EC",
			"AYT", "AO", "IP", "BRK", "DMARK", "NOP", "SE", "EOR", "ABORT", "SUSP", "EOF" };

}
