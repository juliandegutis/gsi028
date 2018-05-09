package br.com.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.configuration.SocketSetting;
import br.com.enums.Operation;
import br.com.proto.ContextProto.ContextRequest;
import br.com.proto.ContextProto.ContextResponse;
import br.com.proto.ContextServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientSenderGrpcThread implements Runnable {

	private ExecutorService executor = Executors.newFixedThreadPool( 1 );
	
	private SocketSetting settings;
	
	public ClientSenderGrpcThread( SocketSetting settings ) {
		this.settings = settings;
	}
	
	@Override
	public void run() {

		String server = settings.getHost() + ":" + settings.getPort();
		
		final ManagedChannel channel = ManagedChannelBuilder.forTarget( server ).usePlaintext( true ).build();

		
		try {

			System.out.println( "Preparado para receber a mensagem: OPERACAO CHAVE VALOR" );
			System.out.println( "OPERACAO: INSERT/UPDATE/DELETE/RETURN" );
			System.out.println( "CHAVE: BigInteger" );
			System.out.println( "VALOR: String" );

			while ( true ) {

				String sentence = "";
				Thread.sleep( 300 );

				Future< String > future = readLine();
				try {
					
					while ( !future.isDone() ) {
						Thread.sleep( 300 );
					}
					Object result = future.get();
					sentence = result.toString();
					
				} catch ( InterruptedException e ) {
					continue;
				} catch ( ExecutionException e ) {
					continue;
				} finally {
					future.cancel( true );
				}

				/**
				 * Leitor do buffer do console
				 */
				if ( validate( sentence ) ) {
					
					ContextServiceGrpc.ContextServiceBlockingStub stub = ContextServiceGrpc.newBlockingStub( channel );
					ContextRequest request = ContextRequest.newBuilder().setKey( "10" ).setValue( sentence ).build();

					ContextResponse response = stub.insert( request );

					System.out.println( response );

					channel.shutdownNow();
					
				}

				Thread.sleep( 1 );

			}

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}

	}
	
	
	public static Boolean validate( String sentence ) {

		List< String > params = Arrays.asList( sentence.split( " " ) );

		if ( !Operation.DELETE.name().equals( params.get( 0 ) ) && !Operation.INSERT.name().equals( params.get( 0 ) )
			&& !Operation.UPDATE.name().equals( params.get( 0 ) )
			&& !Operation.RETURN.name().equals( params.get( 0 ) ) ) {
			System.out.println( "Operacao Invalida." );
			return Boolean.FALSE;
		} else {
			if ( Operation.DELETE.name().equals( params.get( 0 ) ) && params.size() != 2 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: DELETE 3" );
				return Boolean.FALSE;
			} else if ( Operation.RETURN.name().equals( params.get( 0 ) ) && params.size() > 2 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: RETURN / RETURN KEY" );
				return Boolean.FALSE;
			} else if ( Operation.INSERT.name().equals( params.get( 0 ) ) && params.size() < 3 ) {
				System.out.println( "Quantidade de Parametros Invalidos. Exemplo: INSERT 3 VALOR" );
				return Boolean.FALSE;
			} else {
				if( Operation.INSERT.name().equals( params.get( 0 ) ) && params.get( 1 ).getBytes().length > 20 ) {
					System.out.println( "Quantidade de bytes da chave maior do que o permitido. O permitido sao de 20 bytes" );
					return Boolean.FALSE;
				} else if( Operation.INSERT.name().equals( params.get( 0 ) ) && params.get( 2 ).getBytes().length > 1400 ) {
					System.out.println( "Quantidade de bytes do valor maior do que o permitido. O permitido sao de 1400 bytes" );
					return Boolean.FALSE;
				} else if( Operation.UPDATE.name().equals( params.get( 0 ) ) && params.get( 2 ).getBytes().length > 1400 ) {
					System.out.println( "Quantidade de bytes do valor maior do que o permitido. O permitido sao de 1400 bytes" );
					return Boolean.FALSE;
				} else if( Operation.INSERT.name().equals( params.get( 0 ) ) && params.get( 1 ).getBytes().length > 20 ) {
					System.out.println( "Quantidade de bytes da chave maior do que o permitido. O permitido s�o de 20 bytes" );
					return Boolean.FALSE;
				}
				return Boolean.TRUE;
			}
		}

	}

	public Future< String > readLine() {
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );
		return executor.submit( () -> {
			return inFromUser.readLine();
		} );
	}

}
