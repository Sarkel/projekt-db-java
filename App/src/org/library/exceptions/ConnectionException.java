package org.library.exceptions;

/**
 * Created by Sebastian Kubalski on 20.01.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class ConnectionException extends CustomException {
    /**
     * @param msg exception message
     */
    public ConnectionException(String msg){
        super(msg);
    }
}
