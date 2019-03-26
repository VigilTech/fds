package fds.java.events;

import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import io.nats.streaming.SubscriptionOptions;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Stub for the Event Reader of the Nats EventStore
 */
public class EventReader {

    final static Set<String> targets= new HashSet<>(
                                Arrays.asList(  "AdultSki",
                                                "WinterActivities",
                                                "DanielBaudSkiGuide")
                                );

    final static void register(final String logLine, final Map<String,Integer> document) {
        targets.forEach( ( target ) -> {
            if ( logLine.contains((target)) )
                document.compute(target, (k, v) -> (v == null) ? 1 : v + 1);
        });
    }


    public static void main(String[] args) throws Exception {

        final Map<String,Integer> document = new HashMap<>();

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


	// Format a map into a report
    final static String formatDoc(final Map<String,Integer> document ) {
        List<String> rep = new ArrayList<>();
        rep.add("--------------------------------------------------------------------------------");
        rep.add("| Giant Media - Usage Report                                                   |");
        rep.add("--------------------------------------------------------------------------------");
        document.forEach( (k,v) -> rep.add(String.format("| %-49s|%26d |",k,v)));
        rep.add("--------------------------------------------------------------------------------");
        final Stream<Integer> views = document.values().stream();
        rep.add(String.format("| %-49s|%26d |","Total Views", views.reduce(0, Integer::sum)));
        rep.add("--------------------------------------------------------------------------------");
        return rep.stream().collect(Collectors.joining("\n"));
    }


    // Write a String into a named file
    final static void writeToFile(final String filename, final String content) throws IOException {
        Files.write(Paths.get(filename), content.getBytes(StandardCharsets.UTF_8));
    }

    // Read a String from a named file
    final static String readFromFile(final String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
    }


}
