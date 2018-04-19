package br.com.entrega1.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.entrega1.enums.Operation;

public class ServerRecieveThread implements Runnable {

	private DatagramSocket serverSocket;
	
	private Queue< String > executeQueue;

	public ServerRecieveThread( DatagramSocket serverSocket, Queue< String > executeQueue ) {
		this.serverSocket = serverSocket;
		this.executeQueue = executeQueue;
	}

	@Override
	public void run() {

		while ( true ) {

			try {

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
				 * Caso o datagrama seja maior do que 4000 bytes, remove o
				 * excesso.
				 */
				System.arraycopy(
					receivePacket.getData(),
					receivePacket.getOffset(),
					data,
					0,
					receivePacket.getLength() );
				String sentence = new String( data );

				System.out.println( "Mensagem recebida: " + sentence );

				/**
				 * Adiciona a mensagem na fila do log.
				 */
				messageToQueue( sentence, receivePacket.getAddress(), receivePacket.getPort() );

				/**
				 * Envia a confirmação de recebimento para o cliente.
				 */
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String sendBack = "Executado o comando " + sentence;
				sendData = sendBack.getBytes();
				DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, IPAddress, port );
				serverSocket.send( sendPacket );
				
				Thread.sleep( 1 );

			} catch ( Exception ex ) {
				ex.printStackTrace();
			}

		}

	}

	/**
	 * Trata a mensagem e adiciona na fila de log
	 * 
	 * @param message
	 */
	private void messageToQueue( String message, InetAddress recieveAddress, int recievePort ) {

		List< String > list = new LinkedList< String >( Arrays.asList( message.split( " " ) ) );
		String operation = list.get( 0 );

		if ( Operation.RETURN.name().equals( operation ) ) {
			executeQueue.add( operation + ";" + recieveAddress.toString() + ";" + String.valueOf( recievePort ) );
		} else {

			list.remove( 0 );
			String header = list.get( 0 );
			list.remove( 0 );

			if ( Operation.DELETE.name().equals( operation ) ) {
				executeQueue.add( operation.toUpperCase() + ";" + header );
			} else {
				String log = "";
				for ( String current : list ) {
					log = log.concat( current + " " );
				}
				executeQueue.add( operation.toUpperCase() + ";" + header + ";" + log.substring( 0, log.length() - 1 ) );
			}
		}

	}

}
