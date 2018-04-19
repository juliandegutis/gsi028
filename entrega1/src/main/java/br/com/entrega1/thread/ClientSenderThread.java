package br.com.entrega1.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import br.com.entrega1.configuration.SocketSetting;
import br.com.entrega1.enums.Operation;

public class ClientSenderThread implements Runnable {

	private DatagramSocket socketServer;

	private InetAddress serverAddr;
	
	private SocketSetting serverSettings;

	public ClientSenderThread( DatagramSocket socketServer, InetAddress serverAddr, SocketSetting serverSettings ) {
		this.socketServer = socketServer;
		this.serverAddr = serverAddr;
		this.serverSettings = serverSettings;
	}

	@Override
	public void run() {

		while ( true ) {

			try {

				while ( true ) {

					System.out.println( "Preparado para receber a mensagem: OPERACAO CHAVE VALOR" );
					System.out.println( "OPERACAO: INSERT/UPDATE/DELETE/RETURN" );
					System.out.println( "CHAVE: BigInteger" );
					System.out.println( "VALOR: String" );
					/**
					 * Leitor do buffer do console
					 */
					BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );
					String sentence = inFromUser.readLine();
					if ( validate( sentence ) ) {

						byte[] my = sentence.getBytes();

						/**
						 * Cria��o do datagrama IP
						 */
						DatagramPacket pkg = new DatagramPacket( my, my.length, serverAddr, serverSettings.getPort() );

						if ( pkg.getData().length > 1400 ) {
							System.out.println( "Mensagem maior do que 4000 bytes" );
						} else {
							/**
							 * Envio do datagrama
							 */
							socketServer.send( pkg );
							System.out.println(
								"Mensagem enviada para: " + serverAddr.getHostAddress() + "\n" + "Porta: "
									+ serverSettings.getPort() + "\n" + "Mensagem: " + sentence );
						}

					}

				}

			} catch ( Exception ex ) {
				ex.printStackTrace();
			}

		}

	}

	public static Boolean validate( String sentence ) {

		List< String > params = Arrays.asList( sentence.split( " " ) );

		if ( !Operation.DELETE.name().equals( params.get( 0 ) ) && !Operation.INSERT.name().equals( params.get( 0 ) )
			&& !Operation.UPDATE.name().equals( params.get( 0 ) ) ) {
			System.out.println( "Operacao Invalida." );
			return Boolean.FALSE;
		} else {
			if ( Operation.DELETE.name().equals( params.get( 0 ) ) && params.size() > 2 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: DELETE 3" );
				return Boolean.FALSE;
			} else if ( Operation.RETURN.name().equals( params.get( 0 ) ) && params.size() > 1 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: RETURN" );
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}

	}

}
