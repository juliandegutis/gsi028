package br.com.entrega1.context;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.entrega1.enums.Operation;

/** 
 * Contexto da aplica��o
 * @author juliang
 *
 */
public class Context {

	private Map< BigInteger, Object > context;
	
	public Context() {
		this.context = new LinkedHashMap< BigInteger, Object >();
	}
	
	public void put( BigInteger key, Object object ) {
		context.put( key, object );
	}
	
	public void remove( BigInteger key ) {
		context.remove( key );
	}
	
	public void load( Path path ) throws IOException {
		Files.lines( path ).forEach( (line) -> load( line ) );
	}
	
	public void load( String line ) {
		List< String > list = Arrays.asList( line.split( ";" ) );
		
		if( Operation.INSERT.name().equals( list.get( 0 ) ) ) {
			context.put( new BigInteger( list.get( 1 ) ), list.get( 2 ) );
		} else if( Operation.UPDATE.name().equals( list.get( 0 ) ) ) {
			context.put( new BigInteger( list.get( 1 ) ), list.get( 2 ) );
		} else if( Operation.DELETE.name().equals( list.get( 0 ) ) ) {
			context.remove( new BigInteger( list.get( 1 ) ) );
		}
	}
	
	public String stringfy() {
		String toString = "";
		for( Map.Entry< BigInteger, Object > entry : context.entrySet() ) {
			toString = toString.concat( "(" + entry.getKey().toString() + "," + entry.getValue().toString() + ")" );
		}
		return toString;
	}
	
}
