package br.com.enums;

/**
 * Enum de operadores CRUD
 * @author juliang
 *
 */
public enum Operation {

	INSERT( "INSERT" ), 
	DELETE( "DELETE" ),
	UPDATE( "UPDATE" ),
	RETURN( "RETURN" ),
	SUBSCRIBE( "SUBSCRIBE" ),
	UNSUBSCRIBE( "UNSUBSCRIBE" );
	
	private String value;
	
	Operation( String value ) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
