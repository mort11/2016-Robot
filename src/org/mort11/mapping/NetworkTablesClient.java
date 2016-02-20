package org.mort11.mapping;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkTablesClient {

    public static void main(String[] args) {
        new NetworkTablesClient().run();
    }

    public void run() {
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("10.0.11.22");
        NetworkTable table = NetworkTable.getTable("MapTable");

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NetworkTablesClient.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            double x = table.getNumber("LeftDT Dist", 0.0);
            System.out.println(x);

        }
    }


}

