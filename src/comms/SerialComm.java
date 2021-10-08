package comms;

import java.util.Arrays;
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
	
	public char bytetoChar(byte input) {
		
		switch(input) {
			
		case 48:
			return '0';

		case 49:
			return '1';

		case 50:
			return '2';

		case 51:
			return '3';

		case 52:
			return '4';
			
		case 53:
			return '5';
			
		case 54:
			return '6';
			
		case 55:
			return '7';
			
		case 56:
			return '8';
			
		case 57:
			return '9';
		default:
			
			return 'a';
			
			
			
			
		}
		
		
		
	}
	
	public int readByte() {
		byte[] read = {-1};
		byte [] recompose = {-1};
		
		int buffer = 10;
		
		try {
			read = port.readBytes(buffer);
			int startinx = 0;
			
			int endinx = 0;
			
			boolean flagfirst = false;
			
			for(int i =0; i < buffer; i++) {
				//look for start char s = 115
				if(read[i] == 115) {
					
					startinx = i;
					
					flagfirst = true;
						
				}
				else if ( flagfirst && read[i] == 101 ) {
					
					endinx = i;
					
					recompose = Arrays.copyOfRange(read, startinx+1, endinx);
					
					
					break;
					
				}
				
//				System.out.print("Byte " + i + ": " + read[i] + " ");
			}
			
			//endinx - start inx gives us length
			
			
			//Make this work for vals of 100 too
			
			char[] dict = new char [recompose.length];
			
			// do math and return the result
			
			for(int i =0; i < recompose.length;i++) {
				
				
				dict[i] = bytetoChar(recompose[i]);
				
				
			}
			
			
			String s = String.valueOf(dict);
			
			//System.out.println("S is " + s + "\n");
			
			try {
				return 	Integer.parseInt(s);
			}
			catch(NumberFormatException e) {
				return 0;
			}
			
			
		
			
			
			
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
		
			serial = new SerialComm("COM5");
			serial.setDebug(false);
			
			while(true) {
			
			byte[] fuse = new byte[2];
			for(int i=0;i <2; ++i) {
				while(! serial.available());
				
				while(serial.available()) {
					
					
					//System.out.print(serial.readByte() + "\n");
					
					int data = serial.readByte();
					
					if (data != 0) {
					StdDraw.clear();
					
					
					StdDraw.filledCircle((data / 1000.0), 0.5, 0.2);
					
					StdDraw.pause(20);
					
					StdDraw.show();
					}
					
					//Do something with it later 
					
//					StdDraw.square(0, 0, 0.5);
//					
//					StdDraw.show();
					
					
				}
				
				
				
				
				
//				System.out.println("Enter Line, type \"Finished\" to close:");
//				String s = in.nextLine();
//				
////				if(s.equals("Finished")) {
////					break;
////				}
//				
//				for(int i = 0; i < s.length(); ++i) {
//					byte b = (byte) s.charAt(i);
//					
//	//				System.out.println("Sending: " + b + " | ");
//					
//					serial.writeByte(b);
//					
//					while(! serial.available());
//					
//					while(serial.available()) {
//						System.out.print((char)serial.readByte() + " ");
//					}
//					
//				}
//				
				
				
			}
			}
		
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		in.close();
		
		
		
	}
	
	
}
