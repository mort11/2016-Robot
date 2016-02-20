package org.mort11.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Logger - Utility for writing log files to RoboRIO for later use
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Logger {
    private static PrintWriter writer;

    public static void init(String filepath) {
        Date date = new Date();
        try {
            writer = new PrintWriter(filepath + "_" + new Timestamp(date.getTime()), "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void writeString(String msg) {
        writer.write(msg + "\n");
    }

    public static void close() {
        writer.close();
    }
}
