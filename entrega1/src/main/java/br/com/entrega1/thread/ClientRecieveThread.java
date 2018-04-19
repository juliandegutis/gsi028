package br.com.entrega1.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientRecieveThread implements Runnable {

	private DatagramSocket serverSocket;
	
	public ClientRecieveThread( DatagramSocket socket ) {
		super();
		this.serverSocket = socket;
	}
	
	@Override
	public void run() {
		try {
						
			while( true ) {
				
				byte[] receiveData = new byte[ 1400 ];
				
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
				 * Caso o datagrama seja maior do que 1400 bytes, remove o excesso.
				 */
		        System.arraycopy( receivePacket.getData(), receivePacket.getOffset(), data, 0, receivePacket.getLength() );
				String sentence = new String( data );
				
				System.out.println( sentence );
				
				Thread.sleep( 1 );
			
			}
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	

}
