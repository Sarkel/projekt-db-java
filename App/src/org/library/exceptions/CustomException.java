package org.library.exceptions;

/**
 * Created by Sebastian Kubalski on 21.01.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class CustomException extends Exception {
    /**
     * @param msg exception message
     */
    public CustomException(String msg){
        super(msg);
    }
}
