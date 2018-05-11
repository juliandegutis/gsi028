package br.com.server;

import java.net.DatagramSocket;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.configuration.Configuration;
import br.com.configuration.SocketSetting;
import br.com.context.Context;
import br.com.proto.ContextProto.SubscribeResponse;
import br.com.thread.ExecutorThread;
import br.com.thread.GrpcThread;
import br.com.thread.LogThread;
import br.com.thread.ServerRecieveThread;
import io.grpc.stub.StreamObserver;

/**
 * Servidor UDP
 * @author juliang
 *
 */
public class CustomServer {

	private static DatagramSocket serverSocket;
	private static SocketSetting mySettings;
	private static SocketSetting grpcServerSettings;
	private static Queue< String > logQueue = new LinkedList< String >();
	private static Queue< String > executeQueue = new LinkedList< String >();
	private static Map< String, List< StreamObserver< SubscribeResponse > > > observers = new HashMap< String, List< StreamObserver< SubscribeResponse > > >();
	private static Context context;
	private static ExecutorService executor;
	
	
	public static void main( String args[] ) throws Exception {
		
		/**
		 * Externalizacoes das configuracoes
		 */
		context = new Context();
		context.load( Paths.get( "src/main/resources/log/log.txt" ) );
		mySettings = Configuration.serverSettings();
		grpcServerSettings = Configuration.grpcServerSettings();
		serverSocket = new DatagramSocket( mySettings.getPort() );
		
		executor = Executors.newFixedThreadPool(50);
		
		/**
		 * Declaracao das Threads
		 */
		LogThread logThread = new LogThread( logQueue );
		
		ExecutorThread executorThread = new ExecutorThread( serverSocket, logQueue, executeQueue, context, observers );
				
		ServerRecieveThread recieveThread = new ServerRecieveThread( serverSocket, executeQueue );
		
		GrpcThread grpcServerThread = new GrpcThread( logQueue, executeQueue, context, grpcServerSettings, observers );
		
		/**
		 * Execucao das Threads
		 */
		executor.execute( logThread );
		executor.execute( executorThread );
		executor.execute( recieveThread );
		executor.execute( grpcServerThread );
	}

}
