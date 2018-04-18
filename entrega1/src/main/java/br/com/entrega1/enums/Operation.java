package br.com.entrega1.enums;

/**
 * Enum de operadores CRUD
 * @author juliang
 *
 */
public enum Operation {

	INSERT( "INSERT" ), 
	DELETE( "DELETE" ),
	UPDATE( "UPDATE" ),
	RETURN( "RETURN" );
	
	private String value;
	
	Operation( String value ) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
