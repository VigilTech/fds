package fds.java.queue;


import com.zink.queue.*;

import java.io.FileInputStream;
import java.util.Scanner;


/**
 * Stub for the Producer of information to queue
 *
 * From Fly Docker
 *
 * > docker run -p 4396:4396 zink/fly
 *
 * To find container <run time name>
 * > docker ps
 *
 * To find ipAddress
 * > docker inspect <run time name> | grep -i ipaddress
 *
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        final String ipAddr = "192.168.1.84";
        Connection con = ConnectionFactory.connect(ipAddr);
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
