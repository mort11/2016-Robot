package org.mort11.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	File f;
	BufferedWriter bw;
	FileWriter fw;
	
	public Logger(String filepath) {
		try {
    		f = new File(filepath);
    		if(!f.exists()){
    			f.createNewFile();
    		}
			fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
	}
	
	public Logger() {
		new Logger("~/output");
	}
	
	public void writeString(String msg) {
		try {
			bw.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			fw.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
