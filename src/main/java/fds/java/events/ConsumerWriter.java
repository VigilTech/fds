package fds.java.events;


import com.zink.queue.Connection;
import com.zink.queue.ConnectionFactory;
import com.zink.queue.ReadChannel;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;


/**
 * Stub for the Consumer of information from the Queue and writer into the event store
 *
 * The following condensed from Docker
 *
 * To Pull Nats server from Docker
 * > docker pull nats-streaming
 *
 * Then to run on Windows
 * > docker run -d -p 4222:4222 -p 8222:8222 nats-streaming nats-streaming-server -p 4222 -m 8222
 *
 *  And on Unix
 * > docker run -d -p 4222:4222 -p 8222:8222 nats-streaming -p 4222 -m 8222
 *
 * To check it is running
 * > docker ps
 *
 */
public class ConsumerWriter {


    public static void main(String[] args) throws Exception {

        // Connect to queue
        final String ipAddr = "192.168.1.84";
        final Connection con = ConnectionFactory.connect(ipAddr);
        final String channelName = "BBC7";
        final ReadChannel rc = con.subscribe(channelName);

        // Connect to EventStore
        final String clusterID = "test-cluster";
        final String clientID = "event-writer";
        final StreamingConnectionFactory cf = new StreamingConnectionFactory(clusterID, clientID);
        final StreamingConnection sc = cf.createConnection();

        // TODO - write the events into the event store

        // Hints ...

        // to read an item from the queue
        final String event = (String) rc.read();

        // to write an item to the event store
        sc.publish(channelName, event.getBytes());
	}


}
