package fds.CRDT;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
    }

    public GCounter merge(GCounter that) {
        // TODO
    }

    public String toJson() {
        // TODO
    }

    private long valOrZero(Long l) {
        if (l != null ) return l.longValue();
        return 0;
    }


    public static void main(String[] args) {
        Set<String> nodes = new HashSet<String>();

        GCounter a = new GCounter().inc("a");
        GCounter b = new GCounter().inc("b");
        GCounter c = new GCounter().inc("c");

    }

}