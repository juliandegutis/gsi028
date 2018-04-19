package br.com.entrega1.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.enums.Operation;
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

//			while( true ) {
//				
//				System.out.println( "Preparado para receber a mensagem: OPERACAO CHAVE VALOR" );
//				System.out.println( "OPERACAO: INSERT/UPDATE/DELETE/RETURN" );
//				System.out.println( "CHAVE: BigInteger" );
//				System.out.println( "VALOR: String" );
//				/**
//				 * Leitor do buffer do console
//				 */
//				BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );
//				String sentence = inFromUser.readLine();
//				if( validate( sentence ) ) {
//					
//					byte[] my = sentence.getBytes();
//		
//					/**
//					 * Criação do datagrama IP
//					 */
//					DatagramPacket pkg = new DatagramPacket( my, my.length, addr, serverSettings.getPort() );
//					
//					if( pkg.getData().length > 1400 ) {
//						System.out.println( "Mensagem maior do que 4000 bytes" );
//					} else {
//						/**
//						 * Envio do datagrama
//						 */
//						ds.send( pkg );
//						System.out.println(
//							"Mensagem enviada para: " + addr.getHostAddress() + "\n" + "Porta: " + serverSettings.getPort() + "\n"
//								+ "Mensagem: " + sentence );
//					}
//				
//				}
//				
//			}

		}

		catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public static Boolean validate( String sentence ) {
		
		List< String > params = Arrays.asList( sentence.split( " " ) );
		
		if( !Operation.DELETE.name().equals( params.get( 0 ) ) &&
			!Operation.INSERT.name().equals( params.get( 0 ) ) &&
			!Operation.UPDATE.name().equals( params.get( 0 ) )) {
			System.out.println( "Operacao Invalida." );
			return Boolean.FALSE;
		} else {
			if( Operation.DELETE.name().equals( params.get( 0 ) ) && params.size() > 2 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: DELETE 3" );
				return Boolean.FALSE;
			} else if( Operation.RETURN.name().equals( params.get( 0 ) ) && params.size() > 1 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: RETURN" );
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
		
	}

}
