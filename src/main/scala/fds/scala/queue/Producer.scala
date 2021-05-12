package fds.scala.queue


import io.nats.client.Connection
import io.nats.client.Nats
import java.nio.charset.StandardCharsets


/**
  * Producer stub
  *
  */
object Producer extends App {

  val nc: Connection  = Nats.connect("nats://localhost:4222")

  // Write a start of stream marker
  nc.publish("logs", "Starting Streaming".getBytes(StandardCharsets.UTF_8))

  val fileName = "access.log"
  val logLines = scala.io.Source.fromFile(fileName).getLines

  // TODO use logLines to write each line onto the channel
  for (line <- logLines) {
      // ...
  }

  // TODO write an end of stream marker

}
