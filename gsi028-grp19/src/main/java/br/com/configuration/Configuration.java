package br.com.configuration;

import java.io.File;

import br.com.file.FileReader;

/**
 * Classe para Configuracao Externa
 * @author juliang
 *
 */
public class Configuration {

	private final static String SERVER_SETTINGS = "src/main/resources/config/server.xml";
	private final static String CLIENT_SETTINGS = "src/main/resources/config/client.xml";
	
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
