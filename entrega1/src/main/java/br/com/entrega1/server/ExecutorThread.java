package br.com.entrega1.server;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import br.com.entrega1.context.Context;
import br.com.entrega1.enums.Operation;

public class ExecutorThread implements Runnable {
	
	private Queue< String > logQueue;
	private Queue< String > executeQueue;
	private Context context;
	private DatagramSocket serverSocket;
	
	public ExecutorThread( DatagramSocket serverSocket, Queue< String > logQueue, Queue< String > executeQueue, Context context ) {
		super();
		this.logQueue = logQueue;
		this.executeQueue = executeQueue;
		this.context = context;
		this.serverSocket = serverSocket;
	}
	
	@Override
	public void run() {
		
		while( true ) {
			String instruction = executeQueue.poll();
			if( instruction != null ) {
				execute( instruction );
			}
		}
		
	}
	
	private void execute( String instruction ) {
		List< String > params = Arrays.asList( instruction.split( ";" ) );
		if( Operation.RETURN.name().equals( params.get( 0 ) ) ) {
			sendDatagram( params );
			return;
		} else if( Operation.INSERT.name().equals( params.get( 0 ) ) ) {
			context.put( new BigInteger( params.get( 1 ) ), params.get( 2 ) );
		} else if( Operation.UPDATE.name().equals( params.get( 0 ) ) ) {
			context.put( new BigInteger( params.get( 1 ) ), params.get( 2 ) );
		} else {
			context.remove( new BigInteger( params.get( 1 ) ) );
		}
		
		logQueue.add( instruction );
		
	}
	
	private void sendDatagram( List< String > params ) {
		try {
			System.out.println( "Enviado o contexto para o remetente" );
			byte[] sendData = context.stringfy().getBytes();
			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, InetAddress.getByName( params.get( 1 ).replace( "/", "" ) ), Integer.parseInt( params.get( 2 ) ) );
			serverSocket.send( sendPacket );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
