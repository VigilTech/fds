package fds.consistent;

/**
 * Helper Class to lock Hash Algo String to simple method names
 */
import java.math.BigInteger;
import java.security.MessageDigest;


public class Hasher {

    public static BigInteger sha1(String str) { return hash(str,"SHA-1"); }

    public static BigInteger sha256(String str) { return hash(str,"SHA-256"); }

    public static BigInteger sha512(String str) { return hash(str,"SHA-512"); }

    public static BigInteger md2(String str) { return hash(str,"MD2"); }

    public static BigInteger md5(String str) { return hash(str,"MD5"); }


    private static BigInteger hash(String str, String algo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            return new BigInteger(1,md.digest(str.getBytes("UTF-8")));
        }
        catch(Exception exp) {
            throw new RuntimeException("Could not hash string");
        }
    }

}