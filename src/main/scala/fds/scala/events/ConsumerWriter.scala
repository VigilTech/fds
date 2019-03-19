package fds.scala.events


import com.zink.queue.Connection
import com.zink.queue.ConnectionFactory
import com.zink.queue.ReadChannel
import io.nats.streaming.StreamingConnection
import io.nats.streaming.StreamingConnectionFactory


/**
  * Stub for the Consumer of information from the Queue and writer into the Nats EventStore
  *
  * The following condensed from Docker
  *
  * To Pull Nats server from Docker
  * > docker pull nats-streaming
  *
  * Then to run on Windows
  * > docker run -d -p 4222:4222 -p 8222:8222 nats-streaming nats-streaming-server -p 4222 -m 8222
  *
  * And on Unix
  * > docker run -d -p 4222:4222 -p 8222:8222 nats-streaming -p 4222 -m 8222
  *
  * To check it is running
  * > docker ps
  *
  */
object ConsumerWriter {

  @throws[Exception]
  def main(args: Array[String]): Unit = {

    // Connect to queue
    val ipAddr = "localhost" // or "192.168.1.84" e.g.
    val con = ConnectionFactory.connect(ipAddr)
    val channelName = "BBC7"
    val rc = con.subscribe(channelName)

    // Connect to EventStore
    val clusterID = "test-cluster"
    val clientID = "event-writer"
    val cf = new StreamingConnectionFactory(clusterID, clientID)
    val sc = cf.createConnection

    // TODO - write the events into the event store
    // Hints ...
    // to read an item from the queue
    val event = rc.read.asInstanceOf[String]
    // to write an item to the event store
    val subject = "BBC7"
    sc.publish(subject, event.getBytes)

  }

}
