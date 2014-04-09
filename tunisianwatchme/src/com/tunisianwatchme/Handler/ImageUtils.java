/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import de.enough.polish.util.base64.Base64;
import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Farouk
 */
public class ImageUtils {

    public static byte[] decToString(String str) throws IOException {
        byte[] decoded = Base64.decode(str);
        return decoded;
    }
}