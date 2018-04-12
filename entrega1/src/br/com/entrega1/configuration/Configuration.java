package br.com.entrega1.configuration;

import java.io.File;

import br.com.entrega1.file.FileReader;

public class Configuration {

	private final static String SERVER_SETTINGS = "resources/config/server.xml";
	private final static String CLIENT_SETTINGS = "resources/config/client.xml";
	
	public static SocketSetting serverSettings() {
		
		File file = new File( SERVER_SETTINGS );
		return FileReader.readXMLConfiguration( file );
		
	}
	
	public static SocketSetting clientSettings() {
		
		File file = new File( CLIENT_SETTINGS );
		return FileReader.readXMLConfiguration( file );
		
	}
	
}
