/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.sun.midp.io.Base64;
import java.io.IOException;

/**
 *
 * @author Farouk
 */
public class ImageUtils {

    public static String decToString(String str) throws IOException {
        byte[] decoded = Base64.decode(str);
        String strDecoded = new String(decoded);
        return strDecoded;
    }
}