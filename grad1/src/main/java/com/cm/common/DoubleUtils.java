package com.cm.common;

import java.math.BigDecimal;

public class DoubleUtils {

	
	
	 public static BigDecimal add(double v1, double v2) {
         BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
	 
	//
	 public static BigDecimal sub(double v1, double v2) {
         BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }
}
