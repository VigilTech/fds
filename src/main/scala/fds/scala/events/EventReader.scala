package fds.scala.events


import java.util._
import java.util.concurrent.CountDownLatch

import io.nats.streaming.{Message, StreamingConnection, StreamingConnectionFactory, SubscriptionOptions}


object EventReader {

  private[events] val targets  =
    "AdultSki" :: "WinterActivities" :: "DanielBaudSkiGuide" :: Nil

  private[events] def register(logLine: String, document: Map[String, Int]): Unit = {
    targets.foreach((target: String) => {
      if (logLine.contains(target))
        document.compute(target, (k, v) => if (v == null) 1 else v + 1)
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


  private[events] def formatDoc(document: Map[String, Int]): Unit = {

    cout("--------------------------------------------------------------------------------")
    cout("| Giant Media - Usage Report                                                   |")
    cout("--------------------------------------------------------------------------------")
    document.forEach((k, v) => System.out.println(String.format("| %-49s|%26d |", k, new Integer(v))))
    cout("--------------------------------------------------------------------------------")
    val views: Iterable[Int] = document.values.asScala
    cout(String.format("| %-49s|%26d |", "Total Views", new Integer(document.values.asScala.sum)))
    cout("--------------------------------------------------------------------------------")
  }

  private[events] def cout(str: String): Unit = {
    System.out.println(str)
  }

}