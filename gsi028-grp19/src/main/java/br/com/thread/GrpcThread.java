package br.com.thread;

import java.util.Queue;

import br.com.context.Context;

public class GrpcThread implements Runnable {

	private Queue< String > logQueue;

	private Queue< String > executeQueue;
	
	private Context context;
		
	public GrpcThread( Queue< String > logQueue, Queue< String > executeQueue, Context context ) {
		super();
		this.logQueue = logQueue;
		this.executeQueue = executeQueue;
		this.context = context;
	}
	
	@Override
	public void run() {

	}

}
