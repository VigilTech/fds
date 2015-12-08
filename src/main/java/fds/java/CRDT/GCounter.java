package fds.java.CRDT;

import java.util.HashMap;
import java.util.Map;


public class GCounter {

    private Map<String, Long> nodeVals = new HashMap<String, Long>();


    public void inc(String nodeName) { inc(nodeName, 1); }

    public void inc(String nodeName, long i) {
        // set up new node
        // nodeVals.put( k, new Long(0));
        //
    }

    public long value() {
        // TODO
        return 0L;
    }

    public GCounter merge(GCounter that) {
        // TODO
        return new GCounter();
    }

    public String toJson() {
        // TODO
        return "";
    }

    private long valOrZero(Long l) {
        if (l != null ) return l.longValue();
        return 0;
    }


    public static void main(String[] args) {

        GCounter a = new GCounter();
        a.inc("a",3);
        GCounter b = new GCounter();
        b.inc("b",7);
        GCounter c = new GCounter();
        c.inc("c",4);

    }

}