package br.com.entrega1.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

	public static void writeToFile( String path, String text ) throws IOException {
		Files.write( Paths.get( path ), text.getBytes(), StandardOpenOption.APPEND );
	}

}
