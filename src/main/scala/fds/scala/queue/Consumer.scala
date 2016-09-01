package fds.scala.queue

import com.zink.queue.{ReadChannel, ConnectionFactory, Connection}

/**
  *  Consumer Stub
  */
object Consumer extends App {

      val con: Connection = ConnectionFactory.connect("192.168.99.100")
      val rc: ReadChannel = con.subscribe("BBC7")
      println(rc.read)

      // TODO - read each line and count up the number of lines
      // TODO - stop when the end of stream marker is found

}
