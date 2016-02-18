package org.library.wrappers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author Sebastian Kubalski
 * @version 1.0
 */
public class FunctionalityHelper {
    /**
     * @param value value for rounding
     * @param places number of decimal places
     * @return rounded value
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
