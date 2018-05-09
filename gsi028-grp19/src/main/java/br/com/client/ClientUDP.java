package br.com.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.configuration.Configuration;
import br.com.configuration.SocketSetting;
import br.com.thread.ClientRecieveThread;
import br.com.thread.ClientSenderGrpcThread;
import br.com.thread.ClientSenderThread;

public class ClientUDP {

	private static SocketSetting serverSettings;
	private static SocketSetting mySettings;
	private static SocketSetting grpcClientSettings;
	private static DatagramSocket ds;
	private static ExecutorService executor;

	
	public static void main( String[] args ) {

		try {
						
			/**
			 * Criacao das configuracoes externas dos sockets (cliente/servidor)
			 */
			serverSettings = Configuration.serverSettings();
			mySettings = Configuration.clientSettings();
			grpcClientSettings = Configuration.grpcClientSettings();
			
			ds = new DatagramSocket( mySettings.getPort() );
			executor = Executors.newFixedThreadPool(50);

			/**
			 * Traducao de uma String HOST para a classe InetAddress
			 */
			InetAddress addr = InetAddress.getByName( serverSettings.getHost() );
			
			/**
			 * Instanciando as Threads
			 */
			ClientRecieveThread recieveThread = new ClientRecieveThread( ds );
			ClientSenderThread senderThread = new ClientSenderThread( ds, addr, serverSettings );
			ClientSenderGrpcThread grpcClientThread = new ClientSenderGrpcThread( grpcClientSettings );
			
			executor.execute( recieveThread );
			executor.execute( senderThread );
			executor.execute( grpcClientThread );

		}

		catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
