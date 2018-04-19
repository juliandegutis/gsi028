package br.com.entrega1.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.thread.ClientRecieveThread;
import br.com.entrega1.thread.ClientSenderThread;

public class ClientUDP {

	private static SocketSetting serverSettings;
	private static SocketSetting mySettings;
	private static DatagramSocket ds;
	private static ExecutorService executor;

	
	public static void main( String[] args ) {

		try {
						
			/**
			 * Criação das configurações externas dos sockets (cliente/servidor)
			 */
			serverSettings = Configuration.serverSettings();
			mySettings = Configuration.clientSettings();
			ds = new DatagramSocket( mySettings.getPort() );
			executor = Executors.newFixedThreadPool(50);

			/**
			 * Tradução de uma String HOST para a classe InetAddress
			 */
			InetAddress addr = InetAddress.getByName( serverSettings.getHost() );
			
			/**
			 * Instanciando as Threads
			 */
			ClientRecieveThread recieveThread = new ClientRecieveThread( ds );
			ClientSenderThread senderThread = new ClientSenderThread( ds, addr, serverSettings );
			
			executor.execute( recieveThread );
			executor.execute( senderThread );

		}

		catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
