package br.com.entrega1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.utils.ByteFiller;

public class ClientUDP {

	public static void main( String[] args ) {

		try {

			SocketSetting serverSettings = Configuration.serverSettings();
			SocketSetting mySettings = Configuration.clientSettings();

			InetAddress addr = InetAddress.getByName( serverSettings.getHost() );

			while( true ) {
			
				byte[] receiveData = new byte[ 4000 ];
				byte[] sendData = new byte[ 4000 ];
				
				BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );
				String sentence = inFromUser.readLine();
				ByteFiller.fillByteArray( sendData, sentence );
	
				DatagramPacket pkg = new DatagramPacket( sendData, sendData.length, addr, serverSettings.getPort() );
				DatagramSocket ds = new DatagramSocket();
	
				ds.send( pkg );
				System.out.println(
					"Mensagem enviada para: " + addr.getHostAddress() + "\n" + "Porta: " + serverSettings.getPort() + "\n"
						+ "Mensagem: " + sentence );
	
				ds.close();
			
			}

		}

		catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	private void fillByteArray( byte[] bytes, String sentence ) {
		
	}

}
