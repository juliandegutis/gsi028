package br.com.entrega1.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientRecieveThread implements Runnable {

	private DatagramSocket serverSocket;
	
	private ExecutorService executor = Executors.newCachedThreadPool();


	public ClientRecieveThread( DatagramSocket socket ) {
		super();
		this.serverSocket = socket;
	}

	@Override
	public void run() {
		try {

			byte[] receiveData = new byte[ 1400 ];
			DatagramPacket receivePacket = new DatagramPacket( receiveData, receiveData.length );
			
			while ( true ) {

				Future< String > future = recieve( receivePacket );
				try {
					while( !future.isDone() ) {
						Thread.sleep( 300 );
					}
					future.get();
				} catch ( InterruptedException e ) {
					continue;
				} catch ( ExecutionException e ) {
					continue;
				} finally {
					future.cancel( true );
				}

				/**
				 * Verifica o tamanho do datagrama
				 */
				byte[] data = new byte[ receivePacket.getLength() ];

				/**
				 * Caso o datagrama seja maior do que 1400 bytes, remove o
				 * excesso.
				 */
				System.arraycopy(
					receivePacket.getData(),
					receivePacket.getOffset(),
					data,
					0,
					receivePacket.getLength() );
				String sentence = new String( data );

				System.out.println( sentence );

				Thread.sleep( 1 );

			}

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public Future< String > recieve( DatagramPacket receivePacket ) {
		return executor.submit( () -> {
			serverSocket.receive( receivePacket );
			return null;
		} );
	}


}
