package fds.consistent;

/**
 * Stub for a Consistent hash ring
 */
public class HashRing {

    // TODO create data structure for the hash ring

    public HashRing( int size ) {
        // TODO set up the ring of the given size
    }


    public RingNode addNode(RingNode node) {
        // TODO add a ring node to this ring
    }

    public RingNode removeNode(RingNode node) {
        // TODO remove a ring node from this ring
    }

    public void put(String  key, Object  value) {
        // TODO find the appropriate node and add the value under the key
    }

    public Object get( String key) {
        // TODO find the appropriate node and get the value given the key
    }

    public int size() {
        // TODO Total the number of
    }

    private RingNode walkRingToFindNextNode( int startingLocation) {
        // TODO walk round the ring until you hit a node
    }

}
