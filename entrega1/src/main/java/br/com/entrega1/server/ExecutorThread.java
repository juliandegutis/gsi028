package br.com.entrega1.server;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import br.com.entrega1.context.Context;
import br.com.entrega1.enums.Operation;

public class ExecutorThread implements Runnable {
	
	private Queue< String > logQueue;
	private Queue< String > executeQueue;
	private Context context;
	
	public ExecutorThread( Queue< String > logQueue, Queue< String > executeQueue, Context context ) {
		super();
		this.logQueue = logQueue;
		this.executeQueue = executeQueue;
		this.context = context;
	}
	
	@Override
	public void run() {
		
		while( true ) {
			String instruction = executeQueue.poll();
			if( instruction != null ) {
				execute( instruction );
				logQueue.add( instruction );
			}
		}
		
	}
	
	private void execute( String instruction ) {
		List< String > params = Arrays.asList( instruction.split( ";" ) );
		
		if( Operation.INSERT.name().equals( params.get( 0 ) ) ) {
			context.put( new BigInteger( params.get( 1 ) ), params.get( 2 ) );
		} else if( Operation.UPDATE.name().equals( params.get( 0 ) ) ) {
			context.put( new BigInteger( params.get( 1 ) ), params.get( 2 ) );
		} else {
			context.remove( new BigInteger( params.get( 1 ) ) );
		}
	}

}
