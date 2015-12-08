package fds.scala.cache

import java.net.{URL, URLConnection}
import java.util.Scanner

/**
  *
  */
object  MovieDBClient extends App {

  private[cache] val base: String = "https://api.themoviedb.org/3/"
  private[cache] val key: String = "?api_key=???"

  val ids: Seq[String] = getPopularMovieIDs
  System.out.println( ids )
  System.out.println( getDetailsById(ids(0) ) )

  def getPopularMovieIDs: Seq[String] = {
    val pop: String = "movie/popular"
    val json: String = httpGet(base + pop + key)
    val ids = scanForKeysValue("\"id\":", json)
    return ids
  }

  def scanForKeysValue(pattern: String, target: String): Seq[String] = {
    val idx: Int = target.indexOf(pattern)
    if (idx == -1) {  Seq[String]() }
    else {
      val valueAndTail : String = target.substring(idx + pattern.length)
      val ids: Seq[String] = scanForKeysValue(pattern, valueAndTail)
      ids.map( x => x.split(",")(0) )
      return ids
    }
  }

  def getDetailsById(id: String): String = {
    val movie: String = "movie/"
    return httpGet(base + movie + id + key)
  }

  def httpGet(uri: String): String = {
    val conn: URLConnection = new URL(uri).openConnection
    val scr: Scanner = new Scanner(conn.getInputStream)
    scr.useDelimiter("\\Z")
    return scr.next
  }

}


