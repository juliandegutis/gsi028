package br.com.configuration;

/**
 * Classe para a configuracao dos sockets
 * @author juliang
 *
 */
public class SocketSetting {

	private String host;
	
	private int port;
	
	public SocketSetting() {}
	
	public SocketSetting( String host, int port ) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
