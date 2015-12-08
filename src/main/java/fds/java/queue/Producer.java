package fds.java.queue;


import com.zink.queue.*;

import java.io.FileInputStream;
import java.util.Scanner;


/**
 * Stub for the Producer of information to queue
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        Connection con = ConnectionFactory.connect("192.168.99.100");

        WriteChannel wc = con.publish("BBC7");
        wc.write("Hello Consumer");

        /* Read a file from the local file system line by line */
        final String fileName = "file.log";
	    FileInputStream fis = new FileInputStream(fileName);
	    Scanner scanner = new Scanner(fis);
	    while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // TODO
            // Write to channel until the file is finished
        }
        // TODO
        // Write an end of stream marker
	}

}
