/*
 * calculateResult.java
 *
 * Created on June 26, 2008, 12:56 PM
 */
package org.bf.disability.dao;

import java.util.*;

/**
 * this class is for doing calculation
 * @author Pramod Kumar .G
 * @version 1.0
 */
public class calculateResult {

    /**
     * 
     * @param a 
     * @return 
     */
    public double needCalculation(LinkedList a) {
        double final1;
        // Findout the Highest value in the arraylist.
        double max1 = ((Double) Collections.max(a)).doubleValue();
        //sorting the arraylist into ascending order.
        Collections.sort(a);
        //if highest value is 0 means no elements are there in the array.
        if (max1 == 0) {
            final1 = max1;
        } //if atleast one value is 90 or >90 then directly take that value as final1.
        else if (max1 >= 90) {
            final1 = max1;
        } else {
            final1 = calcResult(a);
        }
        return final1;
    }

    /**
     * 
     * @param obj 
     * @return 
     */
    public double calcResult(LinkedList obj) {
        double max1 = 0;



        Collections.reverse(obj);

        Object o[] = obj.toArray();
        double a[] = new double[o.length];

        for (int i = 0; i < o.length; i++) {
            a[i] = ((Double) o[i]).doubleValue();
        }

        if (a.length == 1) {
            max1 = a[0];
        } else if (a.length == 2) {
            max1 = internalCalculation(a[0], a[1]);
        } else {
            max1 = internalCalculation(a[0], a[1]);

            for (int i = 2; i < a.length; i++) {
                if (a[i] == 0) {
                    break;
                }
                max1 = internalCalculation(max1, a[i]);
            }
        }

        return max1;
    }

    /**
     * 
     * @param a 
     * @param b 
     * @return 
     */
    public double internalCalculation(double a, double b) {

        a = a + ((b * (90 - a)) / 90);

        return a;
    }
}
