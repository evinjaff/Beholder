package comms;

import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method that you created in Studio 5
	public void writeByte(byte mybyte) {

		try {
			port.writeByte (mybyte);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(debug) {
			System.out.println("[0x" + String.format("%02x", mybyte) + "]");
		}

	}
	
	public byte readByte() {
		byte[] read = {-1};
		try {
			read = port.readBytes(1);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (debug) {
			System.out.println("[0x" + String.format("%02x", read[0]) + "]");
		}
		return read[0];
	}
	
	public boolean available() {
		try {
			if (port.getInputBufferBytesCount() > 0) {
				return true;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		SerialComm serial;
		try {
			serial = new SerialComm("/dev/cu.Bluetooth-Incoming-Port ");
			serial.setDebug(false);
			
			while(true) {
				
				
				System.out.println("Enter Line, type \"Finished\" to close:");
				String s = in.nextLine();
				
//				if(s.equals("Finished")) {
//					break;
//				}
				
				for(int i = 0; i < s.length(); ++i) {
					byte b = (byte) s.charAt(i);
					
	//				System.out.println("Sending: " + b + " | ");
					
					serial.writeByte(b);
					
					while(! serial.available());
					
					while(serial.available()) {
						System.out.print((char)serial.readByte() + " ");
					}
					
				}
				
				serial.writeByte( (byte)'\n' );
				System.out.print((char)serial.readByte());
				
				
			}
		
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		in.close();
		
		
		
	}
	
	
}
