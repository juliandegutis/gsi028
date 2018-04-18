package br.com.entrega1.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.context.Context;

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

	public static void main( String args[] ) throws Exception {
		
		/**
		 * Externalização das configurações
		 */
		context = new Context();
		mySettings = Configuration.serverSettings();
		serverSocket = new DatagramSocket( mySettings.getPort() );
		
		/**
		 * Start da thread paralela da leitura de log
		 */
		LogThread log = new LogThread( logQueue );
		new Thread( log ).start();
		
		ExecutorThread executor = new ExecutorThread( logQueue, executeQueue, context );
		new Thread( executor ).start();
		
		while ( true ) {
			
			byte[] receiveData = new byte[ 1400 ];
			byte[] sendData = new byte[ 1400 ];
			
			/**
			 * Recebe o datagrama do cliente
			 */
			DatagramPacket receivePacket = new DatagramPacket( receiveData, receiveData.length );
			serverSocket.receive( receivePacket );
			
			/**
			 * Verifica o tamanho do datagrama
			 */
			byte[] data = new byte[ receivePacket.getLength() ];
			
			/**
			 * Caso o datagrama seja maior do que 4000 bytes, remove o excesso.
			 */
	        System.arraycopy( receivePacket.getData(), receivePacket.getOffset(), data, 0, receivePacket.getLength() );
			String sentence = new String( data );
			
			System.out.println( "Mensagem recebida: " + sentence );
			
			/**
			 * Adiciona a mensagem na fila do log.
			 */
			messageToQueue( sentence );
			
			/**
			 * Envia a confirmação de recebimento para o cliente.
			 */
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String sendBack = "Executado o comando " + sentence;
			sendData = sendBack.getBytes();
			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, IPAddress, port );
			serverSocket.send( sendPacket );
			
		}
	}
	
	/**
	 * Trata a mensagem e adiciona na fila de log
	 * @param message
	 */
	private static void messageToQueue( String message ) {
		List< String > list = new LinkedList< String >( Arrays.asList( message.split( " " ) ) );
		String operation = list.get( 0 );
		list.remove( 0 );
		String header = list.get( 0 );
		list.remove( 0 );
		String log = "";
		for( String current : list ) {
			log = log.concat( current + " " );
		}
		
		executeQueue.add( operation.toUpperCase() + ";" + header + ";" + log.substring( 0, log.length() - 1 ) );
		
	}
}
