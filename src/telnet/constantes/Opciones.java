package telnet.constantes;

/**
 * @author dmuelas1
 *
 */
public interface Opciones {

	/**
	 * BINARY
	 */
	public static final byte BINARY = 0;

	/**
	 * ECHO
	 */
	public static final byte ECHO = 1;

	/**
	 * PREPARE_TO_RECONNECT
	 */
	public static final byte PREPARE_TO_RECONNECT = 2;

	/**
	 * SUPPRESS_GO_AHEAD
	 */
	public static final byte SUPPRESS_GO_AHEAD = 3;

	/**
	 * APPROXIMATE_MESSAGE_SIZE
	 */
	public static final byte APPROXIMATE_MESSAGE_SIZE = 4;

	/**
	 * STATUS
	 */
	public static final byte STATUS = 5;

	/**
	 * TIMING_MARK
	 */
	public static final byte TIMING_MARK = 6;

	/**
	 * REMOTE_CONTROLLED_TRANSMISSION
	 */
	public static final byte REMOTE_CONTROLLED_TRANSMISSION = 7;

	/**
	 * NEGOTIATE_OUTPUT_LINE_WIDTH
	 */
	public static final byte NEGOTIATE_OUTPUT_LINE_WIDTH = 8;

	/**
	 * NEGOTIATE_OUTPUT_PAGE_SIZE
	 */
	public static final byte NEGOTIATE_OUTPUT_PAGE_SIZE = 9;

	/**
	 * NEGOTIATE_CARRIAGE_RETURN
	 */
	public static final byte NEGOTIATE_CARRIAGE_RETURN = 10;

	/**
	 * NEGOTIATE_HORIZONTAL_TAB_STOP
	 */
	public static final byte NEGOTIATE_HORIZONTAL_TAB_STOP = 11;

	/**
	 * NEGOTIATE_HORIZONTAL_TAB
	 */
	public static final byte NEGOTIATE_HORIZONTAL_TAB = 12;

	/**
	 * 
	 */
	public static final byte NEGOTIATE_FORMFEED = 13;

	/**
	 * NEGOTIATE_FORMFEED
	 */
	public static final byte NEGOTIATE_VERTICAL_TAB_STOP = 14;

	/**
	 * NEGOTIATE_VERTICAL_TAB
	 */
	public static final byte NEGOTIATE_VERTICAL_TAB = 15;

	/**
	 * NEGOTIATE_LINEFEED
	 */
	public static final byte NEGOTIATE_LINEFEED = 16;

	/**
	 * EXTENDED_ASCII
	 */
	public static final byte EXTENDED_ASCII = 17;

	/**
	 * FORCE_LOGOUT
	 */
	public static final byte FORCE_LOGOUT = 18;

	/**
	 * BYTE_MACRO
	 */
	public static final byte BYTE_MACRO = 19;

	/**
	 * DATA_ENTRY_TERMINAL
	 */
	public static final byte DATA_ENTRY_TERMINAL = 20;

	/**
	 * SUPDUP
	 */
	public static final byte SUPDUP = 21;

	/**
	 * SUPDUP_OUTPUT
	 */
	public static final byte SUPDUP_OUTPUT = 22;

	/**
	 * SEND_LOCATION
	 */
	public static final byte SEND_LOCATION = 23;

	/**
	 * TERMINAL_TYPE
	 */
	public static final byte TERMINAL_TYPE = 24;

	/**
	 * END_OF_RECORD
	 */
	public static final byte END_OF_RECORD = 25;

	/**
	 * TACACS_USER_IDENTIFICATION
	 */
	public static final byte TACACS_USER_IDENTIFICATION = 26;

	/**
	 * OUTPUT_MARKING
	 */
	public static final byte OUTPUT_MARKING = 27;

	/**
	 * TERMINAL_LOCATION_NUMBER
	 */
	public static final byte TERMINAL_LOCATION_NUMBER = 28;

	/**
	 * REGIME_3270
	 */
	public static final byte REGIME_3270 = 29;

	/**
	 * X3_PAD
	 */
	public static final byte X3_PAD = 30;

	/**
	 * WINDOW_SIZE
	 */
	public static final byte WINDOW_SIZE = 31;

	/**
	 * TERMINAL_SPEED
	 */
	public static final byte TERMINAL_SPEED = 32;

	/**
	 * xxc
	 */
	public static final byte xxc = 33;

	/**
	 * LINEMODE
	 */
	public static final byte LINEMODE = 34;

	/**
	 * X_DISPLAY_LOCATION
	 */
	public static final byte X_DISPLAY_LOCATION = 35;

	/**
	 * OLD_ENVIRONMENT_VARIABLES
	 */
	public static final byte OLD_ENVIRONMENT_VARIABLES = 36;

	/**
	 * AUTHENTICATION
	 */
	public static final byte AUTHENTICATION = 37;

	/**
	 * ENCRYPTION
	 */
	public static final byte ENCRYPTION = 38;

	/**
	 * NEW_ENVIRONMENT_VARIABLES
	 */
	public static final byte NEW_ENVIRONMENT_VARIABLES = 39;

	/**
	 * EXTENDED_OPTIONS_LIST
	 */
	public static final byte EXTENDED_OPTIONS_LIST = (byte) (255 & 0xFF);

	/**
	 * optionString
	 */
	public static final String optionString[] = { "BINARY", "ECHO", "RCP", "SUPPRESS GO AHEAD", "NAME", "STATUS",
			"TIMING MARK", "RCTE", "NAOL", "NAOP", "NAOCRD", "NAOHTS", "NAOHTD", "NAOFFD", "NAOVTS", "NAOVTD", "NAOLFD",
			"EXTEND ASCII", "LOGOUT", "BYTE MACRO", "DATA ENTRY TERMINAL", "SUPDUP", "SUPDUP OUTPUT", "SEND LOCATION",
			"TERMINAL TYPE", "END OF RECORD", "TACACS UID", "OUTPUT MARKING", "TTYLOC", "3270 REGIME", "X.3 PAD",
			"NAWS", "TSPEED", "LFLOW", "LINEMODE", "XDISPLOC", "OLD-ENVIRON", "AUTHENTICATION", "ENCRYPT",
			"NEW-ENVIRON", "TN3270E", "XAUTH", "CHARSET", "RSP", "Com Port Control", "Suppress Local Echo", "Start TLS",
			"KERMIT", "SEND-URL", "FORWARD_X", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "TELOPT PRAGMA LOGON", "TELOPT SSPI LOGON",
			"TELOPT PRAGMA HEARTBEAT", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "Extended-Options-List" };

}
