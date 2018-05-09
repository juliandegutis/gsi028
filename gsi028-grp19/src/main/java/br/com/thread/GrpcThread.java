package br.com.thread;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.configuration.SocketSetting;
import br.com.context.Context;
import br.com.service.ContextService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcThread implements Runnable {

	private Queue< String > logQueue;

	private Queue< String > executeQueue;

	private Context context;
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	private SocketSetting settings;

	public GrpcThread( Queue< String > logQueue, Queue< String > executeQueue, Context context, SocketSetting settings ) {
		super();
		this.logQueue = logQueue;
		this.executeQueue = executeQueue;
		this.context = context;
		this.settings = settings;
	}

	@Override
	public void run() {

		try {

			Server server = ServerBuilder.forPort( settings.getPort() )
				.addService( new ContextService( logQueue, executeQueue, context ) ).build();

			// Start the server
			server.start();

			// Server threads are running in the background.
			System.out.println( "Server started" );
			// Don't exit the main thread. Wait until server is terminated.
			
			Future< String > future = wait( server );
			try {
				while( !future.isDone() ) {
					Thread.sleep( 300 );
				}
				future.get();
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			} catch ( ExecutionException e ) {
				e.printStackTrace();
			} finally {
				future.cancel( true );
			}
			
			server.awaitTermination();

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public Future< String > wait( Server server ) {
		return executor.submit( () -> {
			server.awaitTermination();
			return null;
		} );
	}

}
