package fds.scala.queue



import com.zink.queue.{WriteChannel, ConnectionFactory, Connection}

/**
  * Producer stub
  *
  * From Fly Docker
  *
  * > docker run -d -p 4396:4396 zink/fly
  *
  * To find container <run time name>
  * > docker ps
  *
  * To find ipAddress
  * > docker inspect <run time name> | grep -i ipaddress
  *
  */
object Producer extends App {

  val con : Connection = ConnectionFactory.connect("172.17.0.2")
  val wc : WriteChannel = con.publish("BBC7")

  wc.write("Hello Consumer")

  val fileName = "access.log"
  val logLines = scala.io.Source.fromFile(fileName).getLines

  // TODO use logLines to write each line onto the channel
  for (line <- logLines) {
      // ...
  }
  // TODO write an end of stream marker

}
