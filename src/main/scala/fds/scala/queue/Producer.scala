package fds.scala.queue

import java.io.File

import com.zink.queue.{WriteChannel, ConnectionFactory, Connection}

/**
  * Producer stub
  */
object Producer extends App {

  val con : Connection = ConnectionFactory.connect("192.168.99.100")
  val wc : WriteChannel = con.publish("BBC7")
  wc.write("Hello Consumer")

  val fileName = "access.log"
  val logLines = io.Source.fromFile(fileName).getLines

  // TODO use logLines to write each line onto the channel

  // TODO write an end of stream marker\

}
