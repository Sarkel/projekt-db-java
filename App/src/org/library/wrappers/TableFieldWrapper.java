package org.library.wrappers;

/**
 * Created by sebas on 21.01.2016.
 */
public class TableFieldWrapper<T> {
    public String name;

    public T value;

    public TableFieldWrapper(String name, T value){
        this.name = name;
        this.value = value;
    }
}
