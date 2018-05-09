package br.com.test;

import br.com.proto.ContextProto.ContextRequest;
import br.com.proto.ContextProto.ContextResponse;
import br.com.proto.ContextServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientGrpc {

	public static void main( String[] args ) {
		// Channel is the abstraction to connect to a service endpoint
		// Let's use plaintext communication because we don't have certs
		final ManagedChannel channel = ManagedChannelBuilder.forTarget( "localhost:8085" ).usePlaintext( true ).build();

		// It is up to the client to determine whether to block the call
		// Here we create a blocking stub, but an async stub,
		// or an async stub with Future are always possible.
		ContextServiceGrpc.ContextServiceBlockingStub stub = ContextServiceGrpc.newBlockingStub( channel );
		ContextRequest request = ContextRequest.newBuilder().setKey( "10" ).setValue( "Oi" ).build();

		// Finally, make the call using the stub
		ContextResponse response = stub.insert( request );

		System.out.println( response );

		// A Channel should be shutdown before stopping the process.
		channel.shutdownNow();
	}

}
