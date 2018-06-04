package br.com.thread;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import br.com.context.Context;
import br.com.file.FileWriter;

public class SnapshotThread implements Runnable {

	private final String SNAPSHOT_FILE = "src/main/resources/snapshot/snapshot#DATE#.txt";
	private final String SNAPSHOT_BACKUP_FILE = "src/main/resources/snapshot/old/snapshot#DATE#.txt";

	private final String LOG_PATH = "src/main/resources/log/log#DATE#.txt";
	private final String LOG_BACKUP_PATH = "src/main/resources/log/log#DATE#.txt";


	
	private Context context;
	
	public SnapshotThread( Context context ) {
		this.context = context;
	}
	
	@Override
	public void run() {
		
		while( true ) {
			
			try {
			
				Date now = new Date();
				Map< BigInteger, Object > contextMap = context.get();
				
				FileWriter.moveFile( 
					SNAPSHOT_FILE.replace( "#DATE#", String.valueOf( now.getTime() ) ), 
					SNAPSHOT_BACKUP_FILE.replace( "#DATE#", String.valueOf( now.getTime() ) ) );
				
				FileWriter.moveFile( 
					SNAPSHOT_FILE.replace( "#DATE#", String.valueOf( now.getTime() ) ), 
					SNAPSHOT_BACKUP_FILE.replace( "#DATE#", String.valueOf( now.getTime() ) ) );
				
				for( Map.Entry< BigInteger, Object > entry : contextMap.entrySet() ) {
					String command = "INSERT;" + entry.getKey().toString() + ";" + entry.getValue().toString();
					FileWriter.writeToFile( SNAPSHOT_FILE.replace( "DATE", "" ), command );
				}
				
				
				Thread.sleep( 1000 );
			
			} catch( Exception donothing ) {
				
			}
			
		}
		
	}

}
