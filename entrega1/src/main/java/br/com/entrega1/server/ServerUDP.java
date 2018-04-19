package br.com.entrega1.server;

import java.net.DatagramSocket;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.context.Context;
import br.com.entrega1.thread.ExecutorThread;
import br.com.entrega1.thread.LogThread;
import br.com.entrega1.thread.ServerRecieveThread;

/**
 * Servidor UDP
 * @author juliang
 *
 */
public class ServerUDP {

	private static DatagramSocket serverSocket;
	private static SocketSetting mySettings;
	private static Queue< String > logQueue = new LinkedList< String >();
	private static Queue< String > executeQueue = new LinkedList< String >();
	private static Context context;
	private static ExecutorService executor;
	
	
	public static void main( String args[] ) throws Exception {
		
		/**
		 * Externalização das configurações
		 */
		context = new Context();
		context.load( Paths.get( "src/main/resources/log/log.txt" ) );
		mySettings = Configuration.serverSettings();
		serverSocket = new DatagramSocket( mySettings.getPort() );
		
		executor = Executors.newFixedThreadPool(50);
		
		/**
		 * Declaração das Threads
		 */
		LogThread logThread = new LogThread( logQueue );
		
		ExecutorThread executorThread = new ExecutorThread( serverSocket, logQueue, executeQueue, context );
				
		ServerRecieveThread recieveThread = new ServerRecieveThread( serverSocket, executeQueue );
		
		/**
		 * Execução das Threads
		 */
		executor.execute( logThread );
		executor.execute( executorThread );
		executor.execute( recieveThread );

	}

}
