package org.mort11.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Logger {
		
	 PrintWriter writer;
	
	public void init (String filepath) {
		try {
			writer = new PrintWriter(filepath,"UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeString(String msg) {
		writer.write(msg + "\n");
		System.out.println("printing");
	}
	
	public void close() {
		writer.close();
	}
}
	