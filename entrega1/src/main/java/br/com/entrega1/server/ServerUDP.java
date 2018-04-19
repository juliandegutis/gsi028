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

		
//		while ( true ) {
//			
//			byte[] receiveData = new byte[ 1400 ];
//			byte[] sendData = new byte[ 1400 ];
//			
//			/**
//			 * Recebe o datagrama do cliente
//			 */
//			DatagramPacket receivePacket = new DatagramPacket( receiveData, receiveData.length );
//			serverSocket.receive( receivePacket );
//			
//			/**
//			 * Verifica o tamanho do datagrama
//			 */
//			byte[] data = new byte[ receivePacket.getLength() ];
//			
//			/**
//			 * Caso o datagrama seja maior do que 4000 bytes, remove o excesso.
//			 */
//	        System.arraycopy( receivePacket.getData(), receivePacket.getOffset(), data, 0, receivePacket.getLength() );
//			String sentence = new String( data );
//			
//			System.out.println( "Mensagem recebida: " + sentence );
//			
//			/**
//			 * Adiciona a mensagem na fila do log.
//			 */
//			messageToQueue( sentence, receivePacket.getAddress(), receivePacket.getPort() );
//			
//			/**
//			 * Envia a confirmação de recebimento para o cliente.
//			 */
//			InetAddress IPAddress = receivePacket.getAddress();
//			int port = receivePacket.getPort();
//			String sendBack = "Executado o comando " + sentence;
//			sendData = sendBack.getBytes();
//			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, IPAddress, port );
//			serverSocket.send( sendPacket );
//			
//		}
	}
	
//	/**
//	 * Trata a mensagem e adiciona na fila de log
//	 * @param message
//	 */
//	private static void messageToQueue( String message, InetAddress recieveAddress, int recievePort ) {
//		
//		List< String > list = new LinkedList< String >( Arrays.asList( message.split( " " ) ) );
//		String operation = list.get( 0 );
//		
//		if( Operation.RETURN.name().equals( operation ) ) {
//			executeQueue.add( operation + ";" + recieveAddress.toString() + ";" + String.valueOf( recievePort ) );
//		} else {
//			
//			list.remove( 0 );
//			String header = list.get( 0 );
//			list.remove( 0 );
//			
//			if( Operation.DELETE.name().equals( operation ) ) {
//				executeQueue.add( operation.toUpperCase() + ";" + header );
//			} else {
//				String log = "";
//				for( String current : list ) {
//					log = log.concat( current + " " );
//				}
//				executeQueue.add( operation.toUpperCase() + ";" + header + ";" + log.substring( 0, log.length() - 1 ) );
//			}
//		}
//				
//	}
}
