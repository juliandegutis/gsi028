package br.com.thread;

import java.util.Queue;

import br.com.file.FileWriter;

/**
 * Thread executora da escrita em Log
 * @author juliang
 *
 */
public class LogThread implements Runnable {
	
	private final String LOG_PATH = "src/main/resources/log/log.txt";
	private Queue< String > logQueue;
	private Boolean semaphore;
	
	public LogThread( Queue< String > queue, Boolean semaphore ) {
		this.logQueue = queue;
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		try {		
			
			while( true ) {
				String log = logQueue.poll();
				if( log != null && !semaphore ) {
					System.out.println( "Salvando para o log: " + log );
					FileWriter.writeToFile( LOG_PATH, log );	
				}
				Thread.sleep( 1 );
			}
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		
	}

}
