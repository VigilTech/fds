package fds.scala.events


import io.nats.client.{Connection, Dispatcher, Nats}
import io.nats.streaming.{Message, StreamingConnection, StreamingConnectionFactory}

import java.nio.charset.StandardCharsets


/**
  * Stub for the Consumer of information from the Queue and writer into the EventStore
  */
object ConsumerWriter {

  @throws[Exception]
  def main(args: Array[String]): Unit = {

    // Connect to the message queue
    val nc: Connection = Nats.connect("nats://localhost:4222")

    // Connect to EventStore
    val clusterID = "test-cluster"
    val clientID = "event-writer"
    val cf: StreamingConnectionFactory = new StreamingConnectionFactory(clusterID, clientID)
    val sc: StreamingConnection = cf.createConnection

    val dispatcher = nc.createDispatcher((msg) => {
                // Check message arrived
                println(new String(msg.getData(), StandardCharsets.UTF_8))
                // use sc to publish the message
                sc.publish("log-events", msg.getData())
          });
    dispatcher.subscribe("logs")
  }

}
