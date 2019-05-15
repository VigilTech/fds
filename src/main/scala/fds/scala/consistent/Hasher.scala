package fds.scala.consistent

import java.math.BigInteger
import java.security.MessageDigest

/**
  * Helper Class to lock Hash Algo String to simple method names
  */
object Hasher {
  def sha1(str: String): BigInteger = hash(str, "SHA-1")

  def sha256(str: String): BigInteger = hash(str, "SHA-256")

  def sha512(str: String): BigInteger = hash(str, "SHA-512")

  def md2(str: String): BigInteger = hash(str, "MD2")

  def md5(str: String): BigInteger = hash(str, "MD5")

  private def hash(str: String, algo: String) = try {
    val md = MessageDigest.getInstance(algo)
    new BigInteger(1, md.digest(str.getBytes("UTF-8")))
  } catch {
    case exp: Exception =>
      throw new RuntimeException("Could not hash string")
  }
}
