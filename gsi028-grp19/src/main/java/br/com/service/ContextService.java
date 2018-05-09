package br.com.service;

import java.util.Queue;

import br.com.context.Context;
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
		System.out.println( request.getValue() );
	}
	
	@Override
	public void delete( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		
	}
	
	@Override
	public void update( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		
	}
	
	@Override
	public void find( ContextRequest request, StreamObserver< ContextResponse > responseObserver ) {
		
	}
}
