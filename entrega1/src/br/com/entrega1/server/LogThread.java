package br.com.entrega1.server;

import java.util.LinkedList;
import java.util.Queue;

import br.com.entrega1.file.FileWriter;

/**
 * Thread executora da escrita em Log
 * @author juliang
 *
 */
public class LogThread implements Runnable {
	
	private final String LOG_PATH = "resources/log/log.txt";
	private Queue< String > logQueue = new LinkedList< String >();
	
	public LogThread( Queue< String > queue ) {
		super();
		this.logQueue = queue;
	}
	
	@Override
	public void run() {
		try {					
			while( true ) {
				String log = logQueue.poll();
				if( log != null ) {
					FileWriter.writeToFile( LOG_PATH, log );	
				}
			}
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		
	}

}
