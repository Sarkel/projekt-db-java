package org.library.collections;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Pair<T, V> {
    /**
     * @return  value of property key
     */
    public T getKey() {
        return key;
    }

    /**
     * @param key   new value of property key
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * @return  value of property value
     */
    public V getValue() {
        return value;
    }

    /**
     * @param value new value of property value
     */
    public void setValue(V value) {
        this.value = value;
    }

    private T key;

    private V value;

    /**
     * @param key   initial value of property key
     * @param value initial value of property value
     */
    public Pair(T key, V value){
        this.key = key;
        this.value = value;
    }
}
