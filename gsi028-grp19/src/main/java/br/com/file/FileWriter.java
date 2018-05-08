package br.com.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstracao para operacoes de escritas em arquivo
 * @author juliang
 *
 */
public class FileWriter {

	/**
	 * Escreve o log das operacoes no arquivo
	 * @param path
	 * @param text
	 * @throws IOException
	 */
	public static void writeToFile( String path, String text ) throws IOException {
		List< String > list = new ArrayList< String >();
		list.add( text );
		Files.write( Paths.get( path ), list, StandardOpenOption.APPEND );
	}

}
