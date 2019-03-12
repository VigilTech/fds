package fds.java.events;

import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import io.nats.streaming.SubscriptionOptions;

import java.util.*;
import java.util.concurrent.CountDownLatch;


/**
 * Stub for the Event Reader of the Nats EventStore
 */
public class EventReader {

    final static Map<String,Integer> document = new HashMap<>();

    final static Set<String> targets= new HashSet<String>(
                                Arrays.asList(  "AdultSki",
                                                "WinterActivities",
                                                "DanielBaudSkiGuide")
                                );

    final static void register(final String logLine) {
        targets.forEach( ( target ) -> {
            if ( logLine.contains((target)) )
                document.compute(target, (k, v) -> (v == null) ? 1 : v + 1);
        });
    }

    final static void showDoc ( ) {
        document.forEach ( (k,v) -> System.out.println(k + ":" + v));
    }

    public static void main(String[] args) throws Exception {

        // Connect to EventStore
        final String clusterID = "test-cluster";
        final String clientID = "event-reader";
        final StreamingConnectionFactory cf = new StreamingConnectionFactory(clusterID, clientID);
        final StreamingConnection sc = cf.createConnection();

        // Subscribe to the store for events
        final String subject = "BBC7";

        // you may want to remove count down latch for the stream.
        final CountDownLatch doneLatch = new CountDownLatch(1);
        final SubscriptionOptions opts = new SubscriptionOptions.Builder().deliverAllAvailable().build();
        sc.subscribe(subject, evt -> {
            System.out.println(evt);
            doneLatch.countDown();
        } , opts);

        doneLatch.await();
        sc.close();
	}





}
