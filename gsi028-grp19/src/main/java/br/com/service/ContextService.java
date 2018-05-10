package br.com.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.context.Context;
import br.com.enums.Operation;
import br.com.proto.ContextProto.ContextRequest;
import br.com.proto.ContextProto.ContextResponse;
import br.com.proto.ContextServiceGrpc;
import io.grpc.stub.StreamObserver;

public class ContextService extends ContextServiceGrpc.ContextServiceImplBase {

	private Queue< String > logQueue;

	private Queue< String > executeQueue;

	private Context context;

	public ContextService( Queue< String > logQueue, Queue< String > executeQueue, Context context ) {
		super();
		this.logQueue = logQueue;
		this.executeQueue = executeQueue;
		this.context = context;
	}

	@Override
	public void insert( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		messageToQueue( request.getInstruction() );
		ContextResponse response = ContextResponse.newBuilder().setMessage( "Instrucao enviada para execucao " + request.getInstruction() ).build();
		responseObserver.onNext( response );
		responseObserver.onCompleted();
	}

	@Override
	public void delete( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		messageToQueue( request.getInstruction() );
		ContextResponse response = ContextResponse.newBuilder().setMessage( "Instrucao enviada para execucao " + request.getInstruction() ).build();
		responseObserver.onNext( response );
		responseObserver.onCompleted();
	}

	@Override
	public void update( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		messageToQueue( request.getInstruction() );
		ContextResponse response = ContextResponse.newBuilder().setMessage( "Instrucao enviada para execucao " + request.getInstruction() ).build();
		responseObserver.onNext( response );
		responseObserver.onCompleted();
	}

	@Override
	public void find( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		String stringify = context.stringfy();
		ContextResponse response = ContextResponse.newBuilder().setMessage( stringify ).build();
		responseObserver.onNext( response );
		responseObserver.onCompleted();
	}

	/**
	 * Trata a mensagem e adiciona na fila de log
	 * 
	 * @param message
	 */
	private void messageToQueue( String message ) {

		List< String > list = new LinkedList< String >( Arrays.asList( message.split( " " ) ) );
		String operation = list.get( 0 );

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
