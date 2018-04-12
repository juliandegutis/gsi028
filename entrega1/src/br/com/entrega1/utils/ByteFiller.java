package br.com.entrega1.utils;


public class ByteFiller {

	public static void fillByteArray( byte[] bytes, String sentence ) {
		byte[] msg = sentence.getBytes();
		
		for( int i = 0 ; i < msg.length; i++ ) {
			bytes[i] = msg[i];
		}
	}
	
}
