package br.com.test;

import br.com.service.ContextService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ServerGrpc {

	public static void main( String[] args ) {

		try {
			Server server = ServerBuilder.forPort( 8085 ).addService( new ContextService( null, null, null ) ).build();

			// Start the server
			server.start();

			// Server threads are running in the background.
			System.out.println( "Server started" );
			// Don't exit the main thread. Wait until server is terminated.
			server.awaitTermination();
			
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
