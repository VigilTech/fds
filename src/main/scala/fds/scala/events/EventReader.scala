package fds.scala.events


import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util._
import java.util.concurrent.CountDownLatch

import io.nats.streaming.{Message, StreamingConnection, StreamingConnectionFactory, SubscriptionOptions}

import scala.collection.mutable


object EventReader {

  private[events] val targets  =
    "AdultSki" :: "WinterActivities" :: "DanielBaudSkiGuide" :: Nil

  private[events] def register(logLine: String, document: Map[String, Int]): Unit = {
    targets.foreach((target: String) => {
      if (logLine.contains(target))
        document.compute(target, (k, v) => if (v.equals(null)) 1 else v + 1)
    })
  }

  @throws[Exception]
  def main(args: Array[String]): Unit = {

    val document = new HashMap[String, Int]

    // Connect to EventStore
    val clusterID: String = "test-cluster"
    val clientID: String = "event-reader"
    val cf: StreamingConnectionFactory = new StreamingConnectionFactory(clusterID, clientID)
    val sc: StreamingConnection = cf.createConnection

    // Subscribe to the store for events
    val subject: String = "BBC7"
    // you may want to remove count down latch for the stream.
    val doneLatch: CountDownLatch = new CountDownLatch(1)

    //  Many subscribe options @see https://nats.io/documentation/writing_applications/subscribing/
    val opts: SubscriptionOptions = new SubscriptionOptions.Builder().deliverAllAvailable.build

    sc.subscribe(subject, (evt: Message) => {
      System.out.println(evt)
      doneLatch.countDown
    }, opts)

    // wait for a message
    doneLatch.await
    // tidy up the connection
    sc.close
  }

  import scala.collection.JavaConverters._


  // Format the map into a report
  private[events] def formatDoc(document: Map[String, Int]): String = {
    val rep  = mutable.ArrayBuffer[String]()
    rep += "--------------------------------------------------------------------------------"
    rep += "| Giant Media - Usage Report                                                   |"
    rep += "--------------------------------------------------------------------------------"
    rep ++= document.asScala.map{ case (k,v) => String.format("| %-49s|%26d |", k, new Integer(v)) }
    rep += "--------------------------------------------------------------------------------"
    val views = document.asScala.values.sum
    rep += String.format("| %-49s|%26d |", "Total Views", new Integer(views))
    rep += "--------------------------------------------------------------------------------"
    return rep.mkString("\n")
  }

  // Write a String into a named file
  private[events] def writeToFile(filename: String, content: String): Unit = {
    Files.write(Paths.get(filename), content.getBytes(StandardCharsets.UTF_8))
  }

  // Read a String from a named file
  private[events] def readFromFile(filename: String) = {
    new  String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8)
  }


}