package fds.java.queue;


import com.zink.queue.Connection;
import com.zink.queue.ConnectionFactory;
import com.zink.queue.ReadChannel;


/**
 * Stub for the Producer of information to queue
 */
public class Consumer {


    public static void main(String[] args) throws Exception {

        Connection con = ConnectionFactory.connect("192.168.99.100");

        ReadChannel rc = con.subscribe("BBC7");
        System.out.println(rc.read());

        // TODO - Read from the channel until the finish signal
	}


}
