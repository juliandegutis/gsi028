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

	private static Map< BigInteger, Object > context;
	
	public static void start() {
		context = new LinkedHashMap< BigInteger, Object >();
	}
	
	public static void put( BigInteger key, Object object ) {
		context.put( key, object );
	}
	
	public static void remove( BigInteger key ) {
		context.remove( key );
	}
	
	public static void load( Path path ) throws IOException {
		start();
		Files.lines( path ).forEach( (line) -> load( line ) );
	}
	
	public static void load( String line ) {
		List< String > list = Arrays.asList( line.split( ";" ) );
		
		if( Operation.INSERT.name().equals( list.get( 1 ) ) ) {
			context.put( new BigInteger( list.get( 0 ) ), list.get( 2 ) );
		} else if( Operation.UPDATE.name().equals( list.get( 1 ) ) ) {
			context.put( new BigInteger( list.get( 0 ) ), list.get( 2 ) );
		} else if( Operation.DELETE.name().equals( list.get( 1 ) ) ) {
			context.remove( new BigInteger( list.get( 0 ) ) );
		}
	}
	
}