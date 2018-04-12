package br.com.entrega1.server;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.entrega1.file.FileWriter;

public class LogThread implements Runnable {
	
	private final String LOG_PATH = "resources/log/log.txt";
	private Map< BigInteger, String > logQueue = new LinkedHashMap< BigInteger, String >();
	
	public LogThread( Map< BigInteger, String > queue ) {
		super();
		this.logQueue = queue;
	}
	
	@Override
	public void run() {
		try {
			for( Map.Entry< BigInteger, String > entry : logQueue.entrySet() ) {
				String key = entry.getKey().toString();
				String value = entry.getValue();
				String log = key + ";" + value;
				FileWriter.writeToFile( LOG_PATH, log );
			} 
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		
	}

}
