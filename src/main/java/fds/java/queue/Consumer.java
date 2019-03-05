package fds.java.queue;


import com.zink.queue.Connection;
import com.zink.queue.ConnectionFactory;
import com.zink.queue.ReadChannel;


/**
 * Stub for the Consumer of information from queue
 */
public class Consumer {


    public static void main(String[] args) throws Exception {

        final String ipAddr = "192.168.1.84";
        Connection con = ConnectionFactory.connect(ipAddr);

        ReadChannel rc = con.subscribe("BBC7");
        System.out.println(rc.read());

        // TODO - Read from the channel until the finish signal
	}


}
