package fds.java.consistent;

/**
 * Most simple local representation of a node on teh Ring
 */
import java.util.HashMap;
import java.util.Map;


public class RingNode {

    private String name;

    private Map<String,Object> map = new HashMap<String,Object>();

    public RingNode(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void put(String  key, Object  value) {
        map.put(key,value);
    }

    public Object get( String key) {
        return map.get(key);
    }

    public int size() {
        return map.size();
    }

}