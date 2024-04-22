package telnet.constantes;

public interface Opciones {
	// Option
	public static final byte BINARY = 0;
	public static final byte ECHO = 1;
	public static final byte PREPARE_TO_RECONNECT = 2;
	public static final byte SUPPRESS_GO_AHEAD = 3;
	public static final byte APPROXIMATE_MESSAGE_SIZE = 4;
	public static final byte STATUS = 5;
	public static final byte TIMING_MARK = 6;
	public static final byte REMOTE_CONTROLLED_TRANSMISSION = 7;
	public static final byte NEGOTIATE_OUTPUT_LINE_WIDTH = 8;
	public static final byte NEGOTIATE_OUTPUT_PAGE_SIZE = 9;
	public static final byte NEGOTIATE_CARRIAGE_RETURN = 10;
	public static final byte NEGOTIATE_HORIZONTAL_TAB_STOP = 11;
	public static final byte NEGOTIATE_HORIZONTAL_TAB = 12;
	public static final byte NEGOTIATE_FORMFEED = 13;
	public static final byte NEGOTIATE_VERTICAL_TAB_STOP = 14;
	public static final byte NEGOTIATE_VERTICAL_TAB = 15;
	public static final byte NEGOTIATE_LINEFEED = 16;
	public static final byte EXTENDED_ASCII = 17;
	public static final byte FORCE_LOGOUT = 18;
	public static final byte BYTE_MACRO = 19;
	public static final byte DATA_ENTRY_TERMINAL = 20;
	public static final byte SUPDUP = 21;
	public static final byte SUPDUP_OUTPUT = 22;
	public static final byte SEND_LOCATION = 23;
	public static final byte TERMINAL_TYPE = 24;
	public static final byte END_OF_RECORD = 25;
	public static final byte TACACS_USER_IDENTIFICATION = 26;
	public static final byte OUTPUT_MARKING = 27;
	public static final byte TERMINAL_LOCATION_NUMBER = 28;
	public static final byte REGIME_3270 = 29;
	public static final byte X3_PAD = 30;
	public static final byte WINDOW_SIZE = 31;
	public static final byte TERMINAL_SPEED = 32;
	public static final byte REMOTE_FLOW_CONTROL = 33;
	public static final byte LINEMODE = 34;
	public static final byte X_DISPLAY_LOCATION = 35;
	public static final byte OLD_ENVIRONMENT_VARIABLES = 36;
	public static final byte AUTHENTICATION = 37;
	public static final byte ENCRYPTION = 38;
	public static final byte NEW_ENVIRONMENT_VARIABLES = 39;
	public static final byte EXTENDED_OPTIONS_LIST = (byte) (255 & 0xFF);
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
