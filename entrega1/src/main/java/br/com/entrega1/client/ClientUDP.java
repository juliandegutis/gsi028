package br.com.entrega1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import br.com.entrega1.configuration.Configuration;
import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.enums.Operation;

public class ClientUDP {

	public static void main( String[] args ) {

		try {
			
			//Context.load( Paths.get( "resources/log/log.txt" ) );
			
			/**
			 * Cria��o das configura��es externas dos sockets (cliente/servidor)
			 */
			SocketSetting serverSettings = Configuration.serverSettings();
			SocketSetting mySettings = Configuration.clientSettings();
			
			DatagramSocket ds = new DatagramSocket( mySettings.getPort() );
			
			RecieveThread recieveThread = new RecieveThread( ds );
			new Thread( recieveThread ).start();

			/**
			 * Tradu��o de uma String HOST para a classe InetAddress
			 */
			InetAddress addr = InetAddress.getByName( serverSettings.getHost() );

			while( true ) {
				
				System.out.println( "Preparado para receber a mensagem: OPERACAO CHAVE VALOR" );
				System.out.println( "OPERACAO: INSERT/UPDATE/DELETE/RETURN" );
				System.out.println( "CHAVE: BigInteger" );
				System.out.println( "VALOR: String" );
				/**
				 * Leitor do buffer do console
				 */
				BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );
				String sentence = inFromUser.readLine();
				if( validate( sentence ) ) {
					
					byte[] my = sentence.getBytes();
		
					/**
					 * Cria��o do datagrama IP
					 */
					DatagramPacket pkg = new DatagramPacket( my, my.length, addr, serverSettings.getPort() );
					
					if( pkg.getData().length > 1400 ) {
						System.out.println( "Mensagem maior do que 4000 bytes" );
					} else {
						/**
						 * Envio do datagrama
						 */
						ds.send( pkg );
						System.out.println(
							"Mensagem enviada para: " + addr.getHostAddress() + "\n" + "Porta: " + serverSettings.getPort() + "\n"
								+ "Mensagem: " + sentence );
					}
				
				}
				
			}

		}

		catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public static Boolean validate( String sentence ) {
		
		List< String > params = Arrays.asList( sentence.split( " " ) );
		
		if( !Operation.DELETE.name().equals( params.get( 0 ) ) ||
			!Operation.INSERT.name().equals( params.get( 0 ) ) ||
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
