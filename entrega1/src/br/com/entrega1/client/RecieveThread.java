package br.com.entrega1.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import br.com.entrega1.configuration.SocketSetting;

public class RecieveThread implements Runnable {

	private SocketSetting settings;
	private DatagramSocket serverSocket;
	
	public RecieveThread( SocketSetting settings ) {
		super();
		this.settings = settings;
	}
	
	@Override
	public void run() {
		try {
			
			serverSocket = new DatagramSocket( settings.getPort() );
			
			while( true ) {
				
				byte[] receiveData = new byte[ 4000 ];
				
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
				
				System.out.println( "Comando executado pelo servidor: " + sentence );
			
			}
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
