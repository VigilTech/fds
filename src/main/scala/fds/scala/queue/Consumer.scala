package fds.scala.queue

import io.nats.client.{Connection, Dispatcher, Nats}

import java.nio.charset.StandardCharsets
import java.time.Duration


/**
  *  Consumer Stub
  */
object Consumer extends App {

      val nc: Connection  = Nats.connect("nats://localhost:4222")

      val sub = nc.subscribe("logs")

      while (!Thread.interrupted()) {
            val msg = sub.nextMessage(Duration.ofMillis(500))
            if (msg != null) {
                  val response = new String(msg.getData, StandardCharsets.UTF_8)
                  println(response)
            }
      }

      // TODO - rewrite the blocking code above into the Dispatcher code below.
      // TODO - read each line and count up the number of lines
      // TODO - stop when the end of stream marker is found

//          Dispatcher d = nc.createDispatcher((msg) => {
//            val response = new String(msg.getData(), StandardCharsets.UTF_8);
//            println(response)
//                });
//          dispatcher.subscribe("logs")

}
