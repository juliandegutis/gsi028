package br.com.entrega1.configuration;

import java.io.File;

import br.com.entrega1.file.FileReader;

/**
 * Classe para Configura��o Externa
 * @author juliang
 *
 */
public class Configuration {

	private final static String SERVER_SETTINGS = "resources/config/server.xml";
	private final static String CLIENT_SETTINGS = "resources/config/client.xml";
	
	/**
	 * Leitura do XML do servidor
	 * @return SocketSettings
	 */
	public static SocketSetting serverSettings() {
		File file = new File( SERVER_SETTINGS );
		return FileReader.readXMLConfiguration( file );
	}
	
	/**
	 * Leitura do XML do cliente
	 * @return SocketSettings
	 */
	public static SocketSetting clientSettings() {
		
		File file = new File( CLIENT_SETTINGS );
		return FileReader.readXMLConfiguration( file );
		
	}
	
}
