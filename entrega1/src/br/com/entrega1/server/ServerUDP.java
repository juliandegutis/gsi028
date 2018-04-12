package br.com.entrega1.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;

public class ServerUDP {

	private static DatagramSocket serverSocket;
	private static SocketSetting mySettings;

	public static void main( String args[] ) throws Exception {
		
		mySettings = Configuration.serverSettings();
		
		serverSocket = new DatagramSocket( mySettings.getPort() );
		
		byte[] receiveData = new byte[ 4000 ];
		byte[] sendData = new byte[ 4000 ];
		
		while ( true ) {
			
			DatagramPacket receivePacket = new DatagramPacket( receiveData, receiveData.length );
			serverSocket.receive( receivePacket );
			String sentence = new String( receivePacket.getData() );
			System.out.println( "RECEIVED: " + sentence );
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, IPAddress, port );
			serverSocket.send( sendPacket );
			
		}
	}
}
